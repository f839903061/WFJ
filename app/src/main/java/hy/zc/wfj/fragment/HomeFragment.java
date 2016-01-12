package hy.zc.wfj.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.CaptureActivity;
import hy.zc.wfj.activity.MyLoginActivity;
import hy.zc.wfj.activity.MyMessageActivity;
import hy.zc.wfj.activity.SearchActivity;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class HomeFragment extends FrameFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private static final String APP_DB_DIRNAME = "/webdb";
    public static final int LOGIN_REQUEST_CODE = 1;
    public static final int MESSAGE_REQUEST_CODE = 3;
    public static final int SCAN_REQUEST_CODE = 4;
    public static final int SEARCH_REQUEST_CODE = 5;

    private static HomeFragment homeFragment = null;

    private View rootView;
    private WebView mWebView;
    private RelativeLayout to_message_btn;
    private LinearLayout home_search_button;
    private RelativeLayout layout_title_back;
    private ImageButton btn_back;
    private TextView tv_title;
    private RelativeLayout layout_title_search;
    private Boolean islogin = false;
    private AutoCompleteTextView homeActivity_autoComplete;
//    private OnFragmentInteractionListener mListener;
    private OnFragmentInteractiveListener mListener;

    public static synchronized HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static HomeFragment newInstance() {
        homeFragment = new HomeFragment();
        return homeFragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeComponent();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.reload();
    }

    private void initializeComponent() {
        layout_title_back=(RelativeLayout)rootView.findViewById(R.id.layout_title_back);
        layout_title_search=(RelativeLayout)rootView.findViewById(R.id.layout_title_search);

        btn_back=(ImageButton)layout_title_back.findViewById(R.id.common_title_back_btn);
        tv_title=(TextView)layout_title_back.findViewById(R.id.common_title_txt);
        btn_back.setOnClickListener(this);
        tv_title.setVisibility(View.GONE);

        to_message_btn=(RelativeLayout)rootView.findViewById(R.id.to_message_btn);
        homeActivity_autoComplete=(AutoCompleteTextView)rootView.findViewById(R.id.homeActivity_autoComplete);

        home_search_button=(LinearLayout)rootView.findViewById(R.id.home_search_button);

        to_message_btn.setOnClickListener(this);
        home_search_button.setOnClickListener(this);
        homeActivity_autoComplete.setOnClickListener(this);

        mWebView = (WebView) rootView.findViewById(R.id.home_webView);
//        mWebView.loadUrl("http://www.baidu.com");
//        mWebView.loadUrl("file:///android_asset/jd/index.html");
        //set webview cache
        setWebViewCache();

        setWebViewListener();
        mWebView.loadUrl(UriManager.getHomeUri(-1));
    }

    /**
     * webview上的操作监听，包含了跳转链接监听，返回键监听
     */
    private void setWebViewListener() {
        mWebView.setWebViewClient(new WebViewClient() {
            /**
             * if reture false will use application webview ,otherwise use android device browser
             * 如果返回false 将使用webview本身进行跳转，否则会跳转到系统的浏览器打开链接
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                if (!url.contains("gotoPhoneHomePage")) {
                    layout_title_search.setVisibility(View.GONE);
                    layout_title_back.setVisibility(View.VISIBLE);
                    mListener.homeDo(true);
                }
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//把这个注释打开就能解决web加载https连接时显示的空白页
            }
        });
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //如果用户的操作是返回，同时webview又可以返回，那么就执行网页返回操作
                if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                    mWebView.goBack();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * set webview cache
     * 给webview设置缓存机制
     */
    private void setWebViewCache() {
        WebSettings webSettings = mWebView.getSettings();
        //support js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);//
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setDefaultFontSize(16);
        //support webview cached
        //有网络的情况下走网络，没网的情况下走缓存 参考：http://my.oschina.net/mycbb/blog/330422
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //open DOM api
        webSettings.setDomStorageEnabled(true);
        //open database api
        webSettings.setDatabaseEnabled(true);
        String cacheDirPath = getActivity().getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        String dbDirPath = getActivity().getCacheDir().getAbsolutePath() + APP_DB_DIRNAME;

        webSettings.setDatabasePath(dbDirPath);
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setAppCacheEnabled(true);
    }

    public void clearWebViewCache() {

        //清理Webview缓存数据库
        try {
            getActivity().deleteDatabase("webview.db");
            getActivity().deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //WebView 缓存文件
        File appCacheDir = new File(getActivity().getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);
        Log.i(TAG, "appCacheDir path=" + appCacheDir.getAbsolutePath());
        File webviewCacheDir = new File(getActivity().getCacheDir().getAbsolutePath() + "/webviewCache");
        Log.i(TAG, "webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());
        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            getActivity().deleteFile(webviewCacheDir.getName());
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            getActivity().deleteFile(appCacheDir.getName());
        }
    }


    @Override
    public void onAttach(Activity activity) {
//        Fresco.initialize(getActivity().getApplicationContext());
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractiveListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_message_btn://跳转到消息界面
                islogin = (Boolean) SharedPrefUtility.getParam(getActivity(), SharedPrefUtility.IS_LOGIN, false);
                if (!islogin) {
                    Intent goLoginIntent = new Intent(getActivity(), MyLoginActivity.class);
                    startActivityForResult(goLoginIntent, LOGIN_REQUEST_CODE);
                } else {
                    Intent goMsgIntent=new Intent(getActivity(), MyMessageActivity.class);
                    startActivityForResult(goMsgIntent,MESSAGE_REQUEST_CODE);
                }
                break;
            case R.id.homeActivity_autoComplete:
                Intent goSearch=new Intent(getActivity(), SearchActivity.class);
                startActivityForResult(goSearch, SEARCH_REQUEST_CODE);
                break;
            case R.id.home_search_button://跳转到二维码扫描界面
                Intent goScan=new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(goScan, SCAN_REQUEST_CODE);
                break;
            case R.id.common_title_back_btn:
                layout_title_search.setVisibility(View.VISIBLE);
                layout_title_back.setVisibility(View.GONE);
                mListener.homeDo(false);
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                }
                break;
            default:
                break;
        }

    }

}
