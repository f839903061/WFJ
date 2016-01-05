package hy.zc.wfj.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.ByteArrayOutputStream;
import java.io.File;

import hy.zc.wfj.App;
import hy.zc.wfj.R;
import hy.zc.wfj.data.MultipartEntity;
import hy.zc.wfj.data.MultipartRequest;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2016/1/2.
 */
public class ReturnSalesFragment extends FrameFragment implements View.OnClickListener {

    public static final int RB_COMMODITY_FLAG = 5;
    public static final int RB_MONEY_FLAG = 6;
    public static final int RT_M = 1;
    public static final int RT_C = 2;

    public static final int CAMERA_TAG = 0;
    public static final int LOCAT_TAG = 1;
    private static final int REQUESTCODE_PICK = 0;        // 相册选图标记
    private static final int REQUESTCODE_TAKE = 1;        // 相机拍照标记
    private static final int REQUESTCODE_CUTTING = 3;    // 图片裁切标记
    private static final String IMAGE_FILE_NAME = "avatarImage.png";// 头像文件名称
    public static final String IS_UPLOAD = "isUpload";
    public static final String IS_COMMIT_OK = "CommitOk";
    private String urlpath = "";            // 图片本地路径
//    private String urlpath="/storage/emulated/0/SmartWFJ/20160102/temphead.png";            // 图片本地路径

    private int pordersNo;
    private int productId;
    private int pcount;
    private int returnType = -1;//RT_C  or RT_M

    private int returnReasonType = -1;
    private String returnReasonDescription;
    private String txImage;
    private String txImageFileName;
    private OrderListObject.DataEntity.ListEntity mEntity;

    private RadioGroup rg_choice;
    private RelativeLayout layout_choice;
    private RelativeLayout layout_upload;
    private TextView tv_choice;
    private TextView tv_price;
    private EditText et_feedback;
    private TextView tv_feedback_count;
    private Button btn_commit;
    private ImageView sdv_pic;

    public ReturnSalesFragment() {
    }

    public ReturnSalesFragment(OrderListObject.DataEntity.ListEntity pEntity) {
        mEntity = pEntity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_return_sales, container, false);
        initializeComponent(rootView);
        return rootView;
    }

    private void initializeComponent(View rootView) {
        rg_choice = (RadioGroup) rootView.findViewById(R.id.rg_choice);
        layout_choice = (RelativeLayout) rootView.findViewById(R.id.layout_choice);
        tv_choice = (TextView) rootView.findViewById(R.id.tv_choice);
        tv_price = (TextView) rootView.findViewById(R.id.tv_price);
        et_feedback = (EditText) rootView.findViewById(R.id.et_feedback);
        tv_feedback_count = (TextView) rootView.findViewById(R.id.tv_feedback_count);
        layout_upload = (RelativeLayout) rootView.findViewById(R.id.layout_upload);
        btn_commit = (Button) rootView.findViewById(R.id.btn_commit);
        sdv_pic = (ImageView) rootView.findViewById(R.id.sdv_pic);

        rg_choice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                /*由于radiogroup是在fragment中，所以和activity中有些不一样，
                checkedid是索引值，而不是resourceID，所以这地方需要先打印一下，
                看看具体是什么数组，比如5,6 为什么不是从0或者1开始呢，
                我目前的猜测是该监听器读取的是整个应用程序的radiobutton，
                因为在主界面下方有四个radiobutton了，那么这里就是顺延index*/
                switch (checkedId) {
                    case RB_MONEY_FLAG:
                        returnType = RT_M;
                        break;
                    case RB_COMMODITY_FLAG:
                        returnType = RT_C;
                        break;
                    default:
                        break;
                }
            }
        });

        layout_choice.setOnClickListener(this);
        layout_upload.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        tv_price.setText(mEntity.getSalesPrice() + "");
        et_feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_feedback_count.setText(s.toString().length() + "/200");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_choice:
                final String[] stringArray = getResources().getStringArray(R.array.returnreason);
                AlertDialog.Builder builder_choice = new AlertDialog.Builder(getActivity());
                builder_choice.setTitle("请选择退货原因");
                builder_choice.setItems(R.array.returnreason, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        returnReasonType = which;
                        tv_choice.setText(stringArray[which]);
                        dialog.dismiss();
                    }
                });
                builder_choice.create().show();
                break;
            case R.id.layout_upload:
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
                builder.setTitle(R.string.dialog_pic_title);
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
            case R.id.btn_commit:
                if (returnType != -1 && returnReasonType != -1) {
                    String uri = UriManager.getReturnSales();
                    MultipartRequest multipartRequest = new MultipartRequest(uri, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    MultipartEntity multiPartEntity = multipartRequest.getMultiPartEntity();

                    multiPartEntity.addStringPart("ordersNo", "" + mEntity.getOrdersNo());
                    multiPartEntity.addStringPart("productId", "" + mEntity.getProductId());
                    multiPartEntity.addStringPart("count", "" + mEntity.getCount());
                    multiPartEntity.addStringPart("returnType", "" + returnType);
                    multiPartEntity.addStringPart("returnReasonType", "" + returnReasonType);
                    returnReasonDescription = et_feedback.getText().toString();
                    multiPartEntity.addStringPart("returnReasonDescription", "" + returnReasonDescription);
                    multiPartEntity.addStringPart("txImageFileName", urlpath);

                    if (!urlpath.equals("")) {
                        Bitmap bitmap = BitmapFactory.decodeFile(urlpath);

                        String pfilename = mEntity.getOrdersId() + ".png";
                        multiPartEntity.addBinaryPart("txImage", pfilename, bitmapToBytes(bitmap));
                    }

                    App.addRequest(multipartRequest, IS_COMMIT_OK);
                } else {
                    showToast("请把带*都填写");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 将图片转换为byte字节流，用来给上传图片用的
     *
     * @param bm
     * @return
     */
    public byte[] bitmapToBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    public void onStop() {
        App.cancelAllRequests(IS_COMMIT_OK);
        super.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
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
//            urlpath = FileUtil.saveFile(getActivity(), "temphead.png", photo);
//            avatarImg.setImageDrawable(drawable);
            //打印一下最终截取到的图片路径

//            sdv_pic.setImageURI();
//            sdv_pic.setImageBitmap(BitmapFactory.decodeFile(urlpath));

            urlpath="/data/data/com.test.myapplication/files/f1.png";
            Message msg = Message.obtain();
            msg.arg1 = 1;
            Bundle data = new Bundle();
            data.putString("path", urlpath);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                showLogi(urlpath);
                layout_upload.setVisibility(View.GONE);
//                sdv_pic.setVisibility(View.VISIBLE);
//                Bundle data = msg.getData();
//                String path = data.getString("path");
//                sdv_pic.setImageBitmap(BitmapFactory.decodeFile(path));
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("returnType", returnType);
        outState.putInt("returnReasonType", returnReasonType);
    }
}
