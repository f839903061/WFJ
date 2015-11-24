package hy.zc.wfj.data;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by feng on 2015/11/24.
 */
public class OrderDataObject implements Serializable {

    public static String TITLE_ALL = "全部订单";
    public static String TITLE_PAY = "待付款";
    public static String TITLE_SIGN = "待收货";
    public static String TITLE_COMMENT = "待评价";
    public static String TITLE_AFTER_SALE = "返修/退换";

    public static String TITLE_KEY= "title";

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
