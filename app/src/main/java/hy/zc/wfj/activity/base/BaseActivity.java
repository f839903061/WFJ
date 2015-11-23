package hy.zc.wfj.activity.base;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * 这个类的里面封装了一些通用的功能
 * Created by feng on 2015/11/23.
 */
public class BaseActivity extends Activity {

    public static String TAG="fengluchun";

    public void showLogi(String ptext){
        Log.i(TAG,ptext);
    }
    public void showLogd(String ptext){
        Log.i(TAG,ptext);
    }
    public void showLoge(String ptext){
        Log.i(TAG,ptext);
    }
    public void showLogw(String ptext){
        Log.w(TAG, ptext);
    }
    public void showToast(String ptext){
        Toast.makeText(getApplicationContext(),ptext,Toast.LENGTH_LONG).show();
    }
}
