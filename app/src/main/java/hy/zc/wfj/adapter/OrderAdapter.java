package hy.zc.wfj.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.TemplateActivity;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/11.
 */
public class OrderAdapter extends BBaseAdapter {
    private Context mContext;
    private List<OrderListObject.DataEntity> mList;
    private DelCallBack mDelCallBack;
    private int mType = 0;

    public OrderAdapter(Context pcontext, List plist, DelCallBack pdelCallBack, int pType) {
        super(pcontext, plist);
        mList = plist;
        mContext = pcontext;
        mDelCallBack = pdelCallBack;
        mType = pType;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.activity_order_listview_item, null);
            viewHolder.imgb_delete = (ImageButton) convertView.findViewById(R.id.imgb_delete);
            viewHolder.sdv_pic0 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic);
            viewHolder.sdv_pic1 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic1);
            viewHolder.sdv_pic2 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic2);
            viewHolder.sdv_pic3 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic3);
            viewHolder.sdv_finish = (SimpleDraweeView) convertView.findViewById(R.id.sdv_finsih);
            viewHolder.btn_first = (Button) convertView.findViewById(R.id.btn_first);
            viewHolder.btn_second = (Button) convertView.findViewById(R.id.btn_second);
            viewHolder.tv_commodity_name = (TextView) convertView.findViewById(R.id.tv_commodity_name);
            viewHolder.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OrderListObject.DataEntity entity = mList.get(position);
//         设置“完成”印章图标是否显示
        int ordersState = entity.getOrdersState();
        if (ordersState == 9) {
            viewHolder.sdv_finish.setVisibility(View.VISIBLE);
        } else if (ordersState == 3 || ordersState == 4) {
            viewHolder.btn_first.setVisibility(View.VISIBLE);
            viewHolder.btn_first.setText("确认收货");

            viewHolder.imgb_delete.setVisibility(View.GONE);
        } else if (ordersState == 1) {
            viewHolder.btn_first.setVisibility(View.VISIBLE);
            viewHolder.btn_first.setText("待支付");
            viewHolder.btn_second.setVisibility(View.GONE);
        } else if (ordersState == 5) {
            viewHolder.btn_first.setVisibility(View.VISIBLE);
            viewHolder.btn_first.setText("晒单评价");
            viewHolder.btn_second.setVisibility(View.VISIBLE);
            viewHolder.btn_second.setText("再次购买");
        } else if (ordersState == 7) {//返修退换
            viewHolder.imgb_delete.setVisibility(View.VISIBLE);
            viewHolder.sdv_finish.setVisibility(View.INVISIBLE);

            viewHolder.btn_first.setVisibility(View.GONE);
            viewHolder.btn_second.setVisibility(View.GONE);
        } else {
            viewHolder.btn_first.setVisibility(View.GONE);
            viewHolder.btn_second.setVisibility(View.GONE);

            viewHolder.imgb_delete.setVisibility(View.VISIBLE);
            viewHolder.sdv_finish.setVisibility(View.INVISIBLE);
        }

        viewHolder.btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                showToast(button.getText() + "");
                switch (mType) {
                    case OrderDataObject.TITLE_PAY_FLAG:
                        break;
                    case OrderDataObject.TITLE_SIGN_FLAG:
                        break;
                    case OrderDataObject.TITLE_COMMENT_FLAG:
//                        Intent intent = new Intent(mContext, TemplateActivity.class);
//                        Bundle bundle = new Bundle();
//                        OrderDataObject orderDataObject = new OrderDataObject();
//                        orderDataObject.setTitle(OrderDataObject.TITLE_COMMENT_PIC);
//                        bundle.putSerializable(OrderDataObject.TITLE_KEY, orderDataObject);
//                        intent.putExtras(bundle);
//                        mContext.startActivity(intent);
                        gotoOrderDetailUI(position);
                        break;
                    case OrderDataObject.TITLE_AFTER_SALE_FLAG:
                        break;
                    case OrderDataObject.TITLE_ALL_FLAG:
                        break;
                }
            }
        });

        viewHolder.btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                showToast(button.getText() + "");
            }
        });

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
                switch (i) {
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
                mDelCallBack.del(position);
            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageButton imgb_delete;
        SimpleDraweeView sdv_pic0, sdv_pic1, sdv_pic2, sdv_pic3, sdv_finish;
        TextView tv_commodity_name;
        TextView tv_shop_name;
        TextView tv_price;
        Button btn_first, btn_second;
    }

    /**
     * 回调函数，在fragment中执行删除操作
     */
    public interface DelCallBack {
        public void del(int position);
    }

    /**
     * 跳转到对应的单独的订单说明界面
     *
     * @param position
     */
    public void gotoOrderDetailUI(int position) {
        Intent intent = new Intent(mContext, TemplateActivity.class);
        Bundle bundle = new Bundle();

        OrderDataObject dataObject = new OrderDataObject();
        OrderListObject.DataEntity entity = mList.get(position);
        switch (mType) {
            case OrderDataObject.TITLE_ALL_FLAG:
                dataObject.setTitle(OrderDataObject.TITLE_COMPLETE);
                break;
            case OrderDataObject.TITLE_COMMENT_FLAG:
                dataObject.setTitle(OrderDataObject.TITLE_COMMENT_PIC);
                break;
        }
        bundle.putSerializable(OrderDataObject.TITLE_KEY, dataObject);
        bundle.putSerializable(OrderDataObject.SINGLE_ORDER_KEY, entity);

        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

}
