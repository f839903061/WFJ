package hy.zc.wfj.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by feng on 2015/12/15.
 */
public class SignFragment extends OrderFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setType(Type_Sign);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
