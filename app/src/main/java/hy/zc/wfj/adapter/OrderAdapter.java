package hy.zc.wfj.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import hy.zc.wfj.R;
import hy.zc.wfj.activity.TemplateActivity;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.data.OrderDataObject;
import hy.zc.wfj.data.OrderListObject;
import hy.zc.wfj.pay.PayResult;
import hy.zc.wfj.pay.SignUtils;
import hy.zc.wfj.utility.UriManager;

/**
 * Created by feng on 2015/12/11.
 */
public class OrderAdapter extends BBaseAdapter {
    private Context mContext;
    private List<OrderListObject.DataEntity> mList;
    private DelCallBack mDelCallBack;
    private int mType = 0;

    public OrderAdapter(Context pcontext, List plist, DelCallBack pdelCallBack, int pType) {
        super(pcontext, plist);
        mList = plist;
        mContext = pcontext;
        mDelCallBack = pdelCallBack;
        mType = pType;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.activity_order_listview_item, parent,false);
            viewHolder.imgb_delete = (ImageButton) convertView.findViewById(R.id.imgb_delete);
            viewHolder.sdv_pic0 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic);
            viewHolder.sdv_pic1 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic1);
            viewHolder.sdv_pic2 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic2);
            viewHolder.sdv_pic3 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_pic3);
            viewHolder.sdv_finish = (SimpleDraweeView) convertView.findViewById(R.id.sdv_finsih);
            viewHolder.btn_first = (Button) convertView.findViewById(R.id.btn_first);
            viewHolder.btn_second = (Button) convertView.findViewById(R.id.btn_second);
            viewHolder.tv_commodity_name = (TextView) convertView.findViewById(R.id.tv_commodity_name);
            viewHolder.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final OrderListObject.DataEntity entity = mList.get(position);
//         设置“完成”印章图标是否显示
        int ordersState = entity.getOrdersState();
        if (ordersState == 9) {
            viewHolder.sdv_finish.setVisibility(View.VISIBLE);
        } else if (ordersState == 2){
            viewHolder.btn_first.setVisibility(View.GONE);
            viewHolder.btn_second.setVisibility(View.VISIBLE);
            viewHolder.btn_second.setText("已付款");
            viewHolder.btn_second.setTextColor(Color.GRAY);
            viewHolder.btn_second.setEnabled(false);
            viewHolder.imgb_delete.setVisibility(View.GONE);
        } else if ( ordersState == 3) {
            viewHolder.btn_first.setVisibility(View.GONE);
            viewHolder.btn_second.setVisibility(View.VISIBLE);
            viewHolder.btn_second.setText("正在打包");
            viewHolder.btn_second.setTextColor(Color.GRAY);
            viewHolder.btn_second.setEnabled(false);
            viewHolder.imgb_delete.setVisibility(View.GONE);
        } else if (ordersState == 4) {
            viewHolder.btn_first.setVisibility(View.VISIBLE);
            viewHolder.btn_first.setText("确认收货");
            viewHolder.btn_second.setVisibility(View.GONE);

            viewHolder.imgb_delete.setVisibility(View.GONE);
        } else if (ordersState == 1) {
            viewHolder.btn_first.setVisibility(View.VISIBLE);
            viewHolder.btn_first.setText("待支付");
            viewHolder.btn_second.setVisibility(View.GONE);
        } else if (ordersState == 5) {
            viewHolder.btn_first.setVisibility(View.VISIBLE);
            viewHolder.btn_first.setText("晒单评价");
            viewHolder.btn_second.setVisibility(View.VISIBLE);
            viewHolder.btn_second.setText("再次购买");
            viewHolder.btn_second.setEnabled(true);
            viewHolder.btn_second.setTextColor(Color.RED);
        } else if (ordersState == 7) {//返修退换
            viewHolder.imgb_delete.setVisibility(View.VISIBLE);
            viewHolder.sdv_finish.setVisibility(View.INVISIBLE);

            viewHolder.btn_first.setVisibility(View.GONE);
            viewHolder.btn_second.setVisibility(View.GONE);
        } else {
            viewHolder.btn_first.setVisibility(View.GONE);
            viewHolder.btn_second.setVisibility(View.GONE);

            viewHolder.imgb_delete.setVisibility(View.VISIBLE);
            viewHolder.sdv_finish.setVisibility(View.INVISIBLE);
        }

        viewHolder.btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
