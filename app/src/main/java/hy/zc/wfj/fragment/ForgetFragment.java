package hy.zc.wfj.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.data.UserLoginErrorObject;
import hy.zc.wfj.utility.CheckEmailPhone;
import hy.zc.wfj.utility.UriManager;


public class ForgetFragment extends FrameFragment {

    public static final String IS_SEND = "isSend";
    private EditText et_email;
    private Button btn_send;

    public ForgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_forget, container, false);
        initializeComponent(rootView);
        return rootView;
    }

    public void initializeComponent(View rootView){
        et_email=(EditText)rootView.findViewById(R.id.et_email);
        btn_send=(Button)rootView.findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                boolean isEmail = CheckEmailPhone.isEmail(email);
                if (isEmail) {
                    String uri = UriManager.getForgetPasswrod(email);
                    sendEmailToService(uri);
                }else {
                    showToast("邮箱格式不正确！");
                }
            }
        });
    }

    private void sendEmailToService(String uri) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UserLoginErrorObject object = JSON.parseObject(response, UserLoginErrorObject.class);
                String message = object.getData();
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage(message);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge(error.getMessage());
            }
        });

        App.addRequest(stringRequest, IS_SEND);
    }

    @Override
    public void onStop() {
        App.cancelAllRequests(IS_SEND);
        super.onStop();
    }
}
