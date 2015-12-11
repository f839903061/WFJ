package hy.zc.wfj.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这个类功能就是检查邮箱或者电话号码是否正确
 * Created by feng on 2015/11/27.
 */
public class CheckEmailPhone {
    /**
     * 判断输入的手机号是否正确
     *
     * @param mobiles
     * @return
     */
    public static boolean isPhoneNum(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断输入的邮箱格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
