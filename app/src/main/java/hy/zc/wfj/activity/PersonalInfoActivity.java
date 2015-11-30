package hy.zc.wfj.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import hy.zc.wfj.R;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class PersonalInfoActivity extends FrameActivity implements View.OnClickListener {

    public static final int CAMERA_TAG = 0;
    public static final int LOCAT_TAG = 1;
    public static final int Phone_NickName_REQUEST_CODE = 2;
    private TextView common_title_txt;
    private ImageButton common_title_back_btn;
    private SimpleDraweeView mAvatar;
    private SimpleDraweeView mArrow;
    private LayoutInflater mInflater;

    //标签项，可点击
    private RelativeLayout layout_avatar;
    private RelativeLayout layout_username;
    private RelativeLayout layout_email;
    private RelativeLayout layout_phone;
    private RelativeLayout layout_nickname;
    private RelativeLayout layout_address;
    private RelativeLayout layout_safe;
    //标签，无操作
    private TextView tv_avatar;
    private TextView tv_username;
    private TextView tv_email;
    private TextView tv_phone;
    private TextView tv_nickname;
    private TextView tv_address;
    private TextView tv_safe;
    //对于标签的内容
    private TextView tv_content_username;
    private TextView tv_content_email;
    private TextView tv_content_phone;
    private TextView tv_content_nickname;
    private TextView tv_content_safe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(PersonalInfoActivity.this);
        setContentView(R.layout.activity_personal_info);
        initializeComponent();
        loadData();
        setListener();
    }

    /**
     * 对应标签填充获取到的数据，如
     * 头像，名称，昵称，邮箱，手机
     */
    private void loadData() {
        String temp = (String) SharedPrefUtility.getParam(PersonalInfoActivity.this, SharedPrefUtility.LOGIN_DATA, "");
        if (!temp.equals("")) {
            UserLoginObject userLoginObject = JSON.parseObject(temp, UserLoginObject.class);
            UserLoginObject.DataEntity data = userLoginObject.getData();
            Uri uri = UriManager.getLoginAvatarUri(data.getPhotoUrl());
            mAvatar.setImageURI(uri);
            tv_content_username.setText(data.getLoginName());
            tv_content_email.setText(data.getEmail());
            tv_content_phone.setText(data.getPhone());
            tv_content_nickname.setText(data.getNickName());

        } else {
            showLoge("没有或取到数据");
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        //重新加载数据,防止有数据没有随时更新显示
        loadData();
    }

    /**
     * 给组件设置监听
     */
    private void setListener() {
        common_title_back_btn.setOnClickListener(this);
        layout_avatar.setOnClickListener(this);
        layout_username.setOnClickListener(this);
        layout_email.setOnClickListener(this);
        layout_phone.setOnClickListener(this);
        layout_nickname.setOnClickListener(this);
        layout_address.setOnClickListener(this);
        layout_safe.setOnClickListener(this);
    }

    /**
     * 初始化组件
     */
    private void initializeComponent() {
        mInflater = LayoutInflater.from(PersonalInfoActivity.this);

        common_title_txt = (TextView) findViewById(R.id.common_title_txt);
        common_title_txt.setText("我的账户");

        common_title_back_btn = (ImageButton) findViewById(R.id.common_title_back_btn);
        mAvatar = (SimpleDraweeView) findViewById(R.id.personel_item_arrow);

        layout_avatar = (RelativeLayout) findViewById(R.id.personel_item_userpic);
        layout_username = (RelativeLayout) findViewById(R.id.layout_username);
        layout_email = (RelativeLayout) findViewById(R.id.layout_email);
        layout_phone = (RelativeLayout) findViewById(R.id.layout_phone);
        layout_nickname = (RelativeLayout) findViewById(R.id.layout_nickname);
        layout_address = (RelativeLayout) findViewById(R.id.layout_address);
        layout_safe = (RelativeLayout) findViewById(R.id.layout_safe);

        tv_avatar = (TextView) layout_avatar.findViewById(R.id.userpic_text);
        tv_username = (TextView) layout_username.findViewById(R.id.personel_item_text);
        mArrow = (SimpleDraweeView) layout_username.findViewById(R.id.personel_item_arrow);
        tv_email = (TextView) layout_email.findViewById(R.id.personel_item_text);
        tv_phone = (TextView) layout_phone.findViewById(R.id.personel_item_text);
        tv_nickname = (TextView) layout_nickname.findViewById(R.id.personel_item_text);
        tv_address = (TextView) layout_address.findViewById(R.id.personel_item_text);
        tv_safe = (TextView) layout_safe.findViewById(R.id.personel_item_text);

        tv_content_username = (TextView) layout_username.findViewById(R.id.personel_item_content);
        tv_content_email = (TextView) layout_email.findViewById(R.id.personel_item_content);
        tv_content_phone = (TextView) layout_phone.findViewById(R.id.personel_item_content);
        tv_content_nickname = (TextView) layout_nickname.findViewById(R.id.personel_item_content);
        tv_content_safe = (TextView) layout_safe.findViewById(R.id.personel_item_content);

        //下面的这些就是定死的内容，不用再关注
        mArrow.setVisibility(View.INVISIBLE);
        tv_avatar.setText(getResources().getString(R.string.tv_avatar));
        tv_username.setText(getResources().getString(R.string.tv_username));
        tv_email.setText(getResources().getString(R.string.tv_email));
        tv_phone.setText(getResources().getString(R.string.tv_phone));
        tv_nickname.setText(getResources().getString(R.string.tv_nickname));
        tv_address.setText(getResources().getString(R.string.tv_address));
        tv_safe.setText(getResources().getString(R.string.tv_safe));

        tv_content_safe.setText(getResources().getString(R.string.tv_content_safe));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back_btn:
                goBack();
                break;
            case R.id.personel_item_userpic:
                final AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInfoActivity.this, AlertDialog.THEME_HOLO_DARK);
                builder.setTitle(R.string.dialog_avatar_title);
                builder.setItems(R.array.alteritem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case CAMERA_TAG://拍照上传
                                showToast(getResources().getString(R.string.tv_camera));
                                break;
                            case LOCAT_TAG://本地上传
                                showToast(getResources().getString(R.string.tv_locat));
                                break;
                            default:
                                break;
                        }
                    }
                });

//                builder.setView(view);
                builder.create().show();
                break;
            case R.id.layout_username:
                break;
            case R.id.layout_email:
                break;
            case R.id.layout_phone://跳转修改号码界面
                Bundle phone_bundle = new Bundle();
                phone_bundle.putString("modify", "修改号码");
                goModifyActivity(phone_bundle);
                break;
            case R.id.layout_nickname://跳转修改昵称界面
                Bundle nickname_bundle = new Bundle();
                nickname_bundle.putString("modify", "修改昵称");
                goModifyActivity(nickname_bundle);
                break;
            case R.id.layout_address:
                showLogi("1");
                break;
            case R.id.layout_safe:
                showLogi("1");
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到修改界面
     *
     * @param pbundle
     */
    private void goModifyActivity(Bundle pbundle) {
        Intent goModify = new Intent(PersonalInfoActivity.this, ModifyActivity.class);
        goModify.putExtras(pbundle);
        startActivityForResult(goModify, Phone_NickName_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Phone_NickName_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    loadData();
                }
                break;
            default:
                break;
        }
    }
}
