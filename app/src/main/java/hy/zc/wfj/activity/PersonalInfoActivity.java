package hy.zc.wfj.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import hy.zc.wfj.R;
import hy.zc.wfj.data.UserLoginObject;
import hy.zc.wfj.utility.FileUtil;
import hy.zc.wfj.utility.SharedPrefUtility;
import hy.zc.wfj.utility.UriManager;

public class PersonalInfoActivity extends FrameActivity implements View.OnClickListener {

    public static final int CAMERA_TAG = 0;
    public static final int LOCAT_TAG = 1;
    public static final int Phone_NickName_REQUEST_CODE = 2;
    private static final int REQUESTCODE_PICK = 0;        // 相册选图标记
    private static final int REQUESTCODE_TAKE = 1;        // 相机拍照标记
    private static final int REQUESTCODE_CUTTING = 3;    // 图片裁切标记
    private static final String IMAGE_FILE_NAME = "avatarImage.png";// 头像文件名称
    private String urlpath;            // 图片本地路径
    private TextView common_title_txt;
    private ImageButton common_title_back_btn;
    private SimpleDraweeView mAvatar;
    private SimpleDraweeView mArrow;
    private LayoutInflater mInflater;
    private Context mContext;

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
        mContext=PersonalInfoActivity.this;
        mInflater = LayoutInflater.from(PersonalInfoActivity.this);

        common_title_txt = (TextView) findViewById(R.id.common_title_txt);
        common_title_txt.setText("我的账户");

        common_title_back_btn = (ImageButton) findViewById(R.id.common_title_back_btn);
        mAvatar = (SimpleDraweeView) findViewById(R.id.user_img_view);

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
                                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                //下面这句指定调用相机拍照后的照片存储的路径
                                takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                                startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                                break;
                            case LOCAT_TAG://本地上传
                                showToast(getResources().getString(R.string.tv_locat));
                                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                                // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(pickIntent, REQUESTCODE_PICK);
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
                phone_bundle.putString("modify", getResources().getString(R.string.tv_modify_phone_number));
                goModifyActivity(phone_bundle);
                break;
            case R.id.layout_nickname://跳转修改昵称界面
                Bundle nickname_bundle = new Bundle();
                nickname_bundle.putString("modify", getResources().getString(R.string.tv_modify_nick_name));
                goModifyActivity(nickname_bundle);
                break;
            case R.id.layout_address:
                showLogi("1");
                break;
            case R.id.layout_safe:
                Bundle passward_bundle=new Bundle();
                passward_bundle.putString("modify",getResources().getString(R.string.tv_modify_password));
                goModifyActivity(passward_bundle);
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
            case REQUESTCODE_PICK:// 直接从相册获取
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case REQUESTCODE_TAKE:// 调用相机拍照
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUESTCODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            urlpath = FileUtil.saveFile(mContext, "temphead.png", photo);
//            avatarImg.setImageDrawable(drawable);
            //打印一下最终截取到的图片路径
            showLogi(urlpath);
            // 新线程后台上传服务端
//            pd = ProgressDialog.show(mContext, null, "正在上传图片，请稍候...");
//            new Thread(uploadImageRunnable).start();

        }
    }
}
