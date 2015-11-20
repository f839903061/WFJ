package hy.zc.wfj.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.drawee.backends.pipeline.Fresco;

import hy.zc.wfj.App;
import hy.zc.wfj.Main2Activity;
import hy.zc.wfj.R;
import hy.zc.wfj.data.UserLoginErrorObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;

public class MyLoginActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "fengluchun";
    public static final String NAME_PASS_CANNOT_NULL = "用户名或者密码不能为空";
    public static final String ENTER_ERROR = "输入有问题";
    public static final String IS_LOGIN = "isLogin";
    public static final String OBJ_NULL_ERR = "获取登录对象为空";
    public static final String STATE_ERR = "获取对象状态为false";
    public static final String CONNECT_ERR = "http 连接失败";

    private ImageButton mBackBtn;
    private EditText login_input_name;
    private EditText login_input_password;
    private Button login_comfirm_button;
    private ProgressDialog pd;
    private TextView common_title_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MyLoginActivity.this);
        setContentView(R.layout.activity_my_login);

        initializeComponent();
        setListener();
    }

    private void initializeComponent() {
        mBackBtn = (ImageButton) this.findViewById(R.id.common_title_back_btn);
        login_input_name = (EditText) this.findViewById(R.id.login_input_name);
        login_input_password = (EditText) this.findViewById(R.id.login_input_password);
        login_comfirm_button = (Button) this.findViewById(R.id.login_comfirm_button);
        common_title_txt=(TextView)this.findViewById(R.id.common_title_txt);
        common_title_txt.setText("登录");
    }

    private void setListener() {
        mBackBtn.setOnClickListener(this);
        login_comfirm_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back_btn:
                goBackActivity();
                break;
            case R.id.login_comfirm_button:
                String name = login_input_name.getText().toString().trim();
                String password = login_input_password.getText().toString().trim();
                if (name.equals("") && password.equals("")) {
                    Toast.makeText(getApplicationContext(), NAME_PASS_CANNOT_NULL, Toast.LENGTH_SHORT).show();
                } else if (!name.equals("") && !password.equals("")) {
                    pd = new ProgressDialog(MyLoginActivity.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setMessage("请稍等。。。");
                    pd.show();
                    getDataFromUri(name, password);
                } else {
                    Toast.makeText(getApplicationContext(), ENTER_ERROR, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void goBackActivity(Bundle pbundle) {
        Intent intent = getIntent();
        intent.putExtras(pbundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
    private void goBackActivity() {
        Intent intent = getIntent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void getDataFromUri(String pname, String ppassword) {
        StringBuilder stringBuilder = new StringBuilder("http://192.168.10.7:8080/wfj_front/phone/login.action?loginName=");
        stringBuilder.append(pname);
        stringBuilder.append("&cpassword=");
        stringBuilder.append(ppassword);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, stringBuilder.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //如果用户名或者密码或者格式错误，返回数据是非常短的
                if (response.length() <= 50) {
                    UserLoginErrorObject errorObject = JSON.parseObject(response, UserLoginErrorObject.class);
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), errorObject.getData(), Toast.LENGTH_SHORT).show();
                } else {//如果登录成功
                    UserLoginObject userLoginObject = JSON.parseObject(response, UserLoginObject.class);
                    //保存登录状态
                    SharedPrefUtility.setParam(getApplicationContext(), IS_LOGIN, userLoginObject.isStatus());
                    //关闭进度条显示
                    pd.dismiss();
                    //跳转到之前的界面
                    Bundle bundle=new Bundle();
                    /*这个地方需要注意一下，血的教训啊，传输的对象不仅本身需要Serializable，
                    内部同样需要这么做，
                    否则回跳到之前的activity在onActivityResult中是接收不到Intent对象的，NND浪费老子大半天的时间*/
                    bundle.putSerializable("receiveData",userLoginObject);
                    goBackActivity(bundle);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, CONNECT_ERR);
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
        App.addRequest(stringRequest, IS_LOGIN);
    }

    @Override
    protected void onStop() {
        App.cancelAllRequests(IS_LOGIN);
        super.onStop();
    }
}
