package hy.zc.wfj.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

import hy.zc.wfj.R;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.utility.FileUtil;
import hy.zc.wfj.utility.SharedPrefUtility;

public class MySettingsActivity extends FrameActivity implements View.OnClickListener{

    public static final String IS_LOGIN = "isLogin";
    private ImageButton common_title_back_btn;
    private Button logout_comfirm_button;
    private Boolean isLogin=false;
    private RelativeLayout layout_about;
    private RelativeLayout layout_clean_cache;
    private TextView tv_loc_cache_pic_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

        initializeComponent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshCurrentCacheSize();

        logoutBtnVisiable();
    }

    private void refreshCurrentCacheSize() {
        long dirSize = FileUtil.getDirSize(this.getCacheDir());
        tv_loc_cache_pic_size.setText(dirSize/1024/1024+"M");
    }

    /**
     * initialize component
     */
    private void initializeComponent() {

        common_title_back_btn=(ImageButton)findViewById(R.id.common_title_back_btn);
        logout_comfirm_button=(Button)findViewById(R.id.logout_comfirm_button);
        layout_about=(RelativeLayout)findViewById(R.id.layout_upgrade_client);
        layout_clean_cache = (RelativeLayout) findViewById(R.id.layout_clean_loc_cache_pic);
        tv_loc_cache_pic_size = (TextView) findViewById(R.id.tv_loc_cache_pic_size);


        setListener();
    }

    /**
     * set component click listener
     */
    private void setListener() {
        common_title_back_btn.setOnClickListener(this);
        logout_comfirm_button.setOnClickListener(this);
        layout_about.setOnClickListener(this);
        layout_clean_cache.setOnClickListener(this);
    }

    /**
     * 检测是否登录，
     *  如果登录的话，显示退出按钮
     *  如果未登录，隐藏退出按钮
     */
    private void logoutBtnVisiable(){
        isLogin=(Boolean) SharedPrefUtility.getParam(MySettingsActivity.this, SharedPrefUtility.IS_LOGIN, false);
        if (isLogin) {
            logout_comfirm_button.setVisibility(View.VISIBLE);
        }else {
            logout_comfirm_button.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_title_back_btn:
                goBack();
                break;
            case R.id.logout_comfirm_button://退出按钮
                //弹出确认退出对话框
                final AlertDialog.Builder builder=new AlertDialog.Builder(MySettingsActivity.this);
                builder.setTitle("确认退出?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout_comfirm_button.setVisibility(View.GONE);
                        SharedPrefUtility.setParam(MySettingsActivity.this, SharedPrefUtility.IS_LOGIN, false);
                        SharedPrefUtility.removeParam(MySettingsActivity.this,SharedPrefUtility.LOGIN_DATA);
                        goBack();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.layout_upgrade_client://跳转到关于界面
                Intent intent=new Intent(MySettingsActivity.this,TemplateActivity.class);
                Bundle bundle=new Bundle();
                OrderDataObject odo=new OrderDataObject();

                odo.setTitle(OrderDataObject.TITLE_ABOUT);
                bundle.putSerializable(OrderDataObject.TITLE_KEY, odo);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.layout_clean_loc_cache_pic:
                FileUtil.deleteCache(this);
                refreshCurrentCacheSize();
                break;
            default:
                break;
        }
    }



}
