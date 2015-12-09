package hy.zc.wfj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.adapter.DetialAdapter;
import hy.zc.wfj.data.SearchObject;
import hy.zc.wfj.utility.UriManager;

public class CommodityDetailsActivity extends FrameActivity implements View.OnClickListener {

    public static final String IS_SEARCH_OK = "isSearchOk";
    public static final int INDEX_NORMAL = 1;
    public static final int INDEX_SALES = 2;
    public static final int INDEX_PRICE = 3;
    public static final String COME_FROM = "come_from";
    public static final String CATEGORY_UI = "categoryUI";
    public static final String SEARCH_UI = "searchUI";
    public static final String PRODUCT_TYPE_ID = "ProductTypeId";
    public static final String URI = "uri";
    public static final String KEYWORD = "keyword";
    private LinearLayout mBack;

    private PullToRefreshListView mPullRefreshListView;

    private TextView tv_normal;
    private TextView tv_sales;
    private TextView tv_price;
    private SimpleDraweeView img_arrow;
    private AutoCompleteTextView actv_search;

    private Boolean sortUp = true;
    private int product_type_id;
    private String keyword;
    private String come_from;
    private List<SearchObject.DataEntity> mList = new ArrayList<>();
    private String current_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(CommodityDetailsActivity.this);
        setContentView(R.layout.activity_commodity_details);

        initializeComponent();

    }

    private void initializeComponent() {
        //初始化
        tv_normal = (TextView) findViewById(R.id.tv_normal);
        tv_sales = (TextView) findViewById(R.id.tv_sales);
        tv_price = (TextView) findViewById(R.id.tv_price);
        img_arrow = (SimpleDraweeView) findViewById(R.id.img_arrow);
        actv_search = (AutoCompleteTextView) findViewById(R.id.homeActivity_autoComplete);

        //接受跳转过来的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            come_from = bundle.getString(COME_FROM);
            current_uri = bundle.getString(URI);
            //判断是从什么地方跳转过来的
            if (come_from.equals(CATEGORY_UI)) {//从分类界面跳转过来的
                product_type_id = bundle.getInt(PRODUCT_TYPE_ID);
            } else if (come_from.equals(SEARCH_UI)) {//从搜索界面跳转过来的
                keyword = bundle.getString(KEYWORD);
                actv_search.setText(keyword);
            }
            getDataFromUri(current_uri);
        }

        //设置监听
        tv_normal.setOnClickListener(this);
        tv_sales.setOnClickListener(this);
        tv_price.setOnClickListener(this);
        img_arrow.setOnClickListener(this);

        mBack = (LinearLayout) findViewById(R.id.home_search_button);
        mBack.setOnClickListener(this);

        changeTextViewColor(INDEX_NORMAL);
        listviewLoadData();

    }

    /**
     * 初始化listview列表，现在使用的是pulltorefrashlistview
     */
    private void listviewLoadData() {
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

        // Set a listener to be invoked when the list should be refreshed.
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        // Add an end-of-list listener
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(CommodityDetailsActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
            }
        });

    }



    /**
     * 通过uri从服务器获取数据
     *
     * @param uri
     */
    private void getDataFromUri(String uri) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 50) {
                    SearchObject searchObject = JSON.parseObject(response, SearchObject.class);
                    List<SearchObject.DataEntity> data = searchObject.getData();

//                    changeTextViewColor(INDEX_NORMAL);
                    //下面的代码是将数据记载到listview中去
                    setAdapter(data);
                } else {
                    showLogw("没有搜索到对应的数据");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge("连接失败！");
            }
        });
        App.addRequest(stringRequest, IS_SEARCH_OK);
    }

    /**
     * 给listview 或者 gridview填充数据
     */
    private void setAdapter(List<SearchObject.DataEntity> plist) {
        ListView actualListView = mPullRefreshListView.getRefreshableView();
        mList.clear();
        mList.addAll(plist);
        if (mList != null) {
            DetialAdapter adapter = new DetialAdapter(CommodityDetailsActivity.this, mList);
            actualListView.setAdapter(adapter);
        }
    }

    /**
     * 取消网络请求
     */
    @Override
    protected void onStop() {
        App.cancelAllRequests(IS_SEARCH_OK);
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_button://返回之前的界面
                goBack();
                break;

            case R.id.tv_normal://综合列表
                changeTextViewColor(INDEX_NORMAL);
                if (come_from.equals(CATEGORY_UI)) {
                    current_uri=UriManager.getCategorySortUri(product_type_id, UriManager.ORDER.normal);
                }else if (come_from.equals(SEARCH_UI)){
                    current_uri=UriManager.getSearch(keyword, UriManager.ORDER.normal);
                }
                getDataFromUri(current_uri);
                break;
            case R.id.tv_sales://销量列表
                changeTextViewColor(INDEX_SALES);
                if (come_from.equals(CATEGORY_UI)) {
                    current_uri = UriManager.getCategorySortUri(product_type_id, UriManager.ORDER.totalSalesdown);
                } else if (come_from.equals(SEARCH_UI)) {
                    current_uri = UriManager.getSearch(keyword, UriManager.ORDER.totalSalesdown);
                }
                getDataFromUri(current_uri);
                break;
            case R.id.tv_price://价格列表
            case R.id.img_arrow:
                changeTextViewColor(INDEX_PRICE);
                if (come_from.equals(CATEGORY_UI)) {
                    if (sortUp) {
                        img_arrow.setImageResource(R.drawable.sort_button_price_up);
                        //从便宜到贵加载数据
                        current_uri=UriManager.getCategorySortUri(product_type_id, UriManager.ORDER.priceup);

                        sortUp = false;
                    } else {
                        //从贵到便宜加载数据
                        img_arrow.setImageResource(R.drawable.sort_button_price_down);
                        current_uri=UriManager.getCategorySortUri(product_type_id, UriManager.ORDER.pricedown);

                        sortUp = true;
                    }

                } else if (come_from.equals(SEARCH_UI)) {
                    if (sortUp) {
                        img_arrow.setImageResource(R.drawable.sort_button_price_up);
                        //从便宜到贵加载数据
                        current_uri=UriManager.getSearch(keyword, UriManager.ORDER.priceup);
                        sortUp = false;
                    } else {
                        //从贵到便宜加载数据
                        img_arrow.setImageResource(R.drawable.sort_button_price_down);
                        current_uri=UriManager.getSearch(keyword, UriManager.ORDER.pricedown);
                        sortUp = true;
                    }
                }
                getDataFromUri(current_uri);
                break;
            default:

                break;
        }
    }


    /**
     * 修改综合，销量，价格的颜色
     *
     * @param index
     *           INDEX_NORMAL 综合
     *           INDEX_SALES 销量
     *           INDEX_PRICE 价格
     */
    private void changeTextViewColor(int index) {
        switch (index) {
            case INDEX_NORMAL:
                tv_normal.setTextColor(Color.RED);
                tv_sales.setTextColor(Color.BLACK);
                tv_price.setTextColor(Color.BLACK);
                img_arrow.setImageResource(R.drawable.sort_button_price_up_grey);
                break;
            case INDEX_SALES:
                tv_normal.setTextColor(Color.BLACK);
                tv_sales.setTextColor(Color.RED);
                tv_price.setTextColor(Color.BLACK);
                img_arrow.setImageResource(R.drawable.sort_button_price_up_grey);
                break;
            case INDEX_PRICE:
                tv_normal.setTextColor(Color.BLACK);
                tv_sales.setTextColor(Color.BLACK);
                tv_price.setTextColor(Color.RED);
                break;
        }
    }


}
