package hy.cz.wfj.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import hy.cz.wfj.R;
import hy.cz.wfj.activity.MyLoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private ImageButton avatarImage;
    private ImageButton settingsImage;
    private ImageButton messageImage;

    private TextView concern_goods_tv;
    private TextView concern_shop_tv;
    private TextView concern_browser_tv;

    private OnFragmentInteractionListener mListener;

    private static PersonalFragment personalFragment;

    public static synchronized PersonalFragment getInstance(){
        if (personalFragment==null){
            personalFragment=new PersonalFragment();
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
        rootView=inflater.inflate(R.layout.personal_layout, container, false);
        initializeComponent();
        return rootView;
    }

    private void initializeComponent() {
        avatarImage=(ImageButton)rootView.findViewById(R.id.personal_unlogin_avatar);
        settingsImage=(ImageButton)rootView.findViewById(R.id.personal_setting);
        messageImage=(ImageButton)rootView.findViewById(R.id.personal_common_message);

        concern_goods_tv=(TextView)rootView.findViewById(R.id.personal_goods_list_title);
        concern_shop_tv=(TextView)rootView.findViewById(R.id.personal_shop_list_title);
        concern_browser_tv=(TextView)rootView.findViewById(R.id.personal_browsing_list_title);


        setListener();
    }

    /**
     * set component listener
     */
    private void setListener() {
        avatarImage.setOnClickListener(this);
        settingsImage.setOnClickListener(this);
        messageImage.setOnClickListener(this);

        concern_goods_tv.setOnClickListener(this);
        concern_shop_tv.setOnClickListener(this);
        concern_browser_tv.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personal_unlogin_avatar:
                myToast("text");
                Intent intent=new Intent(getActivity(), MyLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.personal_setting:
                myToast("settings");
                break;
            case R.id.personal_common_message:
                myToast("message");
                break;
            //concern incident
            case R.id.personal_goods_list_title:
                myToast("goods");
                break;
            case R.id.personal_shop_list_title:
                myToast("shop");
                break;
            case R.id.personal_browsing_list_title:
                myToast("browser");
            default:
                break;
        }
    }

    private void myToast(String text) {
        Toast.makeText(getActivity().getApplicationContext(),"->"+text+"<-",Toast.LENGTH_SHORT).show();
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
