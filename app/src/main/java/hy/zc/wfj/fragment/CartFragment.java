package hy.zc.wfj.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import hy.zc.wfj.R;
import hy.zc.wfj.utility.UriManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CartFragment extends FrameFragment {

    private OnFragmentInteractionListener mListener;
    private WebView web_cart;

    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private static final String APP_DB_DIRNAME = "/webdb";


    private static CartFragment cartFragment;

    public static synchronized CartFragment getInstance() {
        if (cartFragment == null) {
            cartFragment = new CartFragment();
        }
        return cartFragment;
    }

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        initializeComponent(rootView);
        return rootView;
    }

    private void initializeComponent(View rootView) {
        web_cart = (WebView) rootView.findViewById(R.id.web_cart);

        WebSettings settings = web_cart.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        settings.setDomStorageEnabled(true);
//        settings.setDatabaseEnabled(true);
//        String cacheDirPath = getActivity().getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
//        String dbDirPath = getActivity().getCacheDir().getAbsolutePath() + APP_DB_DIRNAME;
//
//        settings.setDatabasePath(dbDirPath);
//        settings.setAppCachePath(cacheDirPath);
//        settings.setAppCacheEnabled(true);


        setWebViewListener();
        web_cart.loadUrl("https://192.168.10.7:8443/wfj_front/phone//gotoPhoneCart.action?customerId=459");
    }

    /**
     * webview上的操作监听，包含了跳转链接监听，返回键监听
     */
    private void setWebViewListener() {
        web_cart.setWebViewClient(new WebViewClient() {
            /**
             * if reture false will use application webview ,otherwise use android device browser
             * 如果返回false 将使用webview本身进行跳转，否则会跳转到系统的浏览器打开链接
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//解决web加载https连接时显示的空白页
            }
        });
        web_cart.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //如果用户的操作是返回，同时webview又可以返回，那么就执行网页返回操作
                if (keyCode == KeyEvent.KEYCODE_BACK && web_cart.canGoBack()) {
                    web_cart.goBack();
                    return true;
                }
                return false;
            }
        });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        //
        public void onFragmentInteraction(Uri uri);
    }

}
