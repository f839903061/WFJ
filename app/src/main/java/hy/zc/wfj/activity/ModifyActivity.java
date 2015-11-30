package hy.zc.wfj.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class ModifyActivity extends FrameActivity implements View.OnClickListener {

    public static final String IS_MODIFY_OK="isModifyOk";
    private ImageButton imgbtn_back;
    private Button imgbtn_ok;
    private TextView tv_title;
    private TextView tv_modify_tag;
    private EditText et_phone_nickname;
    private ProgressDialog pd;
    private int MODIFY_FLAG=0;

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
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String modify_flag = extras.getString("modify");
        if (modify_flag.equals("修改号码")) {
            showLogi("现在打印一下"+modify_flag);
            MODIFY_FLAG=1;
        }else if (modify_flag.equals("修改昵称")){
            showLogi("现在打印一下"+modify_flag);
            MODIFY_FLAG=2;
        }

        showLogi("modify_flage"+MODIFY_FLAG);
        imgbtn_back = (ImageButton) findViewById(R.id.common_title_back_btn);
        imgbtn_ok = (Button) findViewById(R.id.img_tmp);
        imgbtn_ok.setVisibility(View.VISIBLE);
        imgbtn_ok.setText("确定");
        tv_title = (TextView) findViewById(R.id.common_title_txt);
        tv_modify_tag=(TextView)findViewById(R.id.tv_modify_tag);
        //通过intent传过来的信息，来判断这个地方该填写“修改号码”还是“修改昵称”
        tv_title.setText(modify_flag);
        tv_modify_tag.setText(modify_flag);
        et_phone_nickname = (EditText) findViewById(R.id.et_modify);

        loadData();
    }

    private void loadData(){
        String temp =(String) SharedPrefUtility.getParam(ModifyActivity.this, SharedPrefUtility.LOGIN_DATA, "");
        UserLoginObject userLoginObject = JSON.parseObject(temp, UserLoginObject.class);
        if (!userLoginObject.equals("")){
            switch (MODIFY_FLAG){
                case 1:
                    et_phone_nickname.setText(userLoginObject.getData().getPhone());
                    break;
                case 2:
                    et_phone_nickname.setText(userLoginObject.getData().getNickName());
                    break;
                default:
                    break;
            }
        }else {
            showLoge("读取存储数据，没有数据");
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back_btn://不做任何操作返回
                goBack();
                break;
            case R.id.img_tmp://如果点击的是确定键
                showToast("ok");
                String phone_or_nickname = et_phone_nickname.getText().toString();
                String uri=null;
                pd = new ProgressDialog(ModifyActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setMessage("请稍等。。。");
                pd.show();
                //读取存储的数据，将customID 取出来，这样才能知道修改的是谁的电话号码
                String temp=(String)SharedPrefUtility.getParam(ModifyActivity.this, SharedPrefUtility.LOGIN_DATA, "");
                UserLoginObject object = JSON.parseObject(temp, UserLoginObject.class);
                switch (MODIFY_FLAG){//判断是修改号码的还是修改昵称的
                    case 1:
                        if (CheckEmailMobile.isMobileNO(phone_or_nickname)) {
                            uri=UriManager.getModifyPhoneUri(object.getData().getCustomerId(),phone_or_nickname);
                            showLogi(uri);
                            getDataFromUri(uri);
                            goBack();
                        }
                        break;
                    case 2:
                        if (!phone_or_nickname.equals("")) {
                            uri=UriManager.getMOdifyNickName(object.getData().getCustomerId(),phone_or_nickname);
                            getDataFromUri(uri);
                            goBack();
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