//                showToast(button.getText() + "");
                switch (mType) {
                    case OrderDataObject.TITLE_WAIT_PAY_FLAG://点击待付款按钮
//                        gotoOrderDetailUI(position);
                        pay(entity);
                        break;
                    case OrderDataObject.TITLE_SIGN_FLAG://点击确认收货按钮
                        String uri = UriManager.getSignState(entity.getOrdersId() + "");
                        mDelCallBack.modifySignState(uri);
                        break;
                    case OrderDataObject.TITLE_COMMENT_FLAG:
//                        Intent intent = new Intent(mContext, TemplateActivity.class);
//                        Bundle bundle = new Bundle();
//                        OrderDataObject orderDataObject = new OrderDataObject();
//                        orderDataObject.setTitle(OrderDataObject.TITLE_COMMENT_PIC);
//                        bundle.putSerializable(OrderDataObject.TITLE_KEY, orderDataObject);
//                        intent.putExtras(bundle);
//                        mContext.startActivity(intent);
                        gotoOrderDetailUI(position);
                        break;
                    case OrderDataObject.TITLE_AFTER_SALE_FLAG:
                        break;
                    case OrderDataObject.TITLE_ALL_FLAG:
                        break;
                }
            }
        });

        viewHolder.btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
//                showToast(button.getText() + "");
            }
        });

        List<OrderListObject.DataEntity.ListEntity> list = entity.getList();


//        entity.getFinalAmount();
//        entity.getOrdersNo();

