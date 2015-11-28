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

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.data.UserLoginErrorObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.CheckEmailMobile;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class ModifyPhoneActivity extends FrameActivity implements View.OnClickListener {

    public static final String IS_MODIFY_OK="isModifyOk";
    private ImageButton imgbtn_back;
    private Button imgbtn_ok;
    private TextView tv_title;
    private EditText et_phone;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_phone);

        initializeComponet();
        setListener();
    }

    private void setListener() {
        imgbtn_back.setOnClickListener(this);
        imgbtn_ok.setOnClickListener(this);

    }

    private void initializeComponet() {
        imgbtn_back = (ImageButton) findViewById(R.id.common_title_back_btn);
        imgbtn_ok = (Button) findViewById(R.id.img_tmp);
        imgbtn_ok.setVisibility(View.VISIBLE);
        imgbtn_ok.setText("确定");
        tv_title = (TextView) findViewById(R.id.common_title_txt);
        tv_title.setText("修改号码");
        et_phone = (EditText) findViewById(R.id.et_modify);

        loadData();
    }

    private void loadData(){
        String temp =(String) SharedPrefUtility.getParam(ModifyPhoneActivity.this, SharedPrefUtility.LOGIN_DATA, "");
        UserLoginObject userLoginObject = JSON.parseObject(temp, UserLoginObject.class);
        if (!userLoginObject.equals("")){
            et_phone.setText(userLoginObject.getData().getPhone());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back_btn://不做任何操作返回
                goBack();
                break;
            case R.id.img_tmp:
                showToast("ok");
//                String phone = et_phone.getText().toString();
//                if (CheckEmailMobile.isMobileNO(phone)) {
//                    pd = new ProgressDialog(ModifyPhoneActivity.this);
//                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                    pd.setMessage("请稍等。。。");
//                    pd.show();
//                    getDataFromUri(phone);
//                    goBack();
//
//                }
                break;
            default:
                break;
        }
    }

    private void getDataFromUri(String pphone) {
        String uri = UriManager.getModifyPhoneUri(pphone);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //如果用户名或者密码或者格式错误，返回数据是非常短的
                if (response.length() <= 50) {
                    UserLoginErrorObject errorObject = JSON.parseObject(response, UserLoginErrorObject.class);
                    pd.dismiss();
                    showToast(errorObject.getData());
                } else {//如果登录成功
                    UserLoginObject userLoginObject = JSON.parseObject(response, UserLoginObject.class);
                    //关闭进度条显示
                    pd.dismiss();
                    //保存登录个人信息
                    SharedPrefUtility.setParam(ModifyPhoneActivity.this, SharedPrefUtility.LOGIN_DATA, response);
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
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
        App.addRequest(stringRequest, IS_MODIFY_OK);
    }

    @Override
    protected void onStop() {
        App.cancelAllRequests(IS_MODIFY_OK);
        super.onStop();
    }
}
