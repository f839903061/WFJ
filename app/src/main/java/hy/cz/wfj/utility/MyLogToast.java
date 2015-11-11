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

    public static void mToast(Context pcontext,String ptext) {
        Toast.makeText(pcontext, "-->" + ptext + "<--", Toast.LENGTH_SHORT).show();
    }

    public static void mLog(Context pcontext,String ptext) {
        Log.e(TAG, "---->" + pcontext.getClass().getName() + ">>>" + ptext);
    }

    public static void mLog(Context pcontext,int plevel, String ptext) {
        switch (plevel) {
            case LEVEL_INFO:
                Log.i(TAG, "---->" + pcontext.getClass().getName() + ">>>" + ptext);
                break;
            case LEVEL_DEBUG:
                Log.w(TAG, "---->" + pcontext.getClass().getName() + ">>>" + ptext);
                break;
            case LEVEL_ERROR:
                Log.e(TAG, "---->" + pcontext.getClass().getName() + ">>>" + ptext);
                break;
            default:

                break;
        }
    }
}
