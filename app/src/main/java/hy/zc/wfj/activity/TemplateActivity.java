package hy.zc.wfj.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import hy.zc.wfj.R;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.fragment.AboutFragment;
import hy.zc.wfj.fragment.AfterSaleFragment;
import hy.zc.wfj.fragment.CommentFragment;
import hy.zc.wfj.fragment.CommentPicFragment;
import hy.zc.wfj.fragment.ForgetFragment;
import hy.zc.wfj.fragment.OrderCompleteFragment;
import hy.zc.wfj.fragment.OrderFragment;
import hy.zc.wfj.fragment.ProtocolFragment;
import hy.zc.wfj.fragment.RegisterFragment;
import hy.zc.wfj.fragment.SignFragment;
import hy.zc.wfj.fragment.WaitPayFragment;

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
                if (title.equals(OrderDataObject.TITLE_ALL) ) {//全部订单
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new OrderFragment());
                }else if (title.equals(OrderDataObject.TITLE_WAIT_PAY)){//待支付
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new WaitPayFragment());

                }else if (title.equals(OrderDataObject.TITLE_SIGN)){//待签收
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new SignFragment());

                }else if (title.equals(OrderDataObject.TITLE_COMMENT)){//待评价
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new CommentFragment());

                }else if (title.equals(OrderDataObject.TITLE_AFTER_SALE)){//返修
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new AfterSaleFragment());
                }else if (title.equals(OrderDataObject.TITLE_COMPLETE)){//完成订单，查看订单具体内容
                    OrderListObject.DataEntity entity= (OrderListObject.DataEntity) bundle.getSerializable(OrderDataObject.SINGLE_ORDER_KEY);
                    if (entity != null) {
                        fragmentTransaction=getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.layout_container, new OrderCompleteFragment(entity));
                    }
                }else if (title.equals(OrderDataObject.TITLE_REGISTER)){//注册界面
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new RegisterFragment());
                }else if (title.equals(OrderDataObject.TITLE_COMMENT_PIC)){
                    OrderListObject.DataEntity entity= (OrderListObject.DataEntity) bundle.getSerializable(OrderDataObject.SINGLE_ORDER_KEY);
                    if (entity != null) {
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.layout_container, new CommentPicFragment(entity));
                    }
                }else if (title.equals(OrderDataObject.TITLE_FIND_PASSWORD)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new ForgetFragment());
                }else if (title.equals(OrderDataObject.TITLE_ABOUT)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new AboutFragment());
                }else if (title.equals(OrderDataObject.TITLE_PROTOCOL)){
                    fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new ProtocolFragment());
                }
                fragmentTransaction.commit();
            }
        }
    }


}
