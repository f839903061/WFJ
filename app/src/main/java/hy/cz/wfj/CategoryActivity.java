package hy.cz.wfj;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import hy.cz.wfj.adapter.LeftListAdapter;
import hy.cz.wfj.adapter.RightListAdapter;
import hy.cz.wfj.data.CategoryListObject;

/**
 * @author feng
 */
public class CategoryActivity extends AppCompatActivity {

    private ListView mLeftListView;
    private ListView mRightListView;

    private LeftListAdapter mleftListAdapter;
    private RightListAdapter mrightListAdapter;
    //all category object
    private ArrayList<CategoryListObject> mCategoryObjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_category);
        initComponent();
        loadData();
    }

    private void loadData() {
        for (int i = 0; i < 30; i++) {
            CategoryListObject categoryListObject = new CategoryListObject();
            categoryListObject.setName("Name"+i);
            mCategoryObjectList.add(categoryListObject);
        }

        adapterListView();
    }

    private void adapterListView() {
        mleftListAdapter = new LeftListAdapter(CategoryActivity.this, mCategoryObjectList);
        mrightListAdapter = new RightListAdapter(CategoryActivity.this, mCategoryObjectList);
        mLeftListView.setAdapter(mleftListAdapter);
        mRightListView.setAdapter(mrightListAdapter);
        mLeftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView tv = (TextView) view.findViewById(R.id.category_left_list_text);
//                tv.setTextColor(Color.rgb(241, 87, 87));
                mLeftListView.smoothScrollToPositionFromTop(position, 0);
                mleftListAdapter.setSelect(position);
                view.setBackgroundColor(Color.WHITE);

            }
        });
    }


    /**
     * initialize component
     *
     * @author feng
     */
    private void initComponent() {
        mLeftListView = (ListView) this.findViewById(R.id.left_list);
        mRightListView = (ListView) this.findViewById(R.id.right_list);
        mCategoryObjectList = new ArrayList<>();
    }
}
