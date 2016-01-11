package hy.zc.wfj.utility;

import android.net.Uri;

/**
 * 这个类的功能是同一管理项目的网址
 * Created by feng on 2015/11/27.
 */
public class UriManager {
    //        String uri="http://192.168.10.210:8080/wfj_front/phone/phonecategory?method=initType";
//    public static final String X_CONNECT_PRE = "http://192.168.10.7:8080/wfj_front/phone/";
//    public static final String H_CONNECT_PRE = "http://192.168.10.210:8080/wfj_front/phone/";

    public static final String X_CONNECT_PRE = "https://192.168.10.7:8443/wfj_front/phone/";
    public static final String H_CONNECT_PRE = "https://192.168.10.210:8443/wfj_front/phone/";

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
    //刷新用户信息
    private static final String flush_pre = H_CONNECT_PRE + "flushUser.action?customerId=";
    //修改号码的前缀
    private static final String modify_phone_pre = X_CONNECT_PRE + "editCustomer.action?customerId=";
    //修改昵称
    private static final String modify_nickname_pre = X_CONNECT_PRE + "editCustomer.action?customerId=";
    //修改密码
    private static final String modify_password_pre = X_CONNECT_PRE + "changepass.action?customerId=";
    //上传头像前缀
    private static final String upload_pic_pre = H_CONNECT_PRE + "uploadPic.action";
    //反馈信息前缀
    private static final String feedback_pre = X_CONNECT_PRE + "feedback.action?customerId=";
    //实时搜索前缀
    private static final String search_pre = X_CONNECT_PRE + "mohuProinfo.action?keyword=";
    //分类界面商品列表前缀
    private static final String category_pre = X_CONNECT_PRE + "conditionProinfo.action?productTypeId=";
    //商品推荐前缀
    private static final String recommend_pre = X_CONNECT_PRE + "recommandProinfo.action";
    //关注个类
    private static final String concern_commodity_pre = X_CONNECT_PRE + "getCustomerCollectPro.action?customerId=";
    //关注商铺
    private static final String concern_shop_pre = X_CONNECT_PRE + "getCustomerCollectShop.action?customerId=";
    //商品详情界面
    private static final String commodity_detail_pre = X_CONNECT_PRE + "phoneProductInfoById.action?productId=";
    //订单列表前缀
    private static final String order_detail_pre = H_CONNECT_PRE + "searchAllOrders.action?customerId=";
    //注册列表前缀
    private static final String register_pre = H_CONNECT_PRE + "register.action?email=";
    //找回密码
    private static final String forget_pre = H_CONNECT_PRE + "phoneForGotValidate.action?email=";
    //支付成功与否状态查询
    private static final String pay_state_pre = H_CONNECT_PRE + "payOrder.action?ordersNo=";
    //确认收货请求
    private static final String sign_state_pre = H_CONNECT_PRE + "confirmGoods.action?ordersId=";
    //评价发送
    private static final String comment_pre = X_CONNECT_PRE + "evaluateGood.action";
    //删除订单
    private static final String del_order_pre = H_CONNECT_PRE + "deleteOrders.action?ordersId=";
    //退货请求get
    private static final String return_sales_pre = H_CONNECT_PRE + "returnSales.action?ordersNo=";
    //退货请求，post请求
    private static final String return_sales_pre2 = H_CONNECT_PRE + "returnSales.action";
    //购物车页面
    private static final String cart_pre = H_CONNECT_PRE + "returnSales.action?customerId=";


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
     * 获取搜索自动补全链接
     *
     * @param ptext 搜索内容
     * @return
     */
    public static String getSearch(String ptext, ORDER porder) {
        StringBuilder stringBuilder = new StringBuilder(search_pre);
        stringBuilder.append(ptext);
        return stringBuilder.toString();
    }


    /**
     * 获取完整的分类列表连接
     *
     * @param pid
     * @param porder
     * @return
     */
    public static String getCategorySortUri(int pid, ORDER porder) {
        StringBuilder stringBuilder = new StringBuilder(category_pre);
        stringBuilder.append("" + pid);
        stringBuilder.append("&orderBy=");
        stringBuilder.append(porder);
        return stringBuilder.toString();
    }

    /**
     * 获取商品推荐的连接，在search界面有需要
     *
     * @return
     */
    public static String getRecommendUri() {
        StringBuilder stringBuilder = new StringBuilder(recommend_pre);
        return stringBuilder.toString();
    }

    /**
     * 获取关注商品链接
     *
     * @return
     */
    public static String getConcernCommodityUri(int pcustomerId) {
        StringBuilder stringBuilder = new StringBuilder(concern_commodity_pre);
        stringBuilder.append("" + pcustomerId);
        return stringBuilder.toString();
    }

    /**
     * 获取关注商铺链接
     *
     * @return
     */
    public static String getConcernShopUri(int pcustomerId) {
        StringBuilder stringBuilder = new StringBuilder(concern_shop_pre);
        stringBuilder.append("" + pcustomerId);
        return stringBuilder.toString();
    }

    /**
     * 商品详情界面链接
     *
     * @param pproductId
     * @param pcustomerId
     * @return
     */
    public static String getCommodityDetialUri(int pproductId, int pcustomerId) {
        StringBuilder stringBuilder = new StringBuilder(commodity_detail_pre);
        stringBuilder.append("" + pproductId);
        stringBuilder.append("&customerId=");
        stringBuilder.append("" + pcustomerId);
        return stringBuilder.toString();
    }

