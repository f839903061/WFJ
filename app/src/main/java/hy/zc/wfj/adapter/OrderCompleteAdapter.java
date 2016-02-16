package hy.zc.wfj.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.ReturnSalesActivity;
import hy.zc.wfj.activity.TemplateActivity;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/17.
 */
public class OrderCompleteAdapter extends BBaseAdapter {
    private Context mContext;
    private List<OrderListObject.DataEntity.ListEntity> mList=new ArrayList<>();
    public OrderCompleteAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mContext=pcontext;
        mList=plist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            viewHolder=new ViewHolder();
            convertView=getInflater().inflate(R.layout.fragment_order_complete_list_item,parent,false);
            viewHolder.sdv_pic=(SimpleDraweeView)convertView.findViewById(R.id.sdv_pic);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price=(TextView)convertView.findViewById(R.id.tv_price);
            viewHolder.tv_count=(TextView)convertView.findViewById(R.id.tv_count);
            viewHolder.btn_returnSales=(Button)convertView.findViewById(R.id.btn_tmp);

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final OrderListObject.DataEntity.ListEntity entity = mList.get(position);
        viewHolder.sdv_pic.setImageURI(UriManager.getCategoryPicUri(entity.getLogoImage()));
        viewHolder.tv_name.setText(entity.getProductFullName());
        viewHolder.tv_price.setText("￥"+entity.getSalesPrice());
        viewHolder.tv_count.setText("X"+entity.getCount());
        viewHolder.btn_returnSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ReturnSalesActivity.class);
                Bundle bundle=new Bundle();
                OrderDataObject odo=new OrderDataObject();

                odo.setTitle(OrderDataObject.TITLE_RETURN_SALES);
                bundle.putSerializable(OrderDataObject.TITLE_KEY, odo);
                bundle.putSerializable(OrderDataObject.SINGLE_ORDER_KEY, entity);
                intent.putExtras(bundle);

                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            }
        });

        return convertView;
    }
    private class ViewHolder{
        SimpleDraweeView sdv_pic;
        TextView tv_name;
        TextView tv_price;
        TextView tv_count;
        Button btn_returnSales;
    }
}
