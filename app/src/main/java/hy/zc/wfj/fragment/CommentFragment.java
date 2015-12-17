package hy.zc.wfj.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hy.zc.wfj.data.OrderDataObject;

/**
 * Created by feng on 2015/12/15.
 */
public class CommentFragment extends OrderFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setType(OrderDataObject.TITLE_COMMENT_FLAG);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
