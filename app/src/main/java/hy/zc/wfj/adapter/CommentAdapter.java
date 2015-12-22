package hy.zc.wfj.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/18.
 */
public class CommentAdapter extends BBaseAdapter {
    private Context mContext;
    private List<OrderListObject.DataEntity.ListEntity> mList = new ArrayList();

    public CommentAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mContext = pcontext;
        mList = plist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.fragment_comment_list_item, null);
            viewHolder.sdv_pic = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.ratingBar_level = (RatingBar) convertView.findViewById(R.id.ratingBar_level);
            viewHolder.et_comment = (EditText) convertView.findViewById(R.id.et_comment);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OrderListObject.DataEntity.ListEntity entity = mList.get(position);
        viewHolder.sdv_pic.setImageURI(UriManager.getCategoryPicUri(entity.getLogoImage()));
        viewHolder.tv_name.setText(entity.getProductFullName());

        LayerDrawable stars = (LayerDrawable)viewHolder.ratingBar_level.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(mContext.getResources().getColor(R.color.pink) , PorterDuff.Mode.SRC_ATOP);
        String comment_text = viewHolder.et_comment.getText().toString();

        return convertView;
    }

    private class ViewHolder {
        SimpleDraweeView sdv_pic;
        TextView tv_name;
        RatingBar ratingBar_level;
        EditText et_comment;
    }

}
