package hy.zc.wfj.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.CommentObject;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/18.
 */
public class CommentAdapter extends BBaseAdapter {
    private Context mContext;
    private List<OrderListObject.DataEntity.ListEntity> mList = new ArrayList();
    private HashMap<Integer,CommentObject.EvaluateGoodsEntity> mCommentHashMap=new HashMap<>();

    public CommentAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mContext = pcontext;
        mList = plist;
        //准备好格式好的hashmap,以备填充数据
        for (int i = 0; i < mList.size(); i++) {
            CommentObject.EvaluateGoodsEntity oe=new CommentObject.EvaluateGoodsEntity();
            mCommentHashMap.put(i,oe);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.fragment_comment_list_item, parent,false);
            viewHolder.sdv_pic = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.ratingBar_level = (RatingBar) convertView.findViewById(R.id.ratingBar_level);
            viewHolder.et_comment = (EditText) convertView.findViewById(R.id.et_comment);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ratingBar_level.setTag(position);
        viewHolder.et_comment.setTag(position);


        OrderListObject.DataEntity.ListEntity entity = mList.get(position);
        viewHolder.sdv_pic.setImageURI(UriManager.getCategoryPicUri(entity.getLogoImage()));
        viewHolder.tv_name.setText(entity.getProductFullName());

        LayerDrawable stars = (LayerDrawable)viewHolder.ratingBar_level.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(mContext.getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_ATOP);
        //保存DetailID
        CommentObject.EvaluateGoodsEntity evaluateGoodsEntity = mCommentHashMap.get(position);
        evaluateGoodsEntity.setOrderDetailId(entity.getOrderDetailId());
        mCommentHashMap.put(position,evaluateGoodsEntity);

        //保存星级
        viewHolder.ratingBar_level.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int index = (int) ratingBar.getTag();
                CommentObject.EvaluateGoodsEntity evaluateGoodsEntity1 = mCommentHashMap.get(index);
                evaluateGoodsEntity1.setLevel((int) rating);
                mCommentHashMap.put(index,evaluateGoodsEntity1);
            }
        });
        //保存评价信息
        viewHolder.et_comment.addTextChangedListener(new MyCommentTextWatcher(position));
        return convertView;
    }

    private class ViewHolder {
        SimpleDraweeView sdv_pic;
        TextView tv_name;
        RatingBar ratingBar_level;
        EditText et_comment;
    }

    private class MyCommentTextWatcher implements TextWatcher{

        private int mIndex;

        public MyCommentTextWatcher(int pIndex) {
            mIndex=pIndex;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        /**
         * 将评论信息保存起来
         * 我觉得此处的性能不好，如果有监听edittext完全不输入才保存就比较好了！可惜没有这种接口
         * @param s
         */
        @Override
        public void afterTextChanged(Editable s) {
            CommentObject.EvaluateGoodsEntity evaluateGoodsEntity = mCommentHashMap.get(mIndex);
            evaluateGoodsEntity.setContent(s.toString());
            mCommentHashMap.put(mIndex,evaluateGoodsEntity);
        }
    }

    /**
     * 反馈将评价的内容以及星级
     * @return
     */
    public HashMap<Integer,CommentObject.EvaluateGoodsEntity> getCommentContext(){

        if (mCommentHashMap.size()>0){
            return mCommentHashMap;
        }
        return null;
    }

}
