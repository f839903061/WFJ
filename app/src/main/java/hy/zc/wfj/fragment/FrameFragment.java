package hy.zc.wfj.fragment;

import android.content.Intent;
import android.os.Bundle;

import hy.zc.wfj.fragment.base.BaseFragment;

/**
 * Created by feng on 2015/11/23.
 */
public class FrameFragment extends BaseFragment {

    /**
     * 需要返回结果的跳转
     *
     * @param pclass
     * @param ptag
     * @param pbundle
     */
    public void goToActivityForResult(Class pclass, int ptag, Bundle pbundle) {
        Intent goIntent = new Intent(getActivity(), pclass);
        if (pbundle != null) {

            goIntent.putExtras(pbundle);
        }
        startActivityForResult(goIntent, ptag);
    }

    /**
     * 直接跳转
     *
     * @param pclass
     * @param pbundle
     */
    public void goToActivity(Class pclass, Bundle pbundle) {
        Intent goIntent = new Intent(getActivity(), pclass);
        if (pbundle != null) {
            goIntent.putExtras(pbundle);
        }
        startActivity(goIntent);
    }

    /**
     * 此处是导航栏下面的四个主要fragment的调用接口，区分看名称即可
     */
    public interface OnFragmentInteractiveListener {
        public void homeDo(int action);
        public void categoryDo();
        public void cartDo();
        public void personalDo();
    }

}
