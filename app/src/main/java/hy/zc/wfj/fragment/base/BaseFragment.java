package hy.zc.wfj.fragment.base;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by feng on 2015/11/23.
 */
public class BaseFragment extends Fragment {
    public static String TAG = "fengluchun";

    public void showLogi(String ptext) {
        Log.i(TAG, getClass().getName() + " " + ptext);
    }

    public void showLogd(String ptext) {
        Log.i(TAG, getClass().getName() + " " + ptext);
    }

    public void showLoge(String ptext) {
        Log.i(TAG, getClass().getName() + " " + ptext);
    }

    public void showLogw(String ptext) {
        Log.w(TAG, getClass().getName() + " " + ptext);
    }

    public void showToast(String ptext) {
        Toast.makeText(getActivity(), ptext, Toast.LENGTH_LONG).show();
    }
}
