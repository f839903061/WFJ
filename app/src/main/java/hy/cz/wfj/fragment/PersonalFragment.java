package hy.cz.wfj.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileDescriptor;

import hy.cz.wfj.R;
import hy.cz.wfj.activity.MyLoginActivity;
import hy.cz.wfj.activity.MyMessageActivity;
import hy.cz.wfj.activity.MySettingsActivity;
import hy.cz.wfj.utility.SharedPrefUtility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener {

    public static final int LOGIN_REQUEST_CODE = 1;
    public static final int SETTINGS_REQUEST_CODE = 2;
    public static final int MESSAGE_REQUEST_CODE = 3;
    public static final String TAG = "fengluchun";
    private View rootView;
    private ImageButton avatarImage;
    private ImageButton settingsImage;
    private ImageButton messageImage;

    private LinearLayout concern_goods_layout;
    private LinearLayout concern_shop_layout;
    private RelativeLayout order_layout;
    private LinearLayout service_layout;
    private RelativeLayout wait_for_payment_layout;
    private RelativeLayout wait_sign_in_layout;
    private RelativeLayout wait_comment_layout;
    private RelativeLayout wait_order_after_sale_layout;
//    private TextView concern_browser_tv;

    private OnFragmentInteractionListener mListener;

    private static PersonalFragment personalFragment;
    public static Boolean isLogin = false;

    public static synchronized PersonalFragment getInstance() {
        if (personalFragment == null) {
            personalFragment = new PersonalFragment();
        }
        return personalFragment;
    }

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        initializeComponent();
        setListener();
        return rootView;
    }

    /**
     * initializeComponent
     * 初始化组件
     */
    private void initializeComponent() {
        isLogin = (Boolean) SharedPrefUtility.getParam(getActivity(), "isLogin", false);
        //初始化头像
        avatarImage = (ImageButton) rootView.findViewById(R.id.personal_unlogin_avatar);
        settingsImage = (ImageButton) rootView.findViewById(R.id.personal_setting);
        messageImage = (ImageButton) rootView.findViewById(R.id.personal_common_message);
        //初始化关注
        concern_goods_layout = (LinearLayout) rootView.findViewById(R.id.personal_goods_list_title);
        concern_shop_layout = (LinearLayout) rootView.findViewById(R.id.personal_shop_list_title);
        //初始化订单
        order_layout = (RelativeLayout) rootView.findViewById(R.id.order_layout);
        wait_for_payment_layout = (RelativeLayout) rootView.findViewById(R.id.wait_for_payment_layout);
        wait_sign_in_layout = (RelativeLayout) rootView.findViewById(R.id.wait_sign_in_layout);
        wait_comment_layout = (RelativeLayout) rootView.findViewById(R.id.wait_comment_layout);
        wait_order_after_sale_layout = (RelativeLayout) rootView.findViewById(R.id.wait_order_after_sale_layout);
        //初始化服务
        service_layout = (LinearLayout) rootView.findViewById(R.id.service_layout);

//        concern_browser_tv=(TextView)rootView.findViewById(R.id.personal_browsing_list_title);
    }

    /**
     * set component listener
     * 给组件添加监听
     */
    private void setListener() {
        avatarImage.setOnClickListener(this);
        settingsImage.setOnClickListener(this);
        messageImage.setOnClickListener(this);

        concern_goods_layout.setOnClickListener(this);
        concern_shop_layout.setOnClickListener(this);
        order_layout.setOnClickListener(this);

        wait_for_payment_layout.setOnClickListener(this);
        wait_sign_in_layout.setOnClickListener(this);
        wait_comment_layout.setOnClickListener(this);
        wait_order_after_sale_layout.setOnClickListener(this);

        service_layout.setOnClickListener(this);
//        concern_browser_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.personal_setting) {
            Intent intent_settings = new Intent(getActivity(), MySettingsActivity.class);
            startActivityForResult(intent_settings, SETTINGS_REQUEST_CODE);
        } else {
            //如果未登录则跳转到未登录界面
            if (!isLogin) {
                Intent goLoginIntent = new Intent(getActivity(), MyLoginActivity.class);
                startActivityForResult(goLoginIntent, LOGIN_REQUEST_CODE);
            } else {
                switch (v.getId()) {
                    case R.id.personal_unlogin_avatar://头像
                        myToast("avatar");
                        break;

                    case R.id.personal_common_message://信息
                        Intent intent_message = new Intent(getActivity(), MyMessageActivity.class);
                        startActivityForResult(intent_message, MESSAGE_REQUEST_CODE);
                        break;
                    //concern incident
                    case R.id.personal_goods_list_title://关注商品
                        myToast("goods");
                        break;
                    case R.id.personal_shop_list_title://关注商铺
                        myToast("shop");
                        break;
                    case R.id.order_layout://订单

                        break;
                    case R.id.wait_for_payment_layout://待付款

                        break;
                    case R.id.wait_sign_in_layout://待收货

                        break;
                    case R.id.wait_comment_layout://待评价

                        break;
                    case R.id.wait_order_after_sale_layout://售后

                        break;
                    case R.id.service_layout:

                        break;
//            case R.id.personal_browsing_list_title:
//                myToast("browser");
                    default:
                        break;
                }
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SETTINGS_REQUEST_CODE:

                break;
            case LOGIN_REQUEST_CODE:
                Log.d(TAG, getClass().getName() + "    login");
                break;
            case MESSAGE_REQUEST_CODE:

                break;
            default:
                break;
        }
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

    private void myToast(String text) {
        Toast.makeText(getActivity().getApplicationContext(), "->" + text + "<-", Toast.LENGTH_SHORT).show();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
