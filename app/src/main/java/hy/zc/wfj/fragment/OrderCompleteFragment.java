package hy.zc.wfj.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hy.zc.wfj.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderCompleteFragment extends Fragment {


    public OrderCompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_complete, container, false);
    }

}
