package hy.zc.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.adapter.RecommendAdapter;
import hy.zc.wfj.data.SearchObject;
import hy.zc.wfj.utility.UriManager;

public class SearchActivity extends FrameActivity implements View.OnClickListener {

    public static final String IS_SEARCH = "isSearch";
    public static final int INDEX_AUTOCOMPLETE = 1;
    public static final int INDEX_RECOMMEND = 2;
    private LinearLayout lay_back_btn;
    private AutoCompleteTextView et_search;
    private LinkedList<String> mListItems;
    private Button btn_search;
    private ArrayList<String> mList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView actualListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_search);

        initializeComponent();
    }

    private void initializeComponent() {
        //首先获取全部的关键字，用来服务autocompletetextview
        String uri = UriManager.getSearch("");
        getDataFromUri(uri, INDEX_AUTOCOMPLETE);

        lay_back_btn = (LinearLayout) findViewById(R.id.home_search_button);
        btn_search = (Button) findViewById(R.id.search_btn);
        et_search = (AutoCompleteTextView) findViewById(R.id.homeActivity_autoComplete);

        lay_back_btn.setOnClickListener(this);
        btn_search.setOnClickListener(this);
//        这里是设置弹出软件键盘确认键的文本是搜索或发送或完成等字样，同时做相应的处理
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean result = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    goToActivity();
                }

                return result;
            }
        });

        adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, mList);
        et_search.setAdapter(adapter);
        et_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                goToActivity();
            }
        });

        listviewLoadData();
    }

    /**
     * 商品推荐，此处使用的控件是pulltorefrashlistview
     */
    private void listviewLoadData() {
        actualListView = (ListView) findViewById(R.id.pull_refresh_list);
        String uri = UriManager.getRecommendUri();
        getDataFromUri(uri, INDEX_RECOMMEND);

        actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    /**
     * 给精品推荐列表填充数据
     */
    public void setAdapter(List<SearchObject.DataEntity> data) {
        View view = LayoutInflater.from(SearchActivity.this).inflate(R.layout.activity_search_listview_item, null);
        TextView tv_tmp = (TextView) view.findViewById(R.id.tv_name);
        tv_tmp.setTextSize(16);
        tv_tmp.setTextColor(Color.BLACK);
        tv_tmp.setText("精品推荐");
        actualListView.addHeaderView(view);
        RecommendAdapter recommendAdapter = new RecommendAdapter(SearchActivity.this, data);
        actualListView.setAdapter(recommendAdapter);
    }

    private void goToActivity() {
        String ptext = et_search.getText().toString().trim();
        if (ptext != null && !ptext.equals("")) {
            String uri = UriManager.getSearch(ptext);

            Bundle bundle = new Bundle();
            bundle.putString(SortDetailsActivity.URI, uri);
            bundle.putString(SortDetailsActivity.KEYWORD, ptext);
            bundle.putString(SortDetailsActivity.COME_FROM, SortDetailsActivity.SEARCH_UI);

            Intent goToList = new Intent(SearchActivity.this, SortDetailsActivity.class);
            goToList.putExtras(bundle);

            startActivity(goToList);
            SearchActivity.this.finish();
        }
    }

    /**
     * 通过请求连接获取数据
     *
     * @param uri   通过uri来区分获取的数据
     * @param index INDEX_AUTOCOMPLETE 自动补全文本框
     *              INDEX_RECOMMEND 精品推荐
     */
    private void getDataFromUri(String uri, int index) {
        if (index == INDEX_AUTOCOMPLETE) {//此处获取的是全部关键字，方便查找是的自动补全
            StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.length() > 50) {
                        SearchObject searchObject = JSON.parseObject(response, SearchObject.class);
                        List<SearchObject.DataEntity> data = searchObject.getData();
                        mList.clear();
                        for (int i = 0; i < data.size(); i++) {
                            mList.add(data.get(i).getProductName());
                        }
                    } else {
                        showLoge("没有搜索相关字眼就不做处理");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            App.addRequest(stringRequest, IS_SEARCH);
        } else if (index == INDEX_RECOMMEND) {//此处是获取精品推荐的数据，其实和上面的区别就是多了一行setAdapter的调用而已
            StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.length() > 50) {
                        SearchObject searchObject = JSON.parseObject(response, SearchObject.class);
                        List<SearchObject.DataEntity> data = searchObject.getData();
                        mList.clear();
                        for (int i = 0; i < data.size(); i++) {
                            mList.add(data.get(i).getProductName());
                        }
                        setAdapter(data);
                    } else {
                        showLoge("没有搜索相关字眼就不做处理");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            App.addRequest(stringRequest, IS_SEARCH);
        }


    }

    /**
     * 取消发送的请求
     */
    @Override
    protected void onStop() {
        App.cancelAllRequests(IS_SEARCH);
        super.onStop();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_button:
                Intent goBack = getIntent();
                setResult(Activity.RESULT_OK, goBack);
                finish();
                break;
            case R.id.search_btn:
                goToActivity();
                break;
            default:
                break;
        }
    }
}