//        每个订单中的图片布局，区分一个或者多个
        if (list.size() == 1) {//如果只有一个内容的布局
            OrderListObject.DataEntity.ListEntity listEntity = list.get(0);
            viewHolder.tv_shop_name.setText(listEntity.getShopName());
            Uri uri = UriManager.getCategoryPicUri(listEntity.getLogoImage());
            viewHolder.sdv_pic0.setImageURI(uri);
            viewHolder.tv_commodity_name.setText(listEntity.getProductFullName());
//            viewHolder.tv_price.setText(listEntity.getSalesPrice() + "");

            viewHolder.tv_commodity_name.setVisibility(View.VISIBLE);
            viewHolder.sdv_pic1.setVisibility(View.GONE);
            viewHolder.sdv_pic2.setVisibility(View.GONE);
            viewHolder.sdv_pic3.setVisibility(View.GONE);
        } else if (list.size() > 1) {//如果有多个内容的布局
            float price = 0;
            for (int i = 0; i < list.size(); i++) {//次循环里还要处理多张图片，目前还没做
                Uri uri = UriManager.getCategoryPicUri(list.get(i).getLogoImage());
                switch (i) {
                    case 0:
                        viewHolder.sdv_pic0.setImageURI(uri);
//                        viewHolder.sdv_pic0.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        viewHolder.sdv_pic1.setImageURI(uri);
                        viewHolder.sdv_pic1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        viewHolder.sdv_pic2.setImageURI(uri);
                        viewHolder.sdv_pic2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        viewHolder.sdv_pic3.setImageURI(uri);
                        viewHolder.sdv_pic3.setVisibility(View.VISIBLE);
                        break;
                }

//                price += list.get(i).getSalesPrice();
            }
            viewHolder.tv_shop_name.setText(list.get(0).getShopName());
            viewHolder.tv_commodity_name.setVisibility(View.GONE);
        }
        viewHolder.tv_price.setText(entity.getFinalAmount() + "");

        viewHolder.imgb_delete.setTag(position);

        viewHolder.imgb_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelCallBack.del(position);
            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageButton imgb_delete;
        SimpleDraweeView sdv_pic0, sdv_pic1, sdv_pic2, sdv_pic3, sdv_finish;
        TextView tv_commodity_name;
        TextView tv_shop_name;
        TextView tv_price;
        Button btn_first, btn_second;
    }

    /**
     * 回调函数，在fragment中执行删除操作
     */
    public interface DelCallBack {
        public void del(int position);

        public void modifyPayState(String uri);

        public void modifySignState(String uri);
    }

    /**
     * 跳转到对应的单独的订单说明界面
     *
     * @param position
     */
    public void gotoOrderDetailUI(int position) {
        Intent intent = new Intent(mContext, TemplateActivity.class);
        Bundle bundle = new Bundle();

        OrderDataObject dataObject = new OrderDataObject();
        OrderListObject.DataEntity entity = mList.get(position);
        switch (mType) {
            case OrderDataObject.TITLE_ALL_FLAG:
                dataObject.setTitle(OrderDataObject.TITLE_COMPLETE);
                break;
            case OrderDataObject.TITLE_COMMENT_FLAG:
                dataObject.setTitle(OrderDataObject.TITLE_COMMENT_PIC);
                break;
            default://上面的只是目前逻辑处理的几项，还有几项没有完成，为了防止出现bug，这里暂时以随便写了一个titile,后续还要修改的
                dataObject.setTitle(OrderDataObject.TITLE_COMPLETE);
                break;
        }
        bundle.putSerializable(OrderDataObject.TITLE_KEY, dataObject);
        bundle.putSerializable(OrderDataObject.SINGLE_ORDER_KEY, entity);

        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }


    // 商户PID
    public static final String PARTNER = "2088121041669185";
    // 商户收款账号
    public static final String SELLER = "zhoushaoju@sina.cn";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAPUQwEJbAx11O0l+HOlxNBMzFd4DkertnPbmAYA/fM23rIkhM/WVcmsvM88JwIJp8RCzeFCruahnSzPTLPvtZukxB6Gi7Go7WLjGi3gv8Y2l1IYDOmJMUUnraF69UTMQ0i6/t7OHrvCqDrS/LFnCl/k4jnd4Xz1n6NpDFXLtZGt1AgMBAAECgYABkwL5KiaB+O/RkArVgRGpkelxDKrSTIZ2m5Gk5kOB+s0T8G+qAzTB9YKqsNBYxh9zUQPT2NaPe2JjSdb/I05G6/7Fv1iP3aBIm6wRoFixNtOuePuhQUzzYNXmXT9hcFC4nt6di858bp/y3ZS0dr/yZCW64nAlQW8ju57fjKk1YQJBAP6ygmn8s3xZ6TeFiTN6tacyJgJi8SPhuzxxf2K1s8ztb6wxCg+583lsZvlTeomweXxbFBFLo0n1Fz7LRMjJ7y0CQQD2UaFHV5DuVZ+MLeKSdxxkPxOW+Vl8OaJFmRt2u+p4suxSn1cfKIfDjowVKICxqWO01IJP849tXq587aY3mdppAkEA6KtwIZxc5sNIHpVZkfKOUW+pc1KpkSrcHcpdIXJ2D0X0VyvxUg6AEjNqGmgdL/1L7cNSIL6lK6G/G3kbnAWE/QJBAPJzjwgmDSZLaWwVjjsUuwGBhMrKTWaVZECVYA8TEMwVccAxzxOAVYILwZ7h6yr0zIul0U7ZMog49g9J7J419mECQQD8oz3shqeKZWvsFeOv+47Puqah+Q6vn9OJew6fIO1Mo+f7Jwuzf9xZM48TocjePIHJ1iDEsrrIf0L4ocw4OI1N";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "";
    private static final int SDK_PAY_FLAG = 1;

    private static final int SDK_CHECK_FLAG = 2;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        showToast("支付成功");
                        //如果支付成功的话，拿到订单编号，然后通过回调，向服务器查询一下结果，目的是为了更新付款状态
                        Bundle data = msg.getData();
                        String ordersNo = data.getString("ordersNo");
                        String uri = UriManager.getPayState(ordersNo);
                        mDelCallBack.modifyPayState(uri);
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            showToast("支付结果确认中");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            showToast("支付失败");
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    showToast("检查结果为：" + msg.obj);
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay(final OrderListObject.DataEntity pentity) {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(mContext)
                    .setTitle("警告")
                    .setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    //
                                    ((Activity) mContext).finish();
                                }
                            }).show();
            return;
        }
        // 订单

        final String ordersNo = pentity.getOrdersNo();//订单编号
        float finalAmount = pentity.getFinalAmount();//订单价格
        List<OrderListObject.DataEntity.ListEntity> list = pentity.getList();
        OrderListObject.DataEntity.ListEntity listEntity = list.get(0);
        String productFullName = listEntity.getProductFullName();//订单名称
        showLogi(ordersNo + productFullName + finalAmount + "");
        String orderInfo = getOrderInfo(ordersNo, productFullName, finalAmount + "");

        // 对订单做RSA 签名
        String sign = sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                //start 将订单编号传走,在支付成功里面通知服务器
                Bundle data = new Bundle();
                data.putString("ordersNo", ordersNo);
                msg.setData(data);
                //end
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * check whether the device has authentication alipay account.
     * 查询终端设备是否存在支付宝认证账户
     */
    public void check(View v) {
        Runnable checkRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask payTask = new PayTask((Activity) mContext);
                // 调用查询接口，获取查询结果
                boolean isExist = payTask.checkAccountIfExist();

                Message msg = new Message();
                msg.what = SDK_CHECK_FLAG;
                msg.obj = isExist;
                mHandler.sendMessage(msg);
            }
        };

        Thread checkThread = new Thread(checkRunnable);
        checkThread.start();

    }

    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask((Activity) mContext);
        String version = payTask.getVersion();
        showToast(version);
    }

    /**
     * create the order info. 创建订单信息
     */
    public String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
//        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
        orderInfo += "&out_trade_no=" + "\"" + subject + "\"";

        // 商品名称
//        orderInfo += "&subject=" + "\"" + subject + "\"";
        orderInfo += "&subject=" + "\"" + body + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     * 这个是测试用例时用的，真正到开发中是从服务器端获取的订单编号，注意
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
