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

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import hy.cz.wfj.R;
import hy.cz.wfj.adapter.LeftListAdapter;
import hy.cz.wfj.adapter.RightListAdapter;
import hy.cz.wfj.data.CategoryListObject;
import hy.cz.wfj.data.CategroyJsonObject;
import hy.cz.wfj.utility.MySingleton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CategoryFragment extends Fragment {

    public static final String TAG = "fengluchun";
    public static final String RECEIVER_NULL_ERROR = " 网络联通，接收数据却为空";
    public static final String HTTP_CONNECT_FAILED = " HTTP连接失败";
    public static final String PARSE_ERROR = "解析出问题了";
    private OnFragmentInteractionListener mListener;

    private static CategoryFragment categoryFragment = null;

    private View rootView;
    private ListView mLeftListView;
    private ListView mRightListView;

    private LeftListAdapter mleftListAdapter;
    private RightListAdapter mrightListAdapter;
    //all category object
    private ArrayList<CategoryListObject> mCategoryObjectList;
    private List<CategroyJsonObject.DataEntity> mLeftList;
    private List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> mRightList;
    private List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> tempRightList;

    public static synchronized CategoryFragment getInstance() {
        if (categoryFragment == null) {
            categoryFragment = new CategoryFragment();
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
        rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initializeCompoment();
        getDataFromUri();
        return rootView;
    }

    /**
     * 从服务器端获取分类列表数据
     */
    private void getDataFromUri() {
        //severlet version
//        String uri="http://192.168.10.210:8080/wfj_front/phone/phonecategory?method=initType";
        //action version
        String uri = "http://192.168.10.210:8080/wfj_front/phone/phonecategory.action?method=initType";
//        String uri="http://www.baidu.com/";

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //通过Json工具类将接收到的response 数据从json反序列为实体对象
                CategroyJsonObject jsonObject = JSON.parseObject(response, CategroyJsonObject.class);
                if (jsonObject != null) {
                    mLeftList = jsonObject.getData();
                    if (mLeftList != null) {
                        for (int i = 0; i < mLeftList.size(); i++) {
                            Log.i(TAG, mLeftList.get(i).getCategoryDescription());
                        }
                        //将数据传给adapter
                        setAdapter();
                    } else {
                        Log.e(TAG, PARSE_ERROR);
                    }
                } else {
                    Log.e(TAG, RECEIVER_NULL_ERROR);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, HTTP_CONNECT_FAILED);
            }
        });
        queue.add(stringRequest);

    }

    /**
     * set all listview adapter
     */
    private void setAdapter() {

        mleftListAdapter = new LeftListAdapter(getActivity().getApplication(), mLeftList);
        mRightList=mLeftList.get(0).getChildProductType();
        mrightListAdapter = new RightListAdapter(getActivity().getApplication(), mRightList);
        //right list add headview
        //给右边的listview添加头布局，就是一个广告位单张图片
//        mRightListView.addHeaderView(LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.category_right_list_head_view, null));
        mLeftListView.setAdapter(mleftListAdapter);
        mRightListView.setAdapter(mrightListAdapter);

        mLeftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLeftListView.smoothScrollToPositionFromTop(position, 0);
                /*deal with module reuse template bug
                解决模板复用显示bug*/
                mleftListAdapter.setSelect(position);
                view.setBackgroundColor(Color.WHITE);
                //刷新右侧的列表
                mRightList.clear();
                mLeftList.get(position).getChildProductType();
                mRightList.addAll(mLeftList.get(position).getChildProductType());
                mrightListAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * initialize component
     */
    private void initializeCompoment() {
        mLeftListView = (ListView) rootView.findViewById(R.id.left_list);
        mRightListView = (ListView) rootView.findViewById(R.id.right_list);
        mCategoryObjectList = new ArrayList<>();


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
