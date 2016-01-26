package hy.zc.wfj.utility;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import hy.zc.wfj.data.UserLoginObject;

/**
 * Created by feng on 2016/1/26.
 */
public class CustomObjectUtil {

    public static final String LOGIN_DATA="loginData";
    /**
     * 通过访问本地的sharedpreference读取数据
     *
     * @param pContext
     * @return
     */
    public static UserLoginObject getDataFromLocalSharedPre(Context pContext) {
        String temp = (String) SharedPrefUtility.getParam(pContext, LOGIN_DATA, "");
        if (temp == null || temp.equals("")) {
            return null;
        }
        UserLoginObject userLoginObject = JSON.parseObject(temp, UserLoginObject.class);
        return userLoginObject;
    }
}
