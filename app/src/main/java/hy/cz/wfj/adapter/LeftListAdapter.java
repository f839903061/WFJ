package hy.cz.wfj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hy.cz.wfj.R;
import hy.cz.wfj.data.CategoryListObject;

/**
 * 适配分类界面的左边的listview
 * Created by feng on 2015/10/27.
 */
public class LeftListAdapter extends BaseAdapter {
    private ArrayList<CategoryListObject> mList =null;
    private LayoutInflater mInflater=null;
    private int select=-1;

    public LeftListAdapter(Context pcontext,ArrayList<CategoryListObject> plist) {
        mList =plist;
        mInflater=LayoutInflater.from(pcontext);
    }

    /**
     * 用来判断当前点击项，可以解决模板复用导致的textview文字背景色紊乱
     * @param pposition
     */
    public void setSelect(int pposition){
        select=pposition;
        this.notifyDataSetChanged();
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
        final ViewHolder viewHolder ;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView = mInflater.inflate(R.layout.category_left_list_item, null);
            viewHolder.textView=(TextView)convertView.findViewById(R.id.category_left_list_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.textView.setText(mList.get(position).getName());
        if (select==position)
        {
            viewHolder.textView.setTextColor(Color.rgb(241, 87, 87));
            convertView.setBackgroundColor(Color.WHITE);
        }else {
            viewHolder.textView.setTextColor(Color.rgb(111,112,113));
            convertView.setBackground(convertView.getResources().getDrawable(R.drawable.category_new_left_normal));
        }
        return convertView;
    }

    public final class ViewHolder{
        public TextView textView;
    }
}
