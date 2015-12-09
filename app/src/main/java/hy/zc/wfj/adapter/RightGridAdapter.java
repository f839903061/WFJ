package hy.zc.wfj.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.CommodityDetailsActivity;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.CategroyJsonObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/11/4.
 */
public class RightGridAdapter extends BBaseAdapter {

    private Context mContext;
    private List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> mList=new ArrayList<>();

    public RightGridAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mContext=pcontext;
        mList=plist;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.category_right_grid_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.category_item_have_picture_text_3);
            viewHolder.simpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.category_item_have_picture_image_3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final CategroyJsonObject.DataEntity.ChildProductTypeEntity entity = mList.get(position);

        viewHolder.textView.setText(entity.getSortName());
        //拼接好图片之后，使用fresco来加载显示
        Uri uri = UriManager.getCategoryPicUri(entity.getCategoryImage());
        viewHolder.simpleDraweeView.setImageURI(uri);
        viewHolder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommodityDetails();
            }

            private void openCommodityDetails() {
                Intent intent = new Intent();
                intent.setClass(mContext, CommodityDetailsActivity.class);
                String uri = UriManager.getCategorySortUri(entity.getProductTypeId(), UriManager.ORDER.normal);

                Bundle bundle = new Bundle();
                bundle.putString(CommodityDetailsActivity.COME_FROM,CommodityDetailsActivity.CATEGORY_UI);
                bundle.putString(CommodityDetailsActivity.URI, uri);
                bundle.putInt(CommodityDetailsActivity.PRODUCT_TYPE_ID, entity.getProductTypeId());

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
