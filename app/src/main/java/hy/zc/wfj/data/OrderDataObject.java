package hy.zc.wfj.data;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by feng on 2015/11/24.
 */
public class OrderDataObject implements Serializable {

    private static final long serialVersionUID = 7580665513070075990L;
    public static String TITLE_ALL = "全部订单";
    public static String TITLE_WAIT_PAY = "待付款";
    public static String TITLE_SIGN = "待收货";
    public static String TITLE_COMMENT = "待评价";
    public static String TITLE_AFTER_SALE = "返修/退换";
    public static String TITLE_COMPLETE="完成";
    public static String TITLE_REGISTER="手机快速注册";
    public static String TITLE_FIND_PASSWORD="找回密码";
    public static String TITLE_COMMENT_PIC="评价晒单";
    public static String TITLE_PAY = "付款";
    public static String TITLE_RETURN_SALES = "退款";
    public static String TITLE_ABOUT = "关于";


    public static final int TITLE_ALL_FLAG = 0;
    public static final int TITLE_WAIT_PAY_FLAG = 1;
    public static final int TITLE_SEND_OUT_FLAG = 2;
    public static final int TITLE_SIGN_FLAG = 4;
    public static final int TITLE_COMMENT_FLAG = 5;
    public static final int TITLE_PAY_FLAG = 6;


    public static final int TITLE_AFTER_SALE_FLAG = 7;


    public static String TITLE_KEY= "title";
    public static String SINGLE_ORDER_KEY="single_order";

    private String title;

    private String uriString;

    public String getUriString() {
        return uriString;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
