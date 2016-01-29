package hy.zc.wfj.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.base.BaseActivity;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.CustomObjectUtil;
import hy.zc.wfj.utility.UriManager;

public class AddressActivity extends FrameActivity implements View.OnClickListener {

    private ImageButton imgb_back;
    private TextView tv_title;
    private RelativeLayout layout_title;
    private WebView webView_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initiallizeComponent();
    }

    private void initiallizeComponent() {
        //entire layout init
        layout_title = (RelativeLayout) findViewById(R.id.include_title);
        webView_address = (WebView) findViewById(R.id.web_detial);

        //title laytou init component
        imgb_back = (ImageButton) layout_title.findViewById(R.id.common_title_back_btn);
        imgb_back.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserLoginObject userLoginObject = CustomObjectUtil.getDataFromLocalSharedPre(AddressActivity.this);
        if (userLoginObject != null) {
            int customerId = userLoginObject.getData().getCustomerId();
            String address = UriManager.getAddress(customerId);
            WebSettings settings = webView_address.getSettings();
            settings.setJavaScriptEnabled(true);
            webView_address.loadUrl(address);
            webView_address.setWebChromeClient(new WebChromeClient());
            webView_address.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();
                }
            });
            webView_address.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK&&webView_address.canGoBack()) {
                        webView_address.goBack();
                        return true;
                    }
                    return false;
                }
            });

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back_btn:
                goBack();
                break;
        }
    }
}
