package hy.zc.wfj.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.CommodityDetailsActivity;
import hy.zc.wfj.data.CategroyJsonObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/11/4.
 */
public class RightGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> mList;

    public RightGridAdapter(Context pcontext, List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> plist) {
        mContext = pcontext;
        mInflater = LayoutInflater.from(pcontext);
        mList = plist;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.category_right_grid_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.category_item_have_picture_text_3);
            viewHolder.simpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.category_item_have_picture_image_3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mList.get(position).getSortName());
        //拼接好图片之后，使用fresco来加载显示
        Uri uri = UriManager.getCategoryPicUri(mList.get(position).getCategoryImage());
        viewHolder.simpleDraweeView.setImageURI(uri);
        viewHolder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommodityDetails();
            }

            private void openCommodityDetails() {
                Intent intent = new Intent();
                intent.setClass(mContext, CommodityDetailsActivity.class);
                Bundle bundle = new Bundle();
                String uri = UriManager.getDetialFull(mList.get(position).getProductTypeId(), UriManager.ORDER.normal);
                bundle.putString("uri", uri);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


    public class ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textView;
    }


}
