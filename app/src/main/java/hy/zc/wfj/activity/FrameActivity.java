package hy.zc.wfj.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.ProgressBar;

import hy.zc.wfj.activity.base.BaseActivity;

/**
 * Created by feng on 2015/11/23.
 */
public class FrameActivity extends BaseActivity {


    /**
     * 弹出提示对话框，自带延时关闭
     * @param pText
     *          提示内容
     * @param pDelay
     *          延时关闭时间
     */
    public void showProgressDialog(String pText, final int pDelay){
        final ProgressDialog pd=new ProgressDialog(FrameActivity.this);
        pd.setMessage(pText);
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(pDelay*1000);
                    pd.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void goBack(){
        Intent goBack = getIntent();
        setResult(Activity.RESULT_OK,goBack);
        finish();
    }
}
