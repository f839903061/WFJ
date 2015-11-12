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
import android.widget.Toast;

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
        return rootView;
    }

    private void initializeComponent() {
        isLogin = (Boolean) SharedPrefUtility.getParam(getActivity(), "isLogin", false);

        avatarImage = (ImageButton) rootView.findViewById(R.id.personal_unlogin_avatar);
        settingsImage = (ImageButton) rootView.findViewById(R.id.personal_setting);
        messageImage = (ImageButton) rootView.findViewById(R.id.personal_common_message);

        concern_goods_layout = (LinearLayout) rootView.findViewById(R.id.personal_goods_list_title);
        concern_shop_layout = (LinearLayout) rootView.findViewById(R.id.personal_shop_list_title);
//        concern_browser_tv=(TextView)rootView.findViewById(R.id.personal_browsing_list_title);


        setListener();
    }

    /**
     * set component listener
     */
    private void setListener() {
        avatarImage.setOnClickListener(this);
        settingsImage.setOnClickListener(this);
        messageImage.setOnClickListener(this);

        concern_goods_layout.setOnClickListener(this);
        concern_shop_layout.setOnClickListener(this);
//        concern_browser_tv.setOnClickListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SETTINGS_REQUEST_CODE:

                break;
            case LOGIN_REQUEST_CODE:
                Log.d(TAG,getClass().getName()+"    login");
                break;
            case MESSAGE_REQUEST_CODE:

                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.personal_setting) {
            Intent intent_settings = new Intent(getActivity(), MySettingsActivity.class);
            startActivityForResult(intent_settings, SETTINGS_REQUEST_CODE);
        } else {
            if (!isLogin) {
                Intent goLoginIntent = new Intent(getActivity(), MyLoginActivity.class);
                startActivityForResult(goLoginIntent, LOGIN_REQUEST_CODE);
            } else {
                switch (v.getId()) {
                    case R.id.personal_unlogin_avatar:
                        myToast("avatar");
                        break;

                    case R.id.personal_common_message:
                        Intent intent_message = new Intent(getActivity(), MyMessageActivity.class);
                        startActivityForResult(intent_message, MESSAGE_REQUEST_CODE);
                        break;
                    //concern incident
                    case R.id.personal_goods_list_title:
                        myToast("goods");
                        break;
                    case R.id.personal_shop_list_title:
                        myToast("shop");
                        break;
//            case R.id.personal_browsing_list_title:
//                myToast("browser");

                }
            }
        }

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
