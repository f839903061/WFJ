package hy.zc.wfj.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hy.zc.wfj.data.OrderDataObject;

public class WaitPayFragment extends OrderFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setType(OrderDataObject.TITLE_WAIT_PAY_FLAG);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
