package hy.zc.wfj.activity;

import android.app.Activity;
import android.os.Bundle;

import com.journeyapps.barcodescanner.CaptureActivity;

import hy.zc.wfj.R;

public class ScanActivity extends CaptureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

    }
}
