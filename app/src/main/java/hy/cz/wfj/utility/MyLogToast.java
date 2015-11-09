package hy.cz.wfj.utility;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by feng on 2015/11/9.
 */
public class MyLogToast {
    public static final String TAG = "fengluchun";
    private Context mContext;
    public MyLogToast(Context pcontext) {
        mContext=pcontext;
    }
    public void mToast(String ptext){
        Toast.makeText(mContext,"-->"+ptext+"<--",Toast.LENGTH_SHORT).show();
    }
    public void mLog(String ptext){
        Log.e(TAG,"---->"+mContext.getClass().getName()+">>>"+ptext);
    }
}
