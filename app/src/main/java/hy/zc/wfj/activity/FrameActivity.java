package hy.zc.wfj.activity;

import android.app.Activity;
import android.content.Intent;

import hy.zc.wfj.activity.base.BaseActivity;

/**
 * Created by feng on 2015/11/23.
 */
public class FrameActivity extends BaseActivity {



    public void goBack(){
        Intent goBack = getIntent();
        setResult(Activity.RESULT_OK,goBack);
        finish();
    }
}
