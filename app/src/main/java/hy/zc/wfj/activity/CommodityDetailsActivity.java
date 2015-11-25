package hy.zc.wfj.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

import hy.zc.wfj.R;

public class CommodityDetailsActivity extends FrameActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(CommodityDetailsActivity.this);
        setContentView(R.layout.activity_commodity_details);

        initializeComponent();

    }

    private void initializeComponent() {
        mBack = (LinearLayout) findViewById(R.id.home_search_button);
        mRadioGroup = (RadioGroup) findViewById(R.id.commodity_radiogroup);
        mBack.setOnClickListener(this);

        mRadioGroup.check(R.id.commodity_composite);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.commodity_composite://综合刷新listview
//                        showToast("1");
                        break;
                    case R.id.commodity_sales_volume://销量刷新listview
//                        showToast("2");
                        break;
                    case R.id.commodity_price://价格刷新listview
//                        showToast("3");
                        break;
                    default:

                        break;
                }
                showToast("数据暂时没有加载。。。");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_button:
                goBack();
                break;
            default:

                break;
        }
    }
}
