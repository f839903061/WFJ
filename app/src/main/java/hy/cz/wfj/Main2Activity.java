package hy.cz.wfj;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import hy.cz.wfj.fragment.CartFragment;
import hy.cz.wfj.fragment.CategoryFragment;
import hy.cz.wfj.fragment.HomeFragment;
import hy.cz.wfj.fragment.PersonalFragment;

public class Main2Activity extends Activity implements
        HomeFragment.OnFragmentInteractionListener,
        CategoryFragment.OnFragmentInteractionListener,
        CartFragment.OnFragmentInteractionListener,
        PersonalFragment.OnFragmentInteractionListener {

    public static final int FRAGMENT_HOME = 1;
    public static final int FRAGMENT_CATEGORY = 2;
    public static final int FRAGMENT_CART = 3;
    public static final int FRAGMENT_PERSONAL = 4;

    public static final String HOME_FRAGMENT_TAG = "home_fragment";
    public static final String CATEGORY_FRAGMENT_TAG = "category_fragment";
    public static final String CART_FRAGMENT_TAG = "cart_fragment";
    public static final String PERSONAL_FRAGMENT_TAG = "personal_fragment";

    private FrameLayout mFrameLayout;
    private RadioGroup mRadioGroup;

//    private RadioButton mRadioButton_home;
//    private RadioButton mRadioButton_category;
//    private RadioButton mRadioButton_cart;
//    private RadioButton mRadioButton_personal;
    private  FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initializeComponent();
    }

    /**
     * 初始化组件以及监听事件
     */
    private void initializeComponent() {
        mFrameLayout = (FrameLayout) findViewById(R.id.main_framelayout);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
//        mRadioButton_home = (RadioButton) findViewById(R.id.nav_home_btn);
//        mRadioButton_category = (RadioButton) findViewById(R.id.nav_cart_btn);
//        mRadioButton_cart = (RadioButton) findViewById(R.id.nav_cart_btn);
//        mRadioButton_personal = (RadioButton) findViewById(R.id.nav_personal_btn);

        //获取fragment处理器
        fragmentTransaction= getFragmentManager().beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.add(R.id.main_framelayout,homeFragment,"home_fragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //监听导航按钮
        setListener();
    }

    /**
     * 设计导航栏四键的事件监听
     */
    private void setListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                fragmentTransaction=getFragmentManager().beginTransaction();

                switch (checkedId) {
                    case FRAGMENT_HOME:
                        fragmentTransaction.replace(R.id.main_framelayout,HomeFragment.getInstance(), HOME_FRAGMENT_TAG);
                        break;
                    case FRAGMENT_CATEGORY:
                        fragmentTransaction.replace(R.id.main_framelayout, CategoryFragment.getInstance(), CATEGORY_FRAGMENT_TAG);
                        break;
                    case FRAGMENT_CART:
                        fragmentTransaction.replace(R.id.main_framelayout, CartFragment.getInstance(), CART_FRAGMENT_TAG);
                        break;
                    case FRAGMENT_PERSONAL:
                        fragmentTransaction.replace(R.id.main_framelayout, PersonalFragment.getInstance(), PERSONAL_FRAGMENT_TAG);
                        break;
                }
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void myToast(String text) {
        Toast.makeText(getApplicationContext(), "-->" + text + "<--", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
