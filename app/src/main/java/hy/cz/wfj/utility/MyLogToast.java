package hy.cz.wfj.utility;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by feng on 2015/11/9.
 */
public class MyLogToast {
    public static final String TAG = "fengluchun";
    public static final int LEVEL_INFO = 1;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_ERROR = 3;
    private Context mContext;

    public MyLogToast(Context pcontext) {
        mContext = pcontext;
    }

    public void mToast(String ptext) {
        Toast.makeText(mContext, "-->" + ptext + "<--", Toast.LENGTH_SHORT).show();
    }

    public void mLog(String ptext) {
        Log.e(TAG, "---->" + mContext.getClass().getName() + ">>>" + ptext);
    }

    public void mLog(int plevel, String ptext) {
        switch (plevel) {
            case LEVEL_INFO:
                Log.i(TAG, "---->" + mContext.getClass().getName() + ">>>" + ptext);
                break;
            case LEVEL_DEBUG:
                Log.w(TAG, "---->" + mContext.getClass().getName() + ">>>" + ptext);
                break;
            case LEVEL_ERROR:
                Log.e(TAG, "---->" + mContext.getClass().getName() + ">>>" + ptext);
                break;
            default:

                break;
        }
    }
}
