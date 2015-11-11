package hy.cz.wfj;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

import hy.cz.wfj.fragment.CartFragment;
import hy.cz.wfj.fragment.CategoryFragment;
import hy.cz.wfj.fragment.HomeFragment;
import hy.cz.wfj.fragment.PersonalFragment;
import hy.cz.wfj.utility.SharedPrefUtility;

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

    private RadioGroup mRadioGroup;

    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_main2);
        initializeComponent();
        //set listener for radiogroup
        setListener();
    }


    /**
     * 在恢复activity的时候，读取之前存储的radiobutton的id，然后设置
     * 恢复现场
     */
    @Override
    protected void onResume() {
        super.onResume();
        setRadioGroupCheck();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //when application setup ,set default display HomeFragment
        setRadioGroupCheck();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    /**
     * initialize componet and transcation navigation tasks
     * 初始化组件，以及导航事务
     */
    private void initializeComponent() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
    }

    /**
     * set current radiobutton checkout id
     * 根据保存的index，设置该radiobutton为被点击状态
     * 因为点击了会触发setOnCheckedChangeListener
     * 这样就切换了选项卡
     */
    private void setRadioGroupCheck(){
        Object index = SharedPrefUtility.getParam(getApplicationContext(), "index", 1);
        mRadioGroup.check((int)index);
    }

    /**
     * set radiogroup listener
     * 设置radiogroup的点击监听事件
     */
    private void setListener() {
        //set default checked is 1-->home
//        mRadioGroup.check(mRadioGroup.getChildCount() - (mRadioGroup.getChildCount() - RADIOGROUP_INDEX));

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * @param group
             *         you can use group.getCheckedRadioButtonId() to get current index e.g.(1,2,3,4...)
             *         你可以通过group.getCheckedRadioButtonId() 来获取点击的radiobutton的index，
             *         其实就是下面的checkedId
             * @param checkedId
             *          get current index e.g.(1,2,3,4....)
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //get fragmentTransaction again to deal with second commit result in IllegalStateException: commit already called
                fragmentTransaction = getFragmentManager().beginTransaction();

                //getInstance of Fragment
//                HomeFragment homeFragment = HomeFragment.getInstance();
//                CategoryFragment categoryFragment = CategoryFragment.getInstance();
//                CartFragment cartFragment = CartFragment.getInstance();
//                PersonalFragment personalFragment = PersonalFragment.getInstance();

                switch (checkedId) {
                    case FRAGMENT_HOME:
                        fragmentTransaction.replace(R.id.main_framelayout, HomeFragment.newInstance(), HOME_FRAGMENT_TAG);
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
               /* 每次点击radiobutton之后，就更新保存一下索引，
                 为了解决从别的activity中跳转回来之后，总是radiogroup第一项被选中这个bug*/
                SharedPrefUtility.setParam(getApplicationContext(), "index", checkedId);
                /*before you commit second , you must get ft instance again otherwise you will get illegalexception
                这里需要注意的是每次commit时你的fragmentTransaction都是重新获取的，
                不能连续使用同一fragmentTransaction对象执行两次commit操作，否则会遇到语法异常错误*/
                fragmentTransaction.commit();
            }
        });
    }

        /**
     * interaction with fragment implement interface function
     *
     * @param uri form fragment transfer date to here
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PersonalFragment.LOGIN_REQUEST_CODE) {
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*deal with every time set up can't change fragment
        * 当我的程序是通过按BACK键正常退出的时候，此时设置
        * 下次启动之后默认radiogroup选中第一项，在onStrat中进行的调用*/
        SharedPrefUtility.setParam(getApplicationContext(),"index",FRAGMENT_HOME);
        //下面这个为了解决下次打开应用程序只有下面的导航栏切换，但是选项卡不切合的bug
        System.exit(0);
    }
}
