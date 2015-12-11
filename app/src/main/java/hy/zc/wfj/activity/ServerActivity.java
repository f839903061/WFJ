package hy.zc.wfj.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hy.zc.wfj.R;

public class ServerActivity extends FrameActivity {

    private ImageButton common_title_back_btn;
    private TextView tv_title;

    private LinearLayout layout_feedback;
    private ImageView img_icon;
    private TextView tv_name;
    private TextView tv_hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        initializeComponent();
    }

    private void initializeComponent() {
        common_title_back_btn = (ImageButton) findViewById(R.id.common_title_back_btn);
        tv_title = (TextView) findViewById(R.id.common_title_txt);
        tv_title.setText("服务与反馈");
        common_title_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });


        layout_feedback=(LinearLayout)findViewById(R.id.include_feedback);
        img_icon=(ImageView)layout_feedback.findViewById(R.id.imgv_icon);
        tv_name=(TextView)layout_feedback.findViewById(R.id.tv_name);
        tv_hint=(TextView)layout_feedback.findViewById(R.id.tv_hint);

        img_icon.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.personal_icon_feedback));
        tv_name.setText("意见反馈");
        tv_hint.setText("产品功能建议与反馈");
        layout_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServerActivity.this,FeedBackActivity.class);
                startActivity(intent);
            }
        });
    }
}
