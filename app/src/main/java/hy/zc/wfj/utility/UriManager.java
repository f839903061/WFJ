package hy.zc.wfj.utility;

import android.net.Uri;

/**
 * 这个类的功能是同一管理项目的网址
 * Created by feng on 2015/11/27.
 */
public class UriManager {
    //        String uri="http://192.168.10.210:8080/wfj_front/phone/phonecategory?method=initType";
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
    private static final String login_pre = "http://192.168.10.7:8080/phone/login.action?loginName=";//局域网
    //修改号码的前缀
    private static final String modify_phone_pre = "http://192.168.10.7:8080/phone/editCustomer.action?phone=";
    //修改昵称
    private static final String modify_nickname_pre = "http://192.168.10.7:8080/phone/editCustomer.action?nickname=";


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
     * @param pphone
     * @return
     */
    public static String getModifyPhoneUri(String pphone){
        StringBuilder stringBuilder=new StringBuilder(modify_phone_pre);
        stringBuilder.append(pphone);
        return stringBuilder.toString();
    }

    /**
     * 通过拼接，获得修改昵称的完整请求连接
     * @param pname
     * @return
     */
    public static String getMOdifyNickName(String pname){
        StringBuilder stringBuilder=new StringBuilder(modify_nickname_pre);
        stringBuilder.append(pname);
        return stringBuilder.toString();
    }
}
