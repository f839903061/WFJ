package hy.zc.wfj.fragment;


import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.adapter.CommentAdapter;
import hy.zc.wfj.customview.MyListView;
import hy.zc.wfj.data.CommentObject;
import hy.zc.wfj.data.MultipartEntity;
import hy.zc.wfj.data.MultipartRequest;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;


public class CommentPicFragment extends FrameFragment {


    public static final String IS_COMMENT = "isComment";
    private RatingBar ratingBar_package;
    private RatingBar ratingBar_speed;
    private RatingBar ratingBar_sender;
    private Button btn_commit;
    private MyListView lv_order;
    OrderListObject.DataEntity mEntity;


    private CommentObject commentObject;
    private List<CommentObject.EvaluateGoodsEntity> mTempList=new ArrayList<>();

    private CommentAdapter mCommentAdapter;

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
        commentObject = new CommentObject();
        setRatingBarColor(rootView);
        lv_order = (MyListView) rootView.findViewById(R.id.lv_order);
        btn_commit = (Button) rootView.findViewById(R.id.btn_commit);

        mCommentAdapter = new CommentAdapter(getActivity(), mEntity.getList());
        lv_order.setAdapter(mCommentAdapter);


        //提交评论信息
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<Integer, CommentObject.EvaluateGoodsEntity> commentMap = mCommentAdapter.getCommentContext();
                if (commentMap != null) {

                    for (int i = 0; i < commentMap.size(); i++) {
                        mTempList.add(commentMap.get(i));
                    }
//                    commentObject.setAllCommentContext(mTempList);
                    String temp = (String) SharedPrefUtility.getParam(getActivity(), SharedPrefUtility.LOGIN_DATA, "");
                    if (!temp.equals("")) {
                        UserLoginObject object = JSON.parseObject(temp, UserLoginObject.class);

                        commentObject.setCustomerId(object.getData().getCustomerId());
                        commentObject.setDate("2015");//因为服务器端不处理date，ios端又有这个数据，所以此处随便给了一个数据
                        commentObject.setOrdersId(mEntity.getOrdersId());
                        commentObject.setEvaluateGoods(mTempList);
                        String commentMessage = JSON.toJSONString(commentObject);
                        //将接收到的字符串，修改一下，将"allCommentContext"改为当前界面的ordersId
//                        String replace = commentMessage.replace("\"allCommentContext\"", mEntity.getOrdersId() + "");
                        showLogi(commentMessage);
                        String uri = UriManager.getComment();
                        sendDataToServer(uri,commentMessage);
                    }
                }
            }
        });

    }

    private void sendDataToServer(String uri,String pCommentObject) {

        MultipartRequest multipartRequest=new MultipartRequest(uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showToast("success!!!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoge(error.getMessage());
            }
        });

        MultipartEntity entity=multipartRequest.getMultiPartEntity();
        entity.addStringPart("evaluateGoods",pCommentObject);

        App.addRequest(multipartRequest, IS_COMMENT);
    }

    @Override
    public void onStop() {
        App.cancelAllRequests(IS_COMMENT);
        super.onStop();
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
