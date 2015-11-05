package hy.cz.wfj.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import hy.cz.wfj.R;

/**
 * Created by feng on 2015/11/4.
 */
public class RightGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    ArrayList<String> list=new ArrayList<>();

    public RightGridAdapter(Context pcontext) {
        mContext=pcontext;
        mInflater=LayoutInflater.from(pcontext);

        for (int i = 0; i < 5; i++) {
            list.add("小分类"+i);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.category_right_grid_item,null);
            viewHolder.textView=(TextView)convertView.findViewById(R.id.category_item_have_picture_text_3);
            viewHolder.simpleDraweeView=(SimpleDraweeView)convertView.findViewById(R.id.category_item_have_picture_image_3);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position));
        viewHolder.simpleDraweeView.setBackground(mContext.getResources().getDrawable(R.drawable.computer));

        return convertView;
    }

    public class ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView textView;
    }

}
