package hy.zc.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.base.BaseActivity;
import hy.zc.wfj.data.OrderDataObject;

public class TemplateActivity extends BaseActivity {

    private ImageButton common_title_back_btn;
    private TextView tv_title;
    private WebView order_web;
    private TextView empty_view;
    private OrderDataObject orderDataObject;
    private String uriString;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        initializeComponent();
        setTitleAndData();
    }

    private void initializeComponent() {
        common_title_back_btn = (ImageButton) findViewById(R.id.common_title_back_btn);
        tv_title = (TextView) findViewById(R.id.common_title_txt);

        order_web = (WebView) findViewById(R.id.order_web);
        empty_view = (TextView) findViewById(R.id.empty_View);

        common_title_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleAndData();
    }

    private void setTitleAndData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orderDataObject = (OrderDataObject) bundle.getSerializable(OrderDataObject.TITLE_KEY);
            if (orderDataObject != null) {
                tv_title.setText(orderDataObject.getTitle());
            }
            uriString = orderDataObject.getUriString();
            if (uriString == null) {
                order_web.setVisibility(View.GONE);
                empty_view.setVisibility(View.VISIBLE);
            } else {
                order_web.setVisibility(View.VISIBLE);
                empty_view.setVisibility(View.GONE);
                order_web.loadUrl(uriString);
            }
        }
    }

    private void goBack() {
        Intent goBack = getIntent();
        setResult(Activity.RESULT_OK, goBack);
        finish();
    }
}
