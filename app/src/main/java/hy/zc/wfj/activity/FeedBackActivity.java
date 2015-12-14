package hy.zc.wfj.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.CheckEmailPhone;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class FeedBackActivity extends FrameActivity implements View.OnClickListener {

    public static final String IS_FEED_BACK = "isFeedBack";
    private LinearLayout layout_title;
    private LinearLayout layout_choice;

    private ImageButton imgb_back;
    private ImageView imgv_icon;
    private Button btn_commit;

    private TextView tv_title;
    private TextView tv_name;
    private TextView tv_hint;

    private EditText et_feedback;
    private EditText et_feedback_contact;
    private TextView tv_feedback_count;

    private static int feedType = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        initializeComponent();
    }

    private void initializeComponent() {
        layout_title = (LinearLayout) findViewById(R.id.include_title);
        layout_choice = (LinearLayout) findViewById(R.id.include_choice);

        //----------------title--------
        imgb_back = (ImageButton) layout_title.findViewById(R.id.common_title_back_btn);
        btn_commit = (Button) layout_title.findViewById(R.id.btn_tmp);
        tv_title = (TextView) layout_title.findViewById(R.id.common_title_txt);

        tv_title.setText("意见反馈");
        btn_commit.setVisibility(View.VISIBLE);
        btn_commit.setText("提交");

        //----------------choice-------
        tv_name = (TextView) layout_choice.findViewById(R.id.tv_commodity_name);
        tv_hint = (TextView) layout_choice.findViewById(R.id.tv_hint);
        imgv_icon = (ImageView) layout_choice.findViewById(R.id.imgv_icon);

        imgv_icon.setVisibility(View.GONE);
        tv_name.setText("反馈类型：");
        tv_hint.setText("功能意见");

        //----------------输入框
        et_feedback = (EditText) findViewById(R.id.et_feedback);
        et_feedback_contact = (EditText) findViewById(R.id.et_feedback_contact);
        tv_feedback_count = (TextView) findViewById(R.id.tv_feedback_count);
        et_feedback.addTextChangedListener(new FeedBackTextWatch());

        layout_choice.setOnClickListener(this);
        imgb_back.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    /**
     * 这个类的作用就是监听输入框的字数长度，同步刷新显示个数
     */
    private class FeedBackTextWatch implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv_feedback_count.setText(s.toString().length() + "/100");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_DEL) {
            et_feedback.setText("");
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back_btn:
                goBack();
                break;
            case R.id.btn_tmp://提交按钮
                String fb_text = et_feedback.getText().toString().trim();
                String contact_text = et_feedback_contact.getText().toString().trim();
                if (fb_text.equals("")) {
                    showToast("未填写反馈意见");
                    break;
                }
                String temp = (String) SharedPrefUtility.getParam(FeedBackActivity.this, SharedPrefUtility.LOGIN_DATA, "");
                if (!temp.equals("")) {//判断当前用户数据不为空，这样才能拿到用户Id
                    UserLoginObject object = JSON.parseObject(temp, UserLoginObject.class);

                    int customerId = object.getData().getCustomerId();

                    String uri = null;

                    if (contact_text.equals("")) {//判断联系框  是否有数据
                        uri = UriManager.getFeedback(customerId, null, null, feedType, fb_text);
                    } else {
                        if (CheckEmailPhone.isPhoneNum(contact_text)) {
                            uri = UriManager.getFeedback(customerId, null, contact_text, feedType, fb_text);
                        }
                        if (CheckEmailPhone.isEmail(contact_text)) {
                            uri = UriManager.getFeedback(customerId, contact_text, null, feedType, fb_text);
                        }
                    }
                    getDataFromUri(uri);
                }
                break;
            case R.id.include_choice://点击反馈类型框
                final String[] stringArray = getResources().getStringArray(R.array.feedtype);

                AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("反馈类型:");
                builder.setItems(R.array.feedtype, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        feedType = which + 1;
                        tv_hint.setText(stringArray[which]);
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            default:
                break;
        }
    }

    /**
     * 如果反馈成功，给与提示
     * @param uri
     */
    private void getDataFromUri(String uri) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showToast("反馈成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge(error.getMessage());
            }
        });
        App.addRequest(stringRequest, IS_FEED_BACK);
    }

    /**
     * 取消请求
     */
    @Override
    protected void onStop() {
        App.cancelAllRequests(IS_FEED_BACK);
        super.onStop();
    }
}
