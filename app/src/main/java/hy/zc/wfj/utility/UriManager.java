package hy.zc.wfj.utility;

import android.net.Uri;

/**
 * Created by feng on 2015/11/27.
 */
public class UriManager {
    private static final String uri_pre="http://101.200.182.119:8080/phone/login.action?loginName=";
    public static Uri converStToUri(String ptext){
        StringBuilder stringBuilder=new StringBuilder(uri_pre);
        stringBuilder.append(ptext);
        Uri uri=Uri.parse(stringBuilder.toString());
        return uri;
    }
}
