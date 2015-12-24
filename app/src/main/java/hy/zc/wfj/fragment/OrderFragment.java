package hy.zc.wfj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.activity.TemplateActivity;
import hy.zc.wfj.adapter.OrderAdapter;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends FrameFragment implements OrderAdapter.DelCallBack{

    private ListView lv_order;
    public static final String IS_ORDER_OK = "isOrderOk";

    private static OrderFragment orderFragment;
    private int mCurrentType = 0;
    private List<OrderListObject.DataEntity> mList;
    private OrderAdapter mOrderAdapter;

    public OrderFragment() {

    }

    public static synchronized OrderFragment getInstent() {
        if (orderFragment == null) {
            orderFragment = new OrderFragment();
        }
        return orderFragment;
    }

    public void setType(int ptype) {
        mCurrentType = ptype;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        initializeComponent(rootView);
        return rootView;
    }

    private void initializeComponent(View rootView) {
        lv_order = (ListView) rootView.findViewById(R.id.lv_order);

        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle pbundle = new Bundle();
                OrderDataObject orderDataObject=new OrderDataObject();
                orderDataObject.setTitle(OrderDataObject.TITLE_COMPLETE);
                pbundle.putSerializable(OrderDataObject.TITLE_KEY,orderDataObject);
                goToActivity(TemplateActivity.class, pbundle);
            }
        });
        String temp = (String) SharedPrefUtility.getParam(getActivity(), SharedPrefUtility.LOGIN_DATA, "");
        if (!temp.equals("")) {
            UserLoginObject object = JSON.parseObject(temp, UserLoginObject.class);
            int customerId = object.getData().getCustomerId();
            String uri = UriManager.getOrderDetail(customerId,mCurrentType);
            getDataFromUri(uri);
//            跳转到订单完成界面，查看具体订单
            lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showToast("当前点击的是第"+position+"项");
                    mOrderAdapter.gotoOrderDetailUI(position);
                }
            });
        }
    }

    /**
     * 获取数据
     * @param uri
     */
    private void getDataFromUri(String uri) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("true")) {
                    OrderListObject object = JSON.parseObject(response, OrderListObject.class);
                    mList = object.getData();
                    mOrderAdapter = new OrderAdapter(getActivity(), mList, (OrderAdapter.DelCallBack) OrderFragment.this,mCurrentType);
                    lv_order.setAdapter(mOrderAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge(error.getMessage());
            }
        });
        App.addRequest(stringRequest, IS_ORDER_OK);
    }

    @Override
    public void onStop() {
        App.cancelAllRequests(IS_ORDER_OK);
        super.onStop();
    }

    @Override
    public void del(int position) {
        mList.remove(position);
        mOrderAdapter.notifyDataSetChanged();
    }
}
