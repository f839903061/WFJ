package hy.zc.wfj.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import hy.zc.wfj.R;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.fragment.AfterSaleFragment;
import hy.zc.wfj.fragment.CommentFragment;
import hy.zc.wfj.fragment.OrderCompleteFragment;
import hy.zc.wfj.fragment.OrderFragment;
import hy.zc.wfj.fragment.PayFragment;
import hy.zc.wfj.fragment.RegisterFragment;
import hy.zc.wfj.fragment.SignFragment;

public class TemplateActivity extends FrameActivity {

    private OrderDataObject orderDataObject;

    private ImageButton common_title_back_btn;
    private TextView tv_title;
    private TextView empty_view;

    private String uriString;
    private FragmentTransaction fragmentTransaction;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        initializeComponent();
//        setTitleAndData();
    }

    private void initializeComponent() {

        common_title_back_btn = (ImageButton) findViewById(R.id.common_title_back_btn);
        tv_title = (TextView) findViewById(R.id.common_title_txt);

        empty_view = (TextView) findViewById(R.id.empty_View);

        common_title_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });


    }

    @Override
    protected void onResume() {
        setTitleAndData();
        super.onResume();
    }

    private void setTitleAndData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orderDataObject = (OrderDataObject) bundle.getSerializable(OrderDataObject.TITLE_KEY);
            if (orderDataObject != null) {
                String title = orderDataObject.getTitle();
                tv_title.setText(title);
                /*uriString = orderDataObject.getUriString();
                if (uriString == null) {
                    empty_view.setVisibility(View.VISIBLE);
                } else {
                    empty_view.setVisibility(View.GONE);
                }*/
                if (title.equals(OrderDataObject.TITLE_ALL) ) {
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new OrderFragment());
                }else if (title.equals(OrderDataObject.TITLE_PAY)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new PayFragment());

                }else if (title.equals(OrderDataObject.TITLE_SIGN)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new SignFragment());

                }else if (title.equals(OrderDataObject.TITLE_COMMENT)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new CommentFragment());

                }else if (title.equals(OrderDataObject.TITLE_AFTER_SALE)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new AfterSaleFragment());
                }else if (title.equals(OrderDataObject.TITLE_COMPLETE)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new OrderCompleteFragment());
                }else if (title.equals(OrderDataObject.TITLE_REGISTER)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new RegisterFragment());
                }
                fragmentTransaction.commit();
            }
        }
    }


}
