package hy.zc.wfj.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import hy.zc.wfj.R;
import hy.zc.wfj.utility.SharedPrefUtility;

public class MySettingsActivity extends Activity implements View.OnClickListener{

    public static final String IS_LOGIN = "isLogin";
    public static final String TAG = "fengluchun";
    private ImageButton common_title_back_btn;
    private Button logout_comfirm_button;
    private Boolean isLogin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

        initializeComponent();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        logoutBtnVisiable();
    }

    /**
     * initialize component
     */
    private void initializeComponent() {

        common_title_back_btn=(ImageButton)findViewById(R.id.common_title_back_btn);
        logout_comfirm_button=(Button)findViewById(R.id.logout_comfirm_button);

    }
    /**
     * set component click listener
     */
    private void setListener() {
        common_title_back_btn.setOnClickListener(this);
        logout_comfirm_button.setOnClickListener(this);
    }

    /**
     * 检测是否登录，
     *  如果登录的话，显示退出按钮
     *  如果未登录，隐藏退出按钮
     */
    private void logoutBtnVisiable(){
        isLogin=(Boolean) SharedPrefUtility.getParam(getApplicationContext(), IS_LOGIN, false);
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
            case R.id.logout_comfirm_button:
                //弹出确认退出对话框
                final AlertDialog.Builder builder=new AlertDialog.Builder(MySettingsActivity.this);
                builder.setTitle("确认退出?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout_comfirm_button.setVisibility(View.GONE);
                        SharedPrefUtility.setParam(getApplicationContext(),IS_LOGIN,false);
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
            default:
                break;
        }
    }

    /**
     * 返回之前跳转过来的activity
     */
    private void goBack() {
        Intent gobackIntent=getIntent();
        setResult(Activity.RESULT_OK,gobackIntent);
        finish();
    }


}
