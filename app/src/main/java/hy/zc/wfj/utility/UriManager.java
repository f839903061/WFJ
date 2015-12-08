package hy.zc.wfj.utility;

import android.net.Uri;

/**
 * 这个类的功能是同一管理项目的网址
 * Created by feng on 2015/11/27.
 */
public class UriManager {
    //        String uri="http://192.168.10.210:8080/wfj_front/phone/phonecategory?method=initType";
    public static final String H_CONNECT_PRE = "http://192.168.10.7:8080/wfj_front/phone/";
    public static final String X_CONNECT_PRE = "http://192.168.10.210:8080/wfj_front/phone/";


    //        String uri = "https://192.168.10.210:8443/wfj_front/phone/phonecategory.action?method=initType";
    //分类界面的请求链接
    private static final String category_request_uri = "http://101.200.182.119:8080/phone/phonecategory.action?method=initType";
    //首页的请求链接
    private static final String home_uri = "http://101.200.182.119:8080/test.jsp";
    //加载分类图片的前缀
    private static final String category_pic_pre = "http://192.168.10.210:8085/b2b2c/";
    //加载登录头像的前缀
    private static final String login_pic_pre = "http://192.168.10.7:8085/b2b2c/";
    //登录请求的前缀
//    private static final String login_pre = "http://101.200.182.119:8080/phone/login.action?loginName=";//全网
    private static final String login_pre = H_CONNECT_PRE + "login.action?loginName=";//局域网
    //修改号码的前缀
    private static final String modify_phone_pre = H_CONNECT_PRE + "editCustomer.action?customerId=";
    //修改昵称
    private static final String modify_nickname_pre = H_CONNECT_PRE + "editCustomer.action?customerId=";
    //修改密码
    private static final String modify_password_pre = H_CONNECT_PRE + "changepass.action?customerId=";
    //上传头像前缀
    private static final String upload_pic_pre = H_CONNECT_PRE + "uploadPic.action";
    //实时搜索前缀
    private static final String search_pre = X_CONNECT_PRE + "mohuProinfo.action?keyword=";
    //商品详情前缀
    private static final String detial_pre = X_CONNECT_PRE + "conditionProinfo.action?productTypeId=";

    public enum ORDER {
        normal,
        pricedown,
        priceup,
        totalSalesdown,
        totalSalesup
    }

    /**
     * 拼接图片链接，并返回完整的uri
     * 针对的是分类里的图片
     *
     * @param pappendtext
     * @return
     */
    public static Uri getCategoryPicUri(String pappendtext) {
        StringBuilder stringBuilder = new StringBuilder(category_pic_pre);
        stringBuilder.append(pappendtext);
        Uri uri = Uri.parse(stringBuilder.toString());
        return uri;
    }

    /**
     * 拼接图片链接，并返回完整的uri
     * 针对的是登录的头像
     *
     * @param pappendtext
     * @return
     */
    public static Uri getLoginAvatarUri(String pappendtext) {
        StringBuilder stringBuilder = new StringBuilder(login_pic_pre);
        stringBuilder.append(pappendtext);
        Uri uri = Uri.parse(stringBuilder.toString());
        return uri;
    }

    /**
     * 获取首页访问的链接
     *
     * @return
     */
    public static String getHomeUri() {

        return home_uri;
    }

    /**
     * 返回分类请求链接
     *
     * @return
     */
    public static String getCategoryRequestUri() {
        return category_request_uri;
    }

    /**
     * 拼接用户名和密码与前缀，返回链接
     *
     * @param pname
     * @param ppassword
     * @return
     */
    public static String getLoginRequestUri(String pname, String ppassword) {
        StringBuilder stringBuilder = new StringBuilder(login_pre);
        stringBuilder.append(pname);
        stringBuilder.append("&cpassword=");
        stringBuilder.append(ppassword);
        return stringBuilder.toString();
    }

    /**
     * 通过拼接，获得修改号码的完整请求连接
     *
     * @param pphone
     * @return
     */
    public static String getModifyPhoneUri(int pcustomId, String pphone) {
        StringBuilder stringBuilder = new StringBuilder(modify_phone_pre);
        stringBuilder.append(pcustomId);
        stringBuilder.append("&phone=");
        stringBuilder.append(pphone);
        return stringBuilder.toString();
    }

    /**
     * 通过拼接，获得修改昵称的完整请求连接
     *
     * @param pname
     * @return
     */
    public static String getModifyNickName(int pcustomId, String pname) {
        StringBuilder stringBuilder = new StringBuilder(modify_nickname_pre);
        stringBuilder.append(pcustomId);
        stringBuilder.append("&nickname=");
        stringBuilder.append(pname);
        return stringBuilder.toString();
    }

    /**
     * 通过拼接，获得修改密码的完整请求连接
     *
     * @param pcustomId
     * @param oldpassword
     * @param newpassword
     * @return
     */
    public static String getModifyPassword(int pcustomId, String oldpassword, String newpassword) {
        StringBuilder stringBuilder = new StringBuilder(modify_password_pre);
        stringBuilder.append(pcustomId);
        stringBuilder.append("&password=");
        stringBuilder.append(oldpassword);
        stringBuilder.append("&cpassword=");
        stringBuilder.append(newpassword);
        return stringBuilder.toString();
    }

    /**
     * 获取上传头像的连接
     *
     * @return
     */
    public static String getUpload() {
        StringBuilder stringBuilder = new StringBuilder(upload_pic_pre);
        return stringBuilder.toString();
    }


    /**
     * 获取搜索自动补全链接
     *
     * @param ptext 搜索内容
     * @return
     */
    public static String getSearch(String ptext) {
        StringBuilder stringBuilder = new StringBuilder(search_pre);
        stringBuilder.append(ptext);
        return stringBuilder.toString();
    }

    /**
     * 获取完整的详情列表连接
     * @param pid
     * @param porder
     * @return
     */
    public static String getDetialFull(int pid, ORDER porder) {
        StringBuilder stringBuilder = new StringBuilder(detial_pre);
        stringBuilder.append("" + pid);
        stringBuilder.append("&orderBy=");
        stringBuilder.append(porder);
        return stringBuilder.toString();
    }

    /**
     * 获取部分详情列表连接，最末端的筛选，留出来空着，以便灵活运用
     * @param pid
     * @return
     */
    public static String getDetialPart(int pid) {
        StringBuilder stringBuilder = new StringBuilder(detial_pre);
        stringBuilder.append("" + pid);
        stringBuilder.append("&orderBy=");
        return stringBuilder.toString();
    }
}
