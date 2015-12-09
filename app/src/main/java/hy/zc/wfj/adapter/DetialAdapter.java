package hy.zc.wfj.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.SearchObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/8.
 */
public class DetialAdapter extends BBaseAdapter {

    private List<SearchObject.DataEntity> mList =new ArrayList<>();
    public DetialAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mList=plist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= getInflater().inflate(R.layout.activity_commodity_listview_item, null);
            viewHolder.img=(SimpleDraweeView)convertView.findViewById(R.id.img_detail);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price=(TextView)convertView.findViewById(R.id.tv_price);
            viewHolder.tv_people_count=(TextView)convertView.findViewById(R.id.tv_people_count);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SearchObject.DataEntity entity = mList.get(position);
        Uri uri = UriManager.getCategoryPicUri(entity.getLogoImg());
        viewHolder.img.setImageURI(uri);
        viewHolder.tv_name.setText(entity.getProductName());
        viewHolder.tv_price.setText("￥:"+entity.getSalesPrice());
        viewHolder.tv_people_count.setText(entity.getTotalSales()+"人");

        return convertView;
    }
    public class ViewHolder{
        SimpleDraweeView img;
        TextView tv_name;
        TextView tv_price;
        TextView tv_people_count;
    }
}
