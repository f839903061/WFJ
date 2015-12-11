package hy.zc.wfj.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.data.ChangePasswordObject;
import hy.zc.wfj.data.UserLoginErrorObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.CheckEmailPhone;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class ModifyActivity extends FrameActivity implements View.OnClickListener {

    public static final String IS_MODIFY_OK = "isModifyOk";
    private ImageButton imgb_back;
    private Button btn_ok;
    private TextView tv_title;
    private TextView tv_modify_tag;
    private EditText et_phone_nickname_password;
    private EditText et_new_passward;
    private EditText et_confirm_password;
    private ProgressDialog pd;
    private int MODIFY_FLAG = 0;
    private LinearLayout lay_modify_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_phone);

        initializeComponet();
        setListener();
    }

    private void setListener() {
        imgb_back.setOnClickListener(this);
        btn_ok.setOnClickListener(this);

    }

    private void initializeComponet() {
        lay_modify_pass = (LinearLayout) findViewById(R.id.layout_modify_password);
        tv_modify_tag = (TextView) findViewById(R.id.tv_modify_tag);
        tv_title = (TextView) findViewById(R.id.common_title_txt);
        et_new_passward = (EditText) findViewById(R.id.et_new_password);
        et_confirm_password = (EditText) findViewById(R.id.et_confirm_password);
        et_phone_nickname_password = (EditText) findViewById(R.id.et_modify);
        imgb_back = (ImageButton) findViewById(R.id.common_title_back_btn);
        btn_ok = (Button) findViewById(R.id.btn_tmp);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String modify_flag = extras.getString("modify");
        if (modify_flag.equals("修改号码")) {
            MODIFY_FLAG = 1;
            tv_modify_tag.setText(modify_flag + ":");
        } else if (modify_flag.equals("修改昵称")) {
            MODIFY_FLAG = 2;
            tv_modify_tag.setText(modify_flag + ":");
        } else if (modify_flag.equals("修改密码")) {
            MODIFY_FLAG = 3;
            lay_modify_pass.setVisibility(View.VISIBLE);
            tv_modify_tag.setText("旧密码:");
            //设置密码输入为暗文格式
            et_phone_nickname_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            et_new_passward.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            et_confirm_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        btn_ok.setVisibility(View.VISIBLE);
        btn_ok.setText("确定");
        //通过intent传过来的信息，来判断这个地方该填写“修改号码”还是“修改昵称”还是修改密码
        tv_title.setText(modify_flag);

        loadData();
    }

    private void loadData() {
        String temp = (String) SharedPrefUtility.getParam(ModifyActivity.this, SharedPrefUtility.LOGIN_DATA, "");
        UserLoginObject userLoginObject = JSON.parseObject(temp, UserLoginObject.class);
        if (!userLoginObject.equals("")) {
            switch (MODIFY_FLAG) {
                case 1:
                    et_phone_nickname_password.setText(userLoginObject.getData().getPhone());
                    break;
                case 2:
                    et_phone_nickname_password.setText(userLoginObject.getData().getNickName());
                    break;
                default:
                    break;
            }
        } else {
            showLoge("没有读取到存储数据");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back_btn://不做任何操作返回
                goBack();
                break;
            case R.id.btn_tmp://如果点击的是确定键
                String phone_or_nickname = et_phone_nickname_password.getText().toString();
                String uri = null;
                pd = new ProgressDialog(ModifyActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setMessage("请稍等。。。");
                pd.show();
                //读取存储的数据，将customID 取出来，这样才能知道修改的是谁的电话号码
                String temp = (String) SharedPrefUtility.getParam(ModifyActivity.this, SharedPrefUtility.LOGIN_DATA, "");
                UserLoginObject object = JSON.parseObject(temp, UserLoginObject.class);
                switch (MODIFY_FLAG) {//判断是修改号码的还是修改昵称的
                    case 1://修改电话号码界面
                        if (CheckEmailPhone.isPhoneNum(phone_or_nickname)) {
                            uri = UriManager.getModifyPhoneUri(object.getData().getCustomerId(), phone_or_nickname);
                            showLogi(uri);
                            getDataFromUri(uri);
                            goBack();
                        }
                        break;
                    case 2://修改昵称界面
                        if (!phone_or_nickname.equals("")) {
                            uri = UriManager.getModifyNickName(object.getData().getCustomerId(), phone_or_nickname);
                            getDataFromUri(uri);
                            goBack();
                        }
                        break;
                    case 3://修改密码界面
                        String oldPassword = et_phone_nickname_password.getText().toString().trim();
                        String newPassword = et_new_passward.getText().toString().trim();
                        String confirmPassword = et_confirm_password.getText().toString().trim();
                        if (!oldPassword.equals("")) {//先判断老密码不为空

                            if (newPassword.equals(confirmPassword)) {//判断新密码和重新输入一直
                                uri = UriManager.getModifyPassword(object.getData().getCustomerId(), oldPassword, newPassword);
                                getStateChangePasswrod(uri);
                            } else {
                                pd.dismiss();
                                showToast("两次输入新密码不一致，请重新输入");
                                et_confirm_password.setText("");
                            }
                        }


                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void getStateChangePasswrod(String uri) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("true")) {
                    //删除存储的登录数据
                    SharedPrefUtility.removeParam(ModifyActivity.this, SharedPrefUtility.LOGIN_DATA);
                    //修改登录状态
                    SharedPrefUtility.setParam(ModifyActivity.this, SharedPrefUtility.IS_LOGIN, false);
                    //跳转到登录界面，重新登录
                    Intent goLogin = new Intent(ModifyActivity.this, MyLoginActivity.class);
                    startActivity(goLogin);
                    ModifyActivity.this.finish();
                } else if (response.contains("false")) {
                    ChangePasswordObject passwordObject = JSON.parseObject(response, ChangePasswordObject.class);
                    showToast(passwordObject.getData());
                    et_phone_nickname_password.setText("");
                    et_new_passward.setText("");
                    et_confirm_password.setText("");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
        App.addRequest(stringRequest, IS_MODIFY_OK);//此处有没有安全隐患还不是很清楚，因为请求不同接口的时候使用了同一个tag
        pd.dismiss();
    }

    private void getDataFromUri(String uri) {

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
                    SharedPrefUtility.setParam(ModifyActivity.this, SharedPrefUtility.LOGIN_DATA, response);
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
