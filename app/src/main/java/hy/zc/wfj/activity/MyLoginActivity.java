package hy.zc.wfj.activity;

import android.app.ProgressDialog;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.data.UserLoginErrorObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.CheckEmailMobile;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class MyLoginActivity extends FrameActivity implements View.OnClickListener {

    public static final String NAME_PASS_CANNOT_NULL = "用户名或者密码不能为空";
    public static final String ENTER_ERROR = "输入格式不正确";
    public static final String IS_LOGIN = "isLogin";
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
        common_title_txt = (TextView) this.findViewById(R.id.common_title_txt);
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
                goBack();
                break;
            case R.id.login_comfirm_button:
                String name = login_input_name.getText().toString().trim();
                String password = login_input_password.getText().toString().trim();
                if (name.equals("") && password.equals("")) {
                    Toast.makeText(getApplicationContext(), NAME_PASS_CANNOT_NULL, Toast.LENGTH_SHORT).show();
                } else if (CheckEmailMobile.isEmail(name) || CheckEmailMobile.isMobileNO(name) && !password.equals("")) {
                    pd = new ProgressDialog(MyLoginActivity.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setMessage("请稍等。。。");
                    pd.show();
                    String loginRequestUri = UriManager.getLoginRequestUri(name, password);
                    getDataFromUri(loginRequestUri);
                } else {
                    Toast.makeText(getApplicationContext(), ENTER_ERROR, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void getDataFromUri(String loginRequestUri) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequestUri, new Response.Listener<String>() {
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
                    SharedPrefUtility.setParam(MyLoginActivity.this, SharedPrefUtility.IS_LOGIN, userLoginObject.isStatus());
                    //关闭进度条显示
                    pd.dismiss();
                    //保存登录个人信息
                    SharedPrefUtility.setParam(MyLoginActivity.this, SharedPrefUtility.LOGIN_DATA, response);
                    /*这个地方需要注意一下，血的教训啊，传输的对象不仅本身需要Serializable，
                    内部类同样需要这么做，
                    否则回跳到之前的activity在onActivityResult中是接收不到Intent对象的，NND浪费老子大半天的时间*/
//                    Bundle bundle=new Bundle();
//                    bundle.putSerializable("receiveData",userLoginObject);
//                    goBackActivity(bundle);
                    //跳转到之前的界面,我现在不传输对象了，仅仅是返回，因为我把保存数据的操作在这之前就存储到share_data里面了
                    goBack();
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
