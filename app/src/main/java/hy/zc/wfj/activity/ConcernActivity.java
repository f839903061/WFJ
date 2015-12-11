package hy.zc.wfj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.adapter.ConcernCommodityAdapter;
import hy.zc.wfj.adapter.ConcernShopAdapter;
import hy.zc.wfj.data.ConcernCommodityObject;
import hy.zc.wfj.data.ConcernShopObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class ConcernActivity extends FrameActivity {

    public static final String IS_CONCERN_OK = "isConcernOk";
    public static String TYPE = "type";
    public static int COMMODITY = 1;
    public static int SHOP = 2;

    private ImageButton imgb_back;
    private RadioGroup rg_choice;
    private RadioButton rb_commodity;
    private RadioButton rb_shop;
    private int showtype;
    private ListView lv_concern;
    private ConcernCommodityAdapter commodityAdapter;
    private ConcernShopAdapter shopAdapter;
    private List mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concern);
        initializeComponent();
    }

    private void initializeComponent() {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            showtype = extras.getInt(TYPE);
        }


        imgb_back = (ImageButton) findViewById(R.id.common_title_back_btn);
        rg_choice = (RadioGroup) findViewById(R.id.rg_choice);
        rb_commodity = (RadioButton) findViewById(R.id.rb_commodity);
        rb_shop = (RadioButton) findViewById(R.id.rb_shop);
        lv_concern = (ListView) findViewById(R.id.lv_concern);

        imgb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        rg_choice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_commodity:
                        showtype = COMMODITY;
                        ChangeData(false);
                        break;
                    case R.id.rb_shop:
                        showtype = SHOP;
                        ChangeData(false);
                        break;
                }
            }
        });

        lv_concern.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (showtype==COMMODITY) {
                    commodityAdapter.gotoDetial(position);
                }else if (showtype==SHOP){//这个还在处理中，无法测试
//                    shopAdapter.gotoDetial(position);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        ChangeData(true);
        super.onResume();
    }

    /**
     * 给列表添加对应的数据
     * @param isFirst
     *          判断是跳转过来的显示还是通过点击title里的radiobutton来实现的数据切换，
     */
    private void ChangeData(Boolean isFirst) {
        String temp = (String) SharedPrefUtility.getParam(ConcernActivity.this, SharedPrefUtility.LOGIN_DATA, "");
        if (!temp.equals("")) {
            UserLoginObject object = JSON.parseObject(temp, UserLoginObject.class);
            int id = object.getData().getCustomerId();
            String uri = null;
            if (isFirst) {
                if (showtype == COMMODITY) {
                    rg_choice.check(R.id.rb_commodity);
                    uri = UriManager.getConcernCommodityUri(id);
                } else if (showtype == SHOP) {
                    rg_choice.check(R.id.rb_shop);
                    uri = UriManager.getConcernShopUri(id);
                }
            }else {//和上面的不同的地方就是少了一个radiogroup的check
                if (showtype == COMMODITY) {
                    uri = UriManager.getConcernCommodityUri(id);
                } else if (showtype == SHOP) {
                    uri = UriManager.getConcernShopUri(id);
                }
            }

            getDataFromUri(uri,showtype);
        }
    }

    private void getDataFromUri(String uri,int type) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("true")) {
                    if (showtype == COMMODITY) {
                        ConcernCommodityObject commodityObject = JSON.parseObject(response, ConcernCommodityObject.class);
                        mList = commodityObject.getData();
                    } else if (showtype == SHOP) {
                        ConcernShopObject shopObject = JSON.parseObject(response, ConcernShopObject.class);
                        mList = shopObject.getData();
                    }
                    setAdapter(mList,showtype);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge(error.getMessage());
            }
        });

        App.addRequest(stringRequest, IS_CONCERN_OK);
    }

    /**
     * 给listview添加数据
     * @param data
     *          传过来的数据，虽然不一样，但是这里用的泛型list，挺方便
     * @param ptype
     */
    private void setAdapter(List data,int ptype) {
        if (ptype==COMMODITY) {
            commodityAdapter = new ConcernCommodityAdapter(ConcernActivity.this, data);
            lv_concern.setAdapter(commodityAdapter);
        }else if (ptype==SHOP){
            shopAdapter = new ConcernShopAdapter(ConcernActivity.this,data);
            lv_concern.setAdapter(shopAdapter);
        }
    }

    @Override
    protected void onStop() {
        App.cancelAllRequests(IS_CONCERN_OK);
        super.onStop();
    }
}
