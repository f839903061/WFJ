package hy.zc.wfj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.ConcernShopObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/11.
 */
public class ConcernShopAdapter extends BBaseAdapter {

    private Context mContext;
    private List<ConcernShopObject.DataEntity> mList;
    public ConcernShopAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mContext=pcontext;
        mList=plist;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder=new ViewHolder();
            convertView=getInflater().inflate(R.layout.activity_concern_listview_item,null);
            viewHolder.sdv_pic=(SimpleDraweeView)convertView.findViewById(R.id.img_detail);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_commodity_name);
            viewHolder.tv_address=(TextView)convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ConcernShopObject.DataEntity entity = mList.get(position);
        viewHolder.sdv_pic.setImageURI(UriManager.getCategoryPicUri(entity.getLogoUrl()));
        viewHolder.tv_name.setText(entity.getShopName());
        viewHolder.tv_address.setText(entity.getAddress());

        return convertView;
    }

    /**
     * 当点击商铺的时候，跳转到商品详情页面
     * @param pposition
     */
    public void gotoDetial(int pposition) {

    }

    private class ViewHolder{
        SimpleDraweeView sdv_pic;
        TextView tv_name;
        TextView tv_address;
    }
}
