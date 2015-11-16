package hy.zc.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import hy.zc.wfj.R;

public class MyMessageActivity extends Activity implements View.OnClickListener {

    private ImageButton common_title_back_btn;
    private ImageButton message_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        initializeComponent();
        setListener();
    }

    private void setListener() {
        common_title_back_btn.setOnClickListener(this);
        message_setting.setOnClickListener(this);
    }

    private void initializeComponent() {
        common_title_back_btn=(ImageButton)findViewById(R.id.common_title_back_btn);
        message_setting =(ImageButton)findViewById(R.id.personal_setting);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_title_back_btn:
                Intent gobackintent=getIntent();
                setResult(Activity.RESULT_OK,gobackintent);
                finish();
                break;
            case R.id.personal_setting:
                break;
            default:
                break;
        }
    }
}
