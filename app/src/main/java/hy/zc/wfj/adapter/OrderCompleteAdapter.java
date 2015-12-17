package hy.zc.wfj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.base.BBaseAdapter;
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
            convertView=getInflater().inflate(R.layout.fragment_order_complete_list_item,null);
            viewHolder.sdv_pic=(SimpleDraweeView)convertView.findViewById(R.id.sdv_pic);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price=(TextView)convertView.findViewById(R.id.tv_price);
            viewHolder.tv_count=(TextView)convertView.findViewById(R.id.tv_count);

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        OrderListObject.DataEntity.ListEntity entity = mList.get(position);
        viewHolder.sdv_pic.setImageURI(UriManager.getCategoryPicUri(entity.getLogoImage()));
        viewHolder.tv_name.setText(entity.getProductFullName());
        viewHolder.tv_price.setText("￥"+entity.getSalesPrice());
        viewHolder.tv_count.setText("X"+entity.getCount());

        return convertView;
    }
    private class ViewHolder{
        SimpleDraweeView sdv_pic;
        TextView tv_name;
        TextView tv_price;
        TextView tv_count;
    }
}
