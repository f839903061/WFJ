package hy.cz.wfj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.drawee.backends.pipeline.Fresco;

import hy.cz.wfj.Main2Activity;
import hy.cz.wfj.R;

public class MyLoginActivity extends Activity implements View.OnClickListener{

    public static final String TAG = "fengluchun";

    private ImageButton mBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MyLoginActivity.this);
        setContentView(R.layout.activity_my_login);

        initializeComponent();

    }

    private void initializeComponent() {
        mBackBtn=(ImageButton)this.findViewById(R.id.common_title_back_btn);

        mBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_title_back_btn:
                Intent intent=new Intent(MyLoginActivity.this, Main2Activity.class);
                setResult(Activity.RESULT_OK,intent);
                finish();
        }
    }
}
