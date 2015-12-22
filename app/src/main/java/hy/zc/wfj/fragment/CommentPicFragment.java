package hy.zc.wfj.fragment;


import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.CommentAdapter;
import hy.zc.wfj.customview.MyListView;
import hy.zc.wfj.data.OrderListObject;


public class CommentPicFragment extends FrameFragment {


    private RatingBar ratingBar_package;
    private RatingBar ratingBar_speed;
    private RatingBar ratingBar_sender;
    private Button btn_commit;
    private MyListView lv_order;
    OrderListObject.DataEntity mEntity;

    public CommentPicFragment(OrderListObject.DataEntity entity) {
        mEntity = entity;

    }

    public CommentPicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_comment_pic, container, false);
        initializeComption(rootView);
        return rootView;
    }

    private void initializeComption(View rootView) {
        setRatingBarColor(rootView);
        lv_order = (MyListView) rootView.findViewById(R.id.lv_order);
        btn_commit=(Button)rootView.findViewById(R.id.btn_commit);

        CommentAdapter commentAdapter = new CommentAdapter(getActivity(), mEntity.getList());
        lv_order.setAdapter(commentAdapter);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = (int)ratingBar_package.getRating();
                showToast(""+rating);
            }
        });

    }

    /**
     * 此处仅仅是设置了星星的颜色，并不做数据处理
     *
     * @param rootView
     */
    private void setRatingBarColor(View rootView) {
        ratingBar_package = (RatingBar) rootView.findViewById(R.id.ratingBar_package);
        ratingBar_speed = (RatingBar) rootView.findViewById(R.id.ratingBar_speed);
        ratingBar_sender = (RatingBar) rootView.findViewById(R.id.ratingBar_sender);

        LayerDrawable stars_package = (LayerDrawable) ratingBar_package.getProgressDrawable();
        LayerDrawable stars_speed = (LayerDrawable) ratingBar_speed.getProgressDrawable();
        LayerDrawable stars_sender = (LayerDrawable) ratingBar_sender.getProgressDrawable();

        stars_package.getDrawable(2).setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_ATOP);
        stars_speed.getDrawable(2).setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_ATOP);
        stars_sender.getDrawable(2).setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_ATOP);
    }

}
