package hy.zc.wfj.fragment.base;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import hy.zc.wfj.utility.LogUtil;

/**
 * Created by feng on 2015/11/23.
 */
public class BaseFragment extends Fragment implements LogUtil{


    public void showToast(String ptext) {
        Toast.makeText(getActivity().getApplicationContext(), ptext, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLogi(String ptext) {
        Log.i(TAG,getClass().getName()+" "+ptext);
    }

    @Override
    public void showLogd(String ptext) {
        Log.d(TAG,getClass().getName()+" "+ptext);
    }

    @Override
    public void showLoge(String ptext) {
        Log.e(TAG,getClass().getName()+" "+ptext);
    }

    @Override
    public void showLogw(String ptext) {
        Log.w(TAG,getClass().getName()+" "+ ptext);
    }
}
