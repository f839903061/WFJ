package hy.cz.wfj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hy.cz.wfj.R;
import hy.cz.wfj.customview.MyGridView;
import hy.cz.wfj.data.CategoryListObject;
import hy.cz.wfj.data.CategroyJsonObject;

/**
 * 分类界面的右边的listview的适配器
 * Created by feng on 2015/10/27.
 */
public class RightListAdapter extends BaseAdapter {
    private List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> mList = null;
    private LayoutInflater mInflater = null;
    private Context mContext;

    public RightListAdapter(Context pcontext, List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> plist) {
        mContext = pcontext;
        mList = plist;
        mInflater = LayoutInflater.from(pcontext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.category_right_list_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.right_item_title);
            viewHolder.myGridView = (MyGridView) convertView.findViewById(R.id.category_right_grid);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mList.get(position).getSortName());
        List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> rrList = mList.get(position).getChildProductType();
        RightGridAdapter rightGridAdapter = new RightGridAdapter(mContext,rrList);
        viewHolder.myGridView.setAdapter(rightGridAdapter);
        return convertView;
    }

    public class ViewHolder {
        TextView textView;
        MyGridView myGridView;
    }

}
