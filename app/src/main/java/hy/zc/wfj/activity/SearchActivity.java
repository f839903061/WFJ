package hy.zc.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import hy.zc.wfj.R;

public class SearchActivity extends FrameActivity implements View.OnClickListener{

    private LinearLayout home_search_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_search);

        initializeComponent();
    }

    private void initializeComponent() {
        home_search_button=(LinearLayout)findViewById(R.id.home_search_button);
        home_search_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_search_button:
                Intent goBack = getIntent();
                setResult(Activity.RESULT_OK,goBack);
                finish();
                break;
            default:
                break;
        }
    }
}
