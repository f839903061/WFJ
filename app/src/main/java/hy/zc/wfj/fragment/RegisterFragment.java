package hy.zc.wfj.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.activity.MyLoginActivity;
import hy.zc.wfj.activity.TemplateActivity;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.UserLoginErrorObject;
import hy.zc.wfj.utility.CheckEmailPhone;
import hy.zc.wfj.utility.UriManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends FrameFragment {


    public static final String IS_REGISTER = "isRegister";
    private EditText et_phone;
    private EditText et_password;
    private EditText et_confirm_password;
    private EditText et_email;
    private CheckBox checkBox_agree;
    private Button btn_next;
    private TextView tv_protocol;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        initializeComponent(rootView);
        return rootView;
    }

    private void initializeComponent(View view) {
        et_phone = (EditText) view.findViewById(R.id.et_phone);
        et_password=(EditText)view.findViewById(R.id.et_new_password);
        et_confirm_password=(EditText)view.findViewById(R.id.et_confirm_password);
        et_email=(EditText)view.findViewById(R.id.et_email);


        checkBox_agree = (CheckBox) view.findViewById(R.id.checkbox_agree);
        btn_next = (Button) view.findViewById(R.id.btn_second);
        tv_protocol = (TextView) view.findViewById(R.id.tv_protocol);

//        监听是否同意协议
        checkBox_agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    btn_next.setEnabled(false);
                    btn_next.setBackgroundResource(R.drawable.login_btn_background_disable);
                } else {
                    btn_next.setEnabled(true);
                    btn_next.setBackgroundResource(R.drawable.login_btn_background);
                }
            }
        });
//        监听查看协议
        tv_protocol.getPaint().setFakeBoldText(true);
        tv_protocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("跳转到协议，还没做");
                Intent intent=new Intent(getActivity(), TemplateActivity.class);
                Bundle bundle=new Bundle();
                OrderDataObject odo=new OrderDataObject();

                odo.setTitle(OrderDataObject.TITLE_PROTOCOL);
                bundle.putSerializable(OrderDataObject.TITLE_KEY,odo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
//      监听下一步按钮
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = et_phone.getText().toString().trim();
                String passwrod = et_password.getText().toString().trim();
                String confirm_passwrod = et_confirm_password.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                if (phoneNumber.equals("")||passwrod.equals("")||confirm_passwrod.equals("")||email.equals("")) {
                    showToast("带*的都要填写哦！");
                }else {
                    boolean isPhone = CheckEmailPhone.isPhoneNum(phoneNumber);
                    if (isPhone) {//判断手机号
                        if (passwrod.equals(confirm_passwrod)) {//判断密码
                            boolean isEmail = CheckEmailPhone.isEmail(email);
                            if (isEmail) {//判断邮箱
                                String uri = UriManager.getRegister(email, phoneNumber, passwrod);
                                getDataFromUri(uri);
                            }else {
                                showToast("邮箱格式不正确哦!");
                            }
                        }else {
                            showToast("两次输入的密码不一致!");
                        }
                    }else {
                        showToast("手机号码格式不正确");
                    }

                }

            }
        });

    }

    /**
     * 通过连接获取数据
     * @param uri
     */
    private void getDataFromUri(String uri) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UserLoginErrorObject errorObject = JSON.parseObject(response, UserLoginErrorObject.class);
                if (response.contains("false")){
                    showToast(errorObject.getData().toString());
                }else {
                    showToast(errorObject.getData().toString());
                    goToActivity(MyLoginActivity.class,null);
                    getActivity().finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge(error.getMessage());
            }
        });
        App.addRequest(stringRequest, IS_REGISTER);
    }

    @Override
    public void onStop() {
        App.cancelAllRequests(IS_REGISTER);
        super.onStop();
    }
}
