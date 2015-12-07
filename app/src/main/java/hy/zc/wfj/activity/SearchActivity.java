package hy.zc.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Arrays;
import java.util.LinkedList;

import hy.zc.wfj.R;

public class SearchActivity extends FrameActivity implements View.OnClickListener{

    private LinearLayout lay_back_btn;
    private AutoCompleteTextView et_search;
    private LinkedList<String> mListItems;
    private PullToRefreshListView mPullRefreshListView;
    private ArrayAdapter<String> mAdapter;
    private Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_search);

        initializeComponent();
    }

    private void initializeComponent() {
        lay_back_btn =(LinearLayout)findViewById(R.id.home_search_button);
        btn_search=(Button)findViewById(R.id.search_btn);
        et_search=(AutoCompleteTextView)findViewById(R.id.homeActivity_autoComplete);
        lay_back_btn.setOnClickListener(this);
        btn_search.setOnClickListener(this);
//        这里是设置弹出软件键盘确认键的文本是搜索或发送或完成等字样，同时做相应的处理
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean result = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    showToast("wan cheng");
                }

                return result;
            }
        });
//        实时监听输入，作用就是来随时变化自动补全数据
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                showLogi("before");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showLogi("....");
            }

            @Override
            public void afterTextChanged(Editable s) {
                showLogi("after");
            }
        });

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
                new GetDataTask().execute();
            }
        });

        // Add an end-of-list listener
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
               showToast("End of List!");
            }
        });

        ListView actualListView = mPullRefreshListView.getRefreshableView();

        // Need to use the Actual ListView when registering for Context Menu
        registerForContextMenu(actualListView);

        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);


        // You can also just use setListAdapter(mAdapter) or
        // mPullRefreshListView.setAdapter(mAdapter)
        actualListView.setAdapter(mAdapter);
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(1000);//刷新时间为1秒
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            mListItems.addFirst("Added after refresh...");
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }


    private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler" };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_search_button:
                Intent goBack = getIntent();
                setResult(Activity.RESULT_OK,goBack);
                finish();
                break;
            case R.id.search_btn:
                showToast("sldjfslkfdjslfj");
                break;
            default:
                break;
        }
    }
}
