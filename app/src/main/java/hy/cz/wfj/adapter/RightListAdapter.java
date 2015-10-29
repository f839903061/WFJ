package hy.cz.wfj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hy.cz.wfj.R;
import hy.cz.wfj.data.CategoryListObject;

/**
 * 分类界面的右边的listview的适配器
 * Created by feng on 2015/10/27.
 */
public class RightListAdapter extends BaseAdapter {
    private ArrayList<CategoryListObject> mList =null;
    private LayoutInflater mInflater=null;

    public RightListAdapter(Context pcontext, ArrayList<CategoryListObject> plist) {
        mList =plist;
        mInflater=LayoutInflater.from(pcontext);
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
        ViewHolder viewHolder ;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView = mInflater.inflate(R.layout.category_left_list_item, null);
            viewHolder.textView=(TextView)convertView.findViewById(R.id.category_left_list_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.textView.setText(mList.get(position).getName());
        return convertView;
    }

    public final class ViewHolder{
        public TextView textView;
    }
}
