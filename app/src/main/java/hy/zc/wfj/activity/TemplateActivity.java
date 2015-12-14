package hy.zc.wfj.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.adapter.OrderAdapter;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.OrderListObject;

public class TemplateActivity extends FrameActivity {

    public static final String IS_ORDER_OK = "isOrderOk";
    private ImageButton common_title_back_btn;
    private TextView tv_title;
    private TextView empty_view;
    private OrderDataObject orderDataObject;
    private String uriString;

    private ListView lv_order;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        initializeComponent();
        setTitleAndData();
    }

    private void initializeComponent() {
        common_title_back_btn = (ImageButton) findViewById(R.id.common_title_back_btn);
        tv_title = (TextView) findViewById(R.id.common_title_txt);

        empty_view = (TextView) findViewById(R.id.empty_View);

        common_title_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        lv_order=(ListView)findViewById(R.id.lv_order);

        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showLogi("hhhhhhhhhhhhhhhhhhhhhhaaaaaaaaaaaaaaaaaaaaaa");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleAndData();
    }

    private void setTitleAndData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orderDataObject = (OrderDataObject) bundle.getSerializable(OrderDataObject.TITLE_KEY);
            if (orderDataObject != null) {
                tv_title.setText(orderDataObject.getTitle());
                uriString = orderDataObject.getUriString();
                if (uriString == null) {
                    lv_order.setVisibility(View.GONE);
                    empty_view.setVisibility(View.VISIBLE);
                } else {
                    lv_order.setVisibility(View.VISIBLE);
                    empty_view.setVisibility(View.GONE);
                    getDataFromUri(uriString);
                }
            }
        }
    }

    private void getDataFromUri(String uri) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("true")) {
                    OrderListObject object = JSON.parseObject(response, OrderListObject.class);
                    List<OrderListObject.DataEntity> list = object.getData();
                    OrderAdapter orderAdapter=new OrderAdapter(TemplateActivity.this,list);
                    lv_order.setAdapter(orderAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge(error.getMessage());
            }
        });
        App.addRequest(stringRequest,IS_ORDER_OK);
    }

    @Override
    protected void onStop() {
        App.cancelAllRequests(IS_ORDER_OK);
        super.onStop();
    }
}