    /**
     * 获取反馈信息列表
     *
     * @param pcustomerID  用户Id
     * @param pemail       用户邮箱
     * @param pphonenumber 用户手机号码
     * @param ptype        反馈类型(1~6)
     * @param ptext        反馈内容
     * @return
     */
    public static String getFeedback(int pcustomerID, String pemail, String pphonenumber, int ptype, String ptext) {
        StringBuilder stringBuilder = new StringBuilder(feedback_pre);
        stringBuilder.append(pcustomerID);
        if (pemail != null) {
            stringBuilder.append("&customerEmail=");
            stringBuilder.append(pemail);
        }
        if (pphonenumber != null) {
            stringBuilder.append("&customerPhone=");
        }
        stringBuilder.append("&fbtype=");
        stringBuilder.append("" + ptype);
        stringBuilder.append("&fbcontent=");
        stringBuilder.append(ptext);
        return stringBuilder.toString();
    }

    /**
     * 获取订单列表
     *
     * @param pcustomerId
     * @param orderType   OrderDataObject(
     *                    TITLE_WAIT_PAY_FLAG=1,
     *                    TITLE_SEND_OUT_FLAG=2,
     *                    TITLE_SIGN_FLAG=4,
     *                    TITLE_COMMENT_FLAG=5,
     *                    TITLE_AFTER_SALE_FLAG=7,)
     * @return
     */
    public static String getOrderDetail(int pcustomerId, int orderType) {
        StringBuilder stringBuilder = new StringBuilder(order_detail_pre);
        stringBuilder.append("" + pcustomerId);
        if (orderType != 0) {//如果不是0的话，就是下面四个小分类
            stringBuilder.append("&ordType=");
            stringBuilder.append("" + orderType);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取注册链接
     *
     * @param pemail
     * @param pphoneNumber
     * @param ppassword
     * @return
     */
    public static String getRegister(String pemail, String pphoneNumber, String ppassword) {
        StringBuilder stringBuilder = new StringBuilder(register_pre);
        stringBuilder.append(pemail);
        stringBuilder.append("&phone=");
        stringBuilder.append(pphoneNumber);
        stringBuilder.append("&password=");
        stringBuilder.append(ppassword);
        return stringBuilder.toString();
    }

    /**
     * 忘记密码，向服务器发送邮箱验证消息
     *
     * @param pemail
     * @return
     */
    public static String getForgetPasswrod(String pemail) {
        StringBuilder stringBuilder = new StringBuilder(forget_pre);
        stringBuilder.append(pemail);
        return stringBuilder.toString();
    }

    /**
     * 获取支付成功与否的状态请求链接
     *
     * @return
     */
    public static String getPayState(String pordersNo) {
        StringBuilder stringBuilder = new StringBuilder(pay_state_pre);
        stringBuilder.append(pordersNo);
        return stringBuilder.toString();
    }

    /**
     * 确认收货请求链接
     *
     * @return
     */
    public static String getSignState(String pordersNo) {
        StringBuilder stringBuilder = new StringBuilder(sign_state_pre);
        stringBuilder.append(pordersNo);
        return stringBuilder.toString();
    }

    /**
     * 向服务器端发送评价信息
     *
     * @return
     */
    public static String getComment() {
        StringBuilder stringBuilder = new StringBuilder(comment_pre);
        return stringBuilder.toString();
    }

    /**
     * 删除订单请求链接
     *
     * @return
     */
    public static String getDelOrder(int pordersId) {
        StringBuilder stringBuilder = new StringBuilder(del_order_pre);
        stringBuilder.append(pordersId);
        return stringBuilder.toString();
    }

    /**
     * 退货请求连接
     *
     * @param pordersNo               订单编号
     * @param productId               退的商品id
     * @param pcount                  退的商品数量
     * @param returnType              退货类型：1仅退款2退款且退货
     * @param returnReasonType        退款原因类型：1收到商品破损,2商品错发/漏发,3商品需要维修,4发票问题,5收到商品不符,6商品质量问题,7未收到货,8未按约定时间发货,9其他
     * @param returnReasonDescription 退款说明
     * @param txImage                 传入文件路径
     * @param txImageFileName         文件名称
     * @return
     */
    public static String getReturnSales(int pordersNo, int productId, int pcount, int returnType, int returnReasonType, String returnReasonDescription, String txImage, String txImageFileName) {
        StringBuilder stringBuilder = new StringBuilder(return_sales_pre);
        stringBuilder.append("" + pordersNo);
        stringBuilder.append("&productId=");
        stringBuilder.append("" + productId);

        stringBuilder.append("&count=");
        stringBuilder.append("" + pcount);

        stringBuilder.append("&returnType=");
        stringBuilder.append("" + returnType);

        stringBuilder.append("&returnReasonType=");
        stringBuilder.append("" + returnReasonType);

        stringBuilder.append("&returnReasonDescription=");
        stringBuilder.append("" + returnReasonDescription);

        stringBuilder.append("&txImage=");
        stringBuilder.append("" + txImage);

        stringBuilder.append("&txImageFileName=");
        stringBuilder.append("" + txImageFileName);

        return stringBuilder.toString();
    }

    /**
     * 重载上面的退货，在post请求中使用
     *
     * @return
     */
    public static String getReturnSales() {
        StringBuilder stringBuilder = new StringBuilder(return_sales_pre2);

        return stringBuilder.toString();
    }

    /**
     * 刷新用户信息
     *
     * @param pcustomerId
     * @return
     */
    public static String getFlush(int pcustomerId) {
        StringBuilder stringBuilder = new StringBuilder(flush_pre);
        stringBuilder.append(pcustomerId);
        return stringBuilder.toString();
    }

    public static String getCart(int pcustomerId){
        StringBuilder stringBuilder = new StringBuilder(cart_pre);
        stringBuilder.append(pcustomerId);
        return stringBuilder.toString();
    }
}
