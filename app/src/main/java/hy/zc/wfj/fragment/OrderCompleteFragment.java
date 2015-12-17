package hy.zc.wfj.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.OrderCompleteAdapter;
import hy.zc.wfj.customview.MyListView;
import hy.zc.wfj.data.OrderListObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderCompleteFragment extends Fragment {

    private TextView tv_orderNum;
    private TextView tv_consignee;
    private TextView tv_mobilePhone;
    private TextView tv_address;
    private TextView tv_payMode;
    private TextView tv_sendType;
    private TextView tv_invoiceType;
    private TextView tv_invoiceInfo;
    private TextView tv_finalAmount;
    private TextView tv_cashback;
    private TextView tv_freight;
    private TextView tv_really_pay;
    private TextView tv_order_time;
    private MyListView lv_order;

    private String payType[] = {"未知", "货到付款", "支付宝", "银行汇款", "网银支付"};
    private String sendType[] = {"未知", "快递公司", "同城快递", "线下自提"};
    private String invoiceType[] = {"未知", "不开发票", "普通发票", "增值税发票"};
    private OrderListObject.DataEntity mEntity;

    public OrderCompleteFragment() {
        // Required empty public constructor
    }

    public OrderCompleteFragment(OrderListObject.DataEntity entity) {
        this.mEntity = entity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_complete, container, false);
        initializeComponent(rootView);
        loadData();
        return rootView;
    }


    /**
     * 初始化数据
     * @param rootView
     */
    private void initializeComponent(View rootView) {

        tv_orderNum = (TextView) rootView.findViewById(R.id.tv_orderNum);
        tv_consignee = (TextView) rootView.findViewById(R.id.tv_name);
        tv_mobilePhone = (TextView) rootView.findViewById(R.id.tv_phone);
        tv_address = (TextView) rootView.findViewById(R.id.tv_address);
        tv_payMode = (TextView) rootView.findViewById(R.id.tv_payType);
        tv_sendType = (TextView) rootView.findViewById(R.id.tv_sendType);
        tv_invoiceType = (TextView) rootView.findViewById(R.id.tv_bill_start);
        tv_invoiceInfo = (TextView) rootView.findViewById(R.id.tv_bill_context);
        tv_finalAmount = (TextView) rootView.findViewById(R.id.tv_total_price);
        tv_cashback = (TextView) rootView.findViewById(R.id.tv_cashback);
        tv_freight = (TextView) rootView.findViewById(R.id.tv_freight);
        tv_really_pay = (TextView) rootView.findViewById(R.id.tv_really_pay);
        tv_order_time = (TextView) rootView.findViewById(R.id.tv_order_time);
        lv_order = (MyListView) rootView.findViewById(R.id.lv_order);
    }

    /**
     * 填充数据
     */
    private void loadData() {

        tv_orderNum.setText(mEntity.getOrdersNo());
        tv_consignee.setText(mEntity.getConsignee());
        tv_mobilePhone.setText(mEntity.getMobilePhone());
        tv_address.setText(mEntity.getAddress());
        tv_payMode.setText(payType[mEntity.getPayMode()]);
        tv_sendType.setText(sendType[mEntity.getSendType()]);
        tv_invoiceType.setText(invoiceType[mEntity.getInvoiceType()]);
        tv_invoiceInfo.setText(mEntity.getInvoiceInfo());
        tv_finalAmount.setText("￥" + mEntity.getFinalAmount());
        tv_cashback.setText("￥0.0");
        tv_freight.setText("￥" + mEntity.getFreight());
        tv_really_pay.setText("￥" + mEntity.getFinalAmount());
        tv_order_time.setText(mEntity.getCreatTime());
        List<OrderListObject.DataEntity.ListEntity> list = mEntity.getList();

        OrderCompleteAdapter adapter = new OrderCompleteAdapter(getActivity(), list);
        lv_order.setAdapter(adapter);
    }

}
