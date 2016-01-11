package hy.zc.wfj.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import hy.zc.wfj.R;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class DetailActivity extends FrameActivity {

    public static final String PRODUCTID = "productid";

    //    title
    private ImageButton imgb_back;
    private TextView tv_title;

    private WebView web_detial;
    private int productid;


    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private static final String APP_DB_DIRNAME = "/webdb";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initializeComponent();
    }

    private void initializeComponent() {

        initializeTitle();

        web_detial=(WebView)findViewById(R.id.web_detial);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        productid = extras.getInt(PRODUCTID);

        String temp = (String) SharedPrefUtility.getParam(DetailActivity.this, SharedPrefUtility.LOGIN_DATA, "");
        String uri=null;
        if (!temp.equals("")) {
            UserLoginObject object = JSON.parseObject(temp, UserLoginObject.class);
            int customerId = object.getData().getCustomerId();
             uri= UriManager.getCommodityDetialUri(productid, customerId);

        }else {
            uri = UriManager.getCommodityDetialUri(productid,-1);
        }
        setWebViewCache();
        setWebViewListener();
        web_detial.loadUrl(uri);
    }

    /**
     * 凡是这种格式的标头，都可以使用下面这种依葫芦画瓢哦
     */
    private void initializeTitle() {
        imgb_back=(ImageButton)findViewById(R.id.common_title_back_btn);
        tv_title=(TextView)findViewById(R.id.common_title_txt);
        tv_title.setText("商品详情");



        imgb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }


    /**
     * set webview cache
     * 给webview设置缓存机制
     */
    private void setWebViewCache() {
        WebSettings webSettings = web_detial.getSettings();
        //support js
        webSettings.setJavaScriptEnabled(true);
        //support webview cached
        //有网络的情况下走网络，没网的情况下走缓存 参考：http://my.oschina.net/mycbb/blog/330422
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //open DOM api
        webSettings.setDomStorageEnabled(true);
        //open database api
        webSettings.setDatabaseEnabled(true);
        String cacheDirPath =getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        String dbDirPath = getCacheDir().getAbsolutePath() + APP_DB_DIRNAME;

        webSettings.setDatabasePath(dbDirPath);
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setAppCacheEnabled(true);
    }

    /**
     * webview上的操作监听，包含了跳转链接监听，返回键监听
     */
    private void setWebViewListener() {
        web_detial.setWebViewClient(new WebViewClient() {
            /**
             * if reture false will use application webview ,otherwise use android device browser
             * 如果返回false 将使用webview本身进行跳转，否则会跳转到系统的浏览器打开链接
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                showLogi(url);
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//解决web加载https连接时显示的空白页
            }
        });
        web_detial.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //如果用户的操作是返回，同时webview又可以返回，那么就执行网页返回操作
                if (keyCode == KeyEvent.KEYCODE_BACK && web_detial.canGoBack()) {
                    web_detial.goBack();
                    return true;
                }
                return false;
            }
        });
    }
}
