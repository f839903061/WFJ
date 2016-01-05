package hy.zc.wfj.fragment;


import android.app.ProgressDialog;
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

import java.net.URI;
import java.util.List;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.activity.TemplateActivity;
import hy.zc.wfj.adapter.OrderAdapter;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.data.UserLoginErrorObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends FrameFragment implements OrderAdapter.DelCallBack{

    public static final String PAY_OK = "payOk";
    private ListView lv_order;
    public static final String IS_ORDER_OK = "isOrderOk";

    public static final int FLAG_PAY = 1;
    public static final int FLAG_SIGN = 2;
    public static final int FLAG_DEL = 3;

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

    }

    @Override
    public void onResume() {
        super.onResume();
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
        App.cancelAllRequests(PAY_OK);
        super.onStop();
    }

    /**
     * 删除订单信息
     * @param position 订单的索引值
     */
    @Override
    public void del(int position) {
        OrderListObject.DataEntity dataEntity = mList.get(position);
        int ordersId = dataEntity.getOrdersId();
        String uri = UriManager.getDelOrder(ordersId);
        getState(uri, FLAG_DEL, position);//向服务器端发送删除请求,并删除本地列表项

    }

    @Override
    public void modifyPayState(String uri) {
        getState(uri,FLAG_PAY,0);
    }

    @Override
    public void modifySignState(String uri) {

        getState(uri,FLAG_SIGN,0);
    }

    /**
     * 获取请求状态
     * @param uri
     * @param pflag 表示那个功能的调用（付款，签收，删除订单）
     * @param index 这个值只是在删除订单的时候才会用到
     */
    private void getState(String uri, final int pflag, final int index){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UserLoginErrorObject userLoginErrorObject = JSON.parseObject(response, UserLoginErrorObject.class);
                boolean status = userLoginErrorObject.isStatus();
                String message = userLoginErrorObject.getData();
                if (status) {
                    if (pflag==FLAG_DEL) {
                        mList.remove(index);//删除本地
                        mOrderAdapter.notifyDataSetChanged();
                    }
                }else {
                    showToast(message);
                }
//                mOrderAdapter.notifyDataSetChanged();
//                showToast(message);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge(error.getMessage());
            }
        });

        App.addRequest(stringRequest, PAY_OK);
    }
}
