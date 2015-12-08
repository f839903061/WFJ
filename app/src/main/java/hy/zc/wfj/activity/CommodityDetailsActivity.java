package hy.zc.wfj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
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

import java.util.List;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.adapter.DetialAdapter;
import hy.zc.wfj.data.SearchObject;

public class CommodityDetailsActivity extends FrameActivity implements View.OnClickListener {

    public static final String IS_SEARCH_OK = "isSearchOk";
    public static final int INDEX_NORMAL = 1;
    public static final int INDEX_SALES = 2;
    public static final int INDEX_PRICE = 3;
    private LinearLayout mBack;

    private PullToRefreshListView mPullRefreshListView;

    private TextView tv_normal;
    private TextView tv_sales;
    private TextView tv_price;
    private SimpleDraweeView img_arrow;

    private Boolean sortUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(CommodityDetailsActivity.this);
        setContentView(R.layout.activity_commodity_details);

        initializeComponent();

    }

    private void initializeComponent() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            String uri = bundle.getString("uri");

            getDataFromUri(uri);
        }

        tv_normal = (TextView) findViewById(R.id.tv_normal);
        tv_sales = (TextView) findViewById(R.id.tv_sales);
        tv_price = (TextView) findViewById(R.id.tv_price);
        img_arrow = (SimpleDraweeView) findViewById(R.id.img_arrow);


        tv_normal.setOnClickListener(this);
        tv_sales.setOnClickListener(this);
        tv_price.setOnClickListener(this);
        img_arrow.setOnClickListener(this);

        mBack = (LinearLayout) findViewById(R.id.home_search_button);
        mBack.setOnClickListener(this);


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
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                // Do work to refresh the list here.
//                new GetDataTask().execute();
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

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;//这里是要返回数据的，我现在没有做处理
        }

        @Override
        protected void onPostExecute(String[] result) {
/*            mListItems.addFirst("Added after refresh...");
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();*/

            super.onPostExecute(result);
        }
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

                    changeTextViewColor(INDEX_NORMAL);
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
        DetialAdapter adapter = new DetialAdapter(CommodityDetailsActivity.this, plist);
        actualListView.setAdapter(adapter);
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
                break;
            case R.id.tv_sales://销量列表
                changeTextViewColor(INDEX_SALES);

                break;
            case R.id.tv_price://价格列表

            case R.id.img_arrow:
                changeTextViewColor(INDEX_PRICE);
                if (sortUp) {
                    img_arrow.setImageResource(R.drawable.sort_button_price_up);
                    //从便宜到贵加载数据

                    sortUp = false;
                } else {
                    //从贵到便宜加载数据
                    img_arrow.setImageResource(R.drawable.sort_button_price_down);
                    sortUp = true;
                }
                break;
            default:

                break;
        }
    }


    /**
     * 修改综合，销量，价格的颜色
     *
     * @param index
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
