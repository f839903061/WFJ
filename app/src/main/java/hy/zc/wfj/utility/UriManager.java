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
    //加载图片的前缀
    private static final String pic_pre = "http://192.168.10.210:8085/b2b2c/";
    //登录请求的前缀
    private static final String login_pre = "http://101.200.182.119:8080/phone/login.action?loginName=";


    /**
     * 拼接图片uri，并返回
     *
     * @param pappendtext
     * @return
     */
    public static Uri getPicToUri(String pappendtext) {
        StringBuilder stringBuilder = new StringBuilder(pic_pre);
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
}
