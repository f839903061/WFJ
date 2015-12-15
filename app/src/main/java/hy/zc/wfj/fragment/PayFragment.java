package hy.zc.wfj.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PayFragment extends OrderFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setType(Type_Pay);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
