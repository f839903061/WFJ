package hy.zc.wfj.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.SortDetailsActivity;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.SearchObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/9.
 */
public class RecommendAdapter extends BBaseAdapter {
    private Context mContext;
    private List<SearchObject.DataEntity> mList=new ArrayList();
    public RecommendAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mList= plist;
        mContext=pcontext;
    }

    private class ViewHolder{
        TextView tv_name;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder=new ViewHolder();
            convertView=getInflater().inflate(R.layout.activity_search_listview_item, parent,false);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_commodity_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final SearchObject.DataEntity entity = mList.get(position);
        viewHolder.tv_name.setText(entity.getProductName());

        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, SortDetailsActivity.class);
                Bundle bundle=new Bundle();
                String uri = UriManager.getCategorySortUri(entity.getProductTypeId(), UriManager.ORDER.normal);

                bundle.putString(SortDetailsActivity.COME_FROM, SortDetailsActivity.CATEGORY_UI);
                bundle.putInt(SortDetailsActivity.PRODUCT_TYPE_ID, entity.getProductTypeId());
                bundle.putString(SortDetailsActivity.URI, uri);

                intent.putExtras(bundle);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            }
        });

        return convertView;
    }
}
