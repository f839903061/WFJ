package hy.cz.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import hy.cz.wfj.Main2Activity;
import hy.cz.wfj.R;

public class MySettingsActivity extends Activity implements View.OnClickListener{

    private ImageButton common_title_back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

        initializeComponent();
        setListener();
    }

    /**
     * set component click listener
     */
    private void setListener() {
        common_title_back_btn.setOnClickListener(this);
    }

    /**
     * initialize component
     */
    private void initializeComponent() {
        common_title_back_btn=(ImageButton)findViewById(R.id.common_title_back_btn);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_title_back_btn:
                Intent gobackIntent=new Intent(MySettingsActivity.this,Main2Activity.class);
                setResult(Activity.RESULT_OK,gobackIntent);
                finish();
                break;
            default:
                break;
        }
    }


}
