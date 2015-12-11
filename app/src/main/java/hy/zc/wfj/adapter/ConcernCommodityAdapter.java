package hy.zc.wfj.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.DetailActivity;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.ConcernCommodityObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/11.
 */
public class ConcernCommodityAdapter extends BBaseAdapter {
    private Context mContext;
    private List<ConcernCommodityObject.DataEntity> mList = new ArrayList();


    public ConcernCommodityAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mList = plist;
        mContext = pcontext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.activity_concern_listview_item, null);
            viewHolder.sdv_pic = (SimpleDraweeView) convertView.findViewById(R.id.img_detail);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ConcernCommodityObject.DataEntity entity = mList.get(position);
        Uri uri = UriManager.getCategoryPicUri(entity.getLogoUrl());

        viewHolder.sdv_pic.setImageURI(uri);
        viewHolder.tv_name.setText(entity.getProductName());
        viewHolder.tv_price.setText("￥:" + entity.getSalesPrice());

        return convertView;
    }

    /**
     * 当点击商品的时候，跳转到商品详情页面
     * @param pposition
     */
    public void gotoDetial(int pposition) {
        ConcernCommodityObject.DataEntity entity = mList.get(pposition);
        Bundle bundle = new Bundle();
        bundle.putInt(DetailActivity.PRODUCTID, entity.getProductId());

        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    private class ViewHolder {
        SimpleDraweeView sdv_pic;
        TextView tv_name;
        TextView tv_price;
    }
}
