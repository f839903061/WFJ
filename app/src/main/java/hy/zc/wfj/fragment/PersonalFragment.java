package hy.zc.wfj.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.ConcernActivity;
import hy.zc.wfj.activity.MyLoginActivity;
import hy.zc.wfj.activity.MyMessageActivity;
import hy.zc.wfj.activity.MySettingsActivity;
import hy.zc.wfj.activity.PersonalInfoActivity;
import hy.zc.wfj.activity.ServerActivity;
import hy.zc.wfj.activity.TemplateActivity;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PersonalFragment extends FrameFragment implements View.OnClickListener {

    public static final int LOGIN_REQUEST_CODE = 1;
    public static final int SETTINGS_REQUEST_CODE = 2;
    public static final int MESSAGE_REQUEST_CODE = 3;
    public static final int PERSONAL_INFO_REQUEST_CODE = 4;

    private View rootView;
    private TextView nick_name;
    private TextView user_level;
    private ImageButton avatarImage;
    private ImageButton settingsImage;
    private ImageButton messageImage;
    private SimpleDraweeView user_img_view;

    private LinearLayout concern_goods_layout;
    private LinearLayout concern_shop_layout;
    private LinearLayout service_layout;
    private RelativeLayout order_layout;
    private RelativeLayout wait_for_payment_layout;
    private RelativeLayout wait_sign_in_layout;
    private RelativeLayout wait_comment_layout;
    private RelativeLayout wait_order_after_sale_layout;
    private RelativeLayout personal_for_logout_info_layout;//登出
    private RelativeLayout personal_for_login_info_layout;//登录

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
        //切换登录/登出布局文件
        changeLoginLayout();
        setListener();
        return rootView;
    }

    /**
     * initializeComponent
     * 初始化组件
     */
    private void initializeComponent() {
        personal_for_logout_info_layout = (RelativeLayout) rootView.findViewById(R.id.personal_for_logout_info_layout);
        personal_for_login_info_layout = (RelativeLayout) rootView.findViewById(R.id.personal_for_login_info_layout);

        //初始化头像
        avatarImage = (ImageButton) rootView.findViewById(R.id.personal_unlogin_avatar);
        settingsImage = (ImageButton) rootView.findViewById(R.id.personal_setting);
        messageImage = (ImageButton) rootView.findViewById(R.id.personal_common_message);
        user_img_view = (SimpleDraweeView) rootView.findViewById(R.id.user_img_view);
        //名称
        nick_name = (TextView) rootView.findViewById(R.id.who_and_say_hello);
        user_level = (TextView) rootView.findViewById(R.id.user_level);

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

        personal_for_login_info_layout.setOnClickListener(this);
        user_img_view.setOnClickListener(this);
//        concern_browser_tv.setOnClickListener(this);
    }

    /**
     * 根据登录情况，进行切换用户信息的布局
     */
    private void changeLoginLayout() {
        isLogin = (Boolean) SharedPrefUtility.getParam(getActivity(), SharedPrefUtility.IS_LOGIN, false);
        if (isLogin) {
            personal_for_login_info_layout.setVisibility(View.VISIBLE);
            personal_for_logout_info_layout.setVisibility(View.GONE);
        } else {
            personal_for_login_info_layout.setVisibility(View.GONE);
            personal_for_logout_info_layout.setVisibility(View.VISIBLE);
        }
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
                Bundle bundle = new Bundle();
                switch (v.getId()) {
                    case R.id.personal_common_message://信息
                        goToActivityForResult(MyMessageActivity.class, MESSAGE_REQUEST_CODE, null);
                        break;
                    //concern incident
                    case R.id.personal_goods_list_title://关注商品
                        bundle.putInt(ConcernActivity.TYPE,ConcernActivity.COMMODITY);
                        goToActivity(ConcernActivity.class, bundle);
                        break;
                    case R.id.personal_shop_list_title://关注商铺
                        bundle.putInt(ConcernActivity.TYPE,ConcernActivity.SHOP);
                        goToActivity(ConcernActivity.class, bundle);
                        break;
                    case R.id.order_layout://订单
                        bundle = setOrderData(OrderDataObject.TITLE_ALL, OrderDataObject.TITLE_KEY);
                        goToActivity(TemplateActivity.class, bundle);
                        break;
                    case R.id.wait_for_payment_layout://跳转到待付款，并携带数据过去
                        bundle = setOrderData(OrderDataObject.TITLE_WAIT_PAY, OrderDataObject.TITLE_KEY);
                        goToActivity(TemplateActivity.class, bundle);
                        break;
                    case R.id.wait_sign_in_layout://跳转到待收货，并携带数据过去
                        bundle = setOrderData(OrderDataObject.TITLE_SIGN, OrderDataObject.TITLE_KEY);
                        goToActivity(TemplateActivity.class, bundle);
                        break;
                    case R.id.wait_comment_layout://跳转到待评价，并携带数据过去
                        bundle = setOrderData(OrderDataObject.TITLE_COMMENT, OrderDataObject.TITLE_KEY);
                        goToActivity(TemplateActivity.class, bundle);
                        break;
                    case R.id.wait_order_after_sale_layout://跳转到售后，并携带数据过去
                        bundle = setOrderData(OrderDataObject.TITLE_AFTER_SALE, OrderDataObject.TITLE_KEY);
                        goToActivity(TemplateActivity.class, bundle);
                        break;
                    case R.id.service_layout://跳转到服务界面
                        goToActivity(ServerActivity.class, bundle);
                        break;
                    case R.id.personal_for_login_info_layout://点击头像所在的layout
                        goToActivityForResult(PersonalInfoActivity.class, PERSONAL_INFO_REQUEST_CODE, bundle);
                        break;
                    case R.id.user_img_view://点击头像
                        goToActivityForResult(PersonalInfoActivity.class, PERSONAL_INFO_REQUEST_CODE, bundle);
                        break;
//            case R.id.personal_browsing_list_title:
//                myToast("browser");
                    default:
                        break;
                }
            }
        }

    }

    /**
     * 跳转订单时  封装传输的数据
     *
     * @param ptitle
     * @param pkey
     * @return
     */
    private Bundle setOrderData(String ptitle, String pkey) {
        OrderDataObject odo = new OrderDataObject();
        odo.setTitle(ptitle);
        Bundle bundle = new Bundle();
        bundle.putSerializable(pkey, odo);
        return bundle;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SETTINGS_REQUEST_CODE:

                break;
            case LOGIN_REQUEST_CODE://登录界面返回，保存数据并呈现出来
                if (resultCode == Activity.RESULT_OK) {
                    adapterData();
                }
                break;
            case MESSAGE_REQUEST_CODE:

                break;
            case PERSONAL_INFO_REQUEST_CODE:


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
    public void onResume() {
        super.onResume();
//       根据登录情况，判断显示哪个布局文件
        changeLoginLayout();
//       更新界面显示
        adapterData();
    }

    /**
     * 通过接受过来的数据给组件赋值，头像，昵称，级别等等
     */
    private void adapterData() {
        if (isLogin) {
            String temp = (String) SharedPrefUtility.getParam(getActivity(), SharedPrefUtility.LOGIN_DATA, "");
            UserLoginObject object = (UserLoginObject) JSON.parseObject(temp.trim(), UserLoginObject.class);
//            StringBuilder stringBuilder = new StringBuilder("http://101.200.182.119:8080/phone");
//            stringBuilder.append(object.getData().getPhotoUrl());
//            showLogi(stringBuilder.toString());
            Uri uri = UriManager.getLoginAvatarUri(object.getData().getPhotoUrl());
            user_img_view.setImageURI(uri);
            nick_name.setText(object.getData().getLoginName());
            user_level.setText(object.getData().getNickName());
        } else {
            showLoge("未登录");
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
