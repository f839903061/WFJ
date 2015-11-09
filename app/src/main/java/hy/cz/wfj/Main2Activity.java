package hy.cz.wfj;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import hy.cz.wfj.fragment.CartFragment;
import hy.cz.wfj.fragment.CategoryFragment;
import hy.cz.wfj.fragment.HomeFragment;
import hy.cz.wfj.fragment.PersonalFragment;
import hy.cz.wfj.utility.MyLogToast;
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
    public static final String TAG = "fengluchun";

    private RadioGroup mRadioGroup;
    private MyLogToast myLogToast;

    //    private RadioButton mRadioButton_home;
//    private RadioButton mRadioButton_category;
//    private RadioButton mRadioButton_cart;
//    private RadioButton mRadioButton_personal;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_main2);
        initializeComponent();
    }



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
     */
    private void initializeComponent() {
        myLogToast =new MyLogToast(Main2Activity.this);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
//        mRadioButton_home = (RadioButton) findViewById(R.id.nav_home_btn);
//        mRadioButton_category = (RadioButton) findViewById(R.id.nav_cart_btn);
//        mRadioButton_cart = (RadioButton) findViewById(R.id.nav_cart_btn);
//        mRadioButton_personal = (RadioButton) findViewById(R.id.nav_personal_btn);


        //set listener for radiogroup
        setListener();
    }

    /**
     * set current radiobutton checkout id
     */
    private void setRadioGroupCheck(){
        Object index = SharedPrefUtility.getParam(getApplicationContext(), "index", 1);
        mRadioGroup.check(mRadioGroup.getChildCount() - (mRadioGroup.getChildCount() - (int)index));
    }

    /**
     * set radiogroup listener
     */
    private void setListener() {
        //set default checked is 1-->home
//        mRadioGroup.check(mRadioGroup.getChildCount() - (mRadioGroup.getChildCount() - RADIOGROUP_INDEX));

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * @param group
             *         you can use group.getCheckedRadioButtonId() to get current index e.g.(1,2,3,4...)
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
                        SharedPrefUtility.setParam(getApplicationContext(),"index",FRAGMENT_HOME);
                        break;
                    case FRAGMENT_CATEGORY:
                        fragmentTransaction.replace(R.id.main_framelayout, CategoryFragment.getInstance(), CATEGORY_FRAGMENT_TAG);
                        SharedPrefUtility.setParam(getApplicationContext(), "index", FRAGMENT_CATEGORY);
                        break;
                    case FRAGMENT_CART:
                        fragmentTransaction.replace(R.id.main_framelayout, CartFragment.getInstance(), CART_FRAGMENT_TAG);
                        SharedPrefUtility.setParam(getApplicationContext(), "index", FRAGMENT_CART);
                        break;
                    case FRAGMENT_PERSONAL:
                        fragmentTransaction.replace(R.id.main_framelayout, PersonalFragment.getInstance(), PERSONAL_FRAGMENT_TAG);
                        SharedPrefUtility.setParam(getApplicationContext(), "index", FRAGMENT_PERSONAL);
                        break;
                }
                //when user pressed BACK  will go to previous fragment,but if you changed more times fragment,
                // you must pressed more times BACK key,so you must consist whether to use
//                fragmentTransaction.addToBackStack(null);
                //before you commit second , you must get ft instance again otherwise you will get illegalexception
                fragmentTransaction.commit();
            }
        });
    }


    /**
     * myself Toast encapsulation
     *
     * @param text toast text
     */
    private void myToast(String text) {
        Toast.makeText(getApplicationContext(), "-->" + text + "<--", Toast.LENGTH_SHORT).show();
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
            Log.e(TAG, "result ");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //deal with every time set up can't change fragment
        SharedPrefUtility.setParam(getApplicationContext(),"index",FRAGMENT_HOME);
        System.exit(0);
    }
}
