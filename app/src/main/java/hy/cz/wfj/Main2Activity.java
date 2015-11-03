package hy.cz.wfj;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
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
    public static final String TAG = "fengluchun";

    private FrameLayout mFrameLayout;
    private RadioGroup mRadioGroup;

    //    private RadioButton mRadioButton_home;
//    private RadioButton mRadioButton_category;
//    private RadioButton mRadioButton_cart;
//    private RadioButton mRadioButton_personal;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.e(TAG, "AAAAAA is create");
        initializeComponent();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setListener();
        Log.e(TAG,"AAAAAA is resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "AAAAAA is start");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"AAAAAA is stop");
    }

    /**
     * initialize componet and transcation navigation tasks
     */
    private void initializeComponent() {
        mFrameLayout = (FrameLayout) findViewById(R.id.main_framelayout);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
//        mRadioButton_home = (RadioButton) findViewById(R.id.nav_home_btn);
//        mRadioButton_category = (RadioButton) findViewById(R.id.nav_cart_btn);
//        mRadioButton_cart = (RadioButton) findViewById(R.id.nav_cart_btn);
//        mRadioButton_personal = (RadioButton) findViewById(R.id.nav_personal_btn);

        //get FragmentTransaction
        fragmentTransaction = getFragmentManager().beginTransaction();

        //set home fragment when application is first set up
        fragmentTransaction.replace(R.id.main_framelayout, HomeFragment.newInstance(), HOME_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //set listener for radiogroup
        setListener();
    }

    /**
     * set radiogroup listener
     */
    private void setListener() {
        //set default checked is 1-->home
        Log.e(TAG, "listener");
        mRadioGroup.check(mRadioGroup.getChildCount() - (mRadioGroup.getChildCount() - 1));

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
                        fragmentTransaction.commit();
                        break;
                    case FRAGMENT_CATEGORY:
                        fragmentTransaction.replace(R.id.main_framelayout, CategoryFragment.getInstance(), CATEGORY_FRAGMENT_TAG);
                        fragmentTransaction.commit();
                        break;
                    case FRAGMENT_CART:
                        fragmentTransaction.replace(R.id.main_framelayout, CartFragment.getInstance(), CART_FRAGMENT_TAG);
                        fragmentTransaction.commit();
                        break;
                    case FRAGMENT_PERSONAL:
                        fragmentTransaction.replace(R.id.main_framelayout, PersonalFragment.getInstance(), PERSONAL_FRAGMENT_TAG);
                        fragmentTransaction.commit();
                        break;
                }
                //when user pressed BACK key will go to previous fragment,but if you changed more times fragment,you must pressed more times BACK key
//                fragmentTransaction.addToBackStack(null);
                //before you commit second , you must get ft instance again otherwise you will get illegalexception
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
    protected void onDestroy() {
        super.onDestroy();
        //deal with every time set up can't change fragment
        System.exit(0);
        Log.e(TAG, "AAAAAA is destroy");
    }
}
