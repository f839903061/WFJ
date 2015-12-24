package hy.zc.wfj.pay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hy.zc.wfj.R;
import hy.zc.wfj.fragment.FrameFragment;

public class ExternalFragment extends FrameFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pay_external, container, false);
	}
}
