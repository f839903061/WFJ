package hy.zc.wfj.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/11.
 */
public class OrderAdapter extends BBaseAdapter {
    private Context mContext;
    private List<OrderListObject.DataEntity> mList;

    public OrderAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mList = plist;
        mContext = pcontext;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.activity_order_listview_item, null);
            viewHolder.imgb_delete = (ImageButton) convertView.findViewById(R.id.imgb_delete);
            viewHolder.sdv_pic0 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic);
            viewHolder.sdv_pic1 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic1);
            viewHolder.sdv_pic2= (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic2);
            viewHolder.sdv_pic3 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic3);
            viewHolder.tv_commodity_name = (TextView) convertView.findViewById(R.id.tv_commodity_name);
            viewHolder.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OrderListObject.DataEntity entity = mList.get(position);
        List<OrderListObject.DataEntity.ListEntity> list = entity.getList();

//        每个订单中的图片布局，区分一个或者多个
        if (list.size() == 1) {//如果只有一个内容的布局
            OrderListObject.DataEntity.ListEntity listEntity = list.get(0);
            viewHolder.tv_shop_name.setText(listEntity.getShopName());
            Uri uri = UriManager.getCategoryPicUri(listEntity.getLogoImage());
            viewHolder.sdv_pic0.setImageURI(uri);
            viewHolder.tv_commodity_name.setText(listEntity.getProductFullName());
            viewHolder.tv_price.setText(listEntity.getSalesPrice() + "");
        } else if (list.size() > 1) {//如果有多个内容的布局
            float price = 0;
            for (int i = 0; i < list.size(); i++) {//次循环里还要处理多张图片，目前还没做
                Uri uri = UriManager.getCategoryPicUri(list.get(0).getLogoImage());
                switch (i){
                    case 0:
                        viewHolder.sdv_pic0.setImageURI(uri);
                        viewHolder.sdv_pic0.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        viewHolder.sdv_pic1.setImageURI(uri);
                        viewHolder.sdv_pic1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        viewHolder.sdv_pic2.setImageURI(uri);
                        viewHolder.sdv_pic2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        viewHolder.sdv_pic3.setImageURI(uri);
                        viewHolder.sdv_pic3.setVisibility(View.VISIBLE);
                        break;
                }

                price += list.get(i).getSalesPrice();
            }
            viewHolder.tv_shop_name.setText(list.get(0).getShopName());
            viewHolder.tv_commodity_name.setVisibility(View.GONE);
            viewHolder.tv_price.setText(price + "");
        }

        viewHolder.imgb_delete.setTag(position);

        viewHolder.imgb_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("delete");
            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageButton imgb_delete;
        SimpleDraweeView sdv_pic0,sdv_pic1,sdv_pic2,sdv_pic3;
        TextView tv_commodity_name;
        TextView tv_shop_name;
        TextView tv_price;
    }
}
