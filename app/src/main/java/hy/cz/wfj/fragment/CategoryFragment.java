package hy.cz.wfj.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import hy.cz.wfj.R;
import hy.cz.wfj.adapter.LeftListAdapter;
import hy.cz.wfj.adapter.RightListAdapter;
import hy.cz.wfj.data.CategoryListObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CategoryFragment extends Fragment {

    public static final String TAG = "fengluchun";
    private OnFragmentInteractionListener mListener;

    private static CategoryFragment categoryFragment=null;

    private View rootView;
    private ListView mLeftListView;
    private ListView mRightListView;

    private LeftListAdapter mleftListAdapter;
    private RightListAdapter mrightListAdapter;
    //all category object
    private ArrayList<CategoryListObject> mCategoryObjectList;

    
    public static synchronized CategoryFragment getInstance(){
        if (categoryFragment==null){
            categoryFragment=new CategoryFragment();
        }
        return categoryFragment;
    }
    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fresco.initialize(getActivity().getApplicationContext());
        rootView=inflater.inflate(R.layout.fragment_category, container, false);
        initializeCompoment();
        loadData();
        return rootView;
    }

    /**
     * load data and set listview adapter
     */
    private void loadData() {
        getDataFromUri();
        for (int i = 0; i < 40; i++) {
            CategoryListObject categoryListObject=new CategoryListObject();
            categoryListObject.setName("大分类"+i);
            mCategoryObjectList.add(categoryListObject);
        }

        setAdapter();
    }

    private void getDataFromUri() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        String uri="http://192.168.10.181:8080/wfj_front/PhoneCategory?method=initType";
//        String uri="https://192.168.10.181:8443/wfj_front/PhoneCategory?method=initType";
//        String uri="http://www.baidu.com/";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,getClass().getName()+"    successed:"+response.substring(0,5));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,getClass().getName()+"    failed");
            }
        });
        queue.add(stringRequest);
    }

    /**
     * set all listview adapter
     */
    private void setAdapter() {
        mLeftListView.setAdapter(mleftListAdapter);

        //right list add headview
        //给右边的listview添加头布局，就是一个广告位单张图片
//        mRightListView.addHeaderView(LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.category_right_list_head_view, null));

        mRightListView.setAdapter(mrightListAdapter);
        mLeftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLeftListView.smoothScrollToPositionFromTop(position,0);
                //deal with module reuse template bug
                //解决模板复用显示bug
                mleftListAdapter.setSelect(position);
                view.setBackgroundColor(Color.WHITE);
            }
        });
    }

    /**
     * initialize component
     */
    private void initializeCompoment() {
        mLeftListView=(ListView)rootView.findViewById(R.id.left_list);
        mRightListView=(ListView)rootView.findViewById(R.id.right_list);
        mCategoryObjectList=new ArrayList<>();
        mleftListAdapter=new LeftListAdapter(getActivity().getApplication(),mCategoryObjectList);
        mrightListAdapter=new RightListAdapter(getActivity().getApplication(),mCategoryObjectList);
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
