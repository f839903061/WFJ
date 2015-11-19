package hy.zc.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hy.zc.wfj.R;

public class PersonalInfoActivity extends Activity implements View.OnClickListener{

    private TextView common_title_txt;
    private ImageButton common_title_back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initializeComponent();
        setListener();
    }

    private void setListener() {
        common_title_back_btn.setOnClickListener(this);
    }

    private void initializeComponent() {
        common_title_txt=(TextView)findViewById(R.id.common_title_txt);
        common_title_txt.setText("我的账户");

        common_title_back_btn=(ImageButton)findViewById(R.id.common_title_back_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_title_back_btn:
                goBack();
                break;
            default:
                break;
        }
    }

    /**
     * 返回刚才的Activity
     */
    private void goBack() {
        Intent goBackIntent=getIntent();
        setResult(Activity.RESULT_OK,goBackIntent);
        finish();
    }
}
