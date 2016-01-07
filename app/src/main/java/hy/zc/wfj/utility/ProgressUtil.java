package hy.zc.wfj.utility;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by feng on 2016/1/7.
 */
public class ProgressUtil {
    private static Context context = null;
    private static ProgressUtil instance = null;
    private static ProgressDialog progressDialog = null;

    private ProgressUtil() {}

    public static ProgressUtil getInstance(Context ctx) {

        context = ctx;

        if(instance == null) {
            instance = new ProgressUtil();
        }

        return instance;
    }

    public boolean isShowing() {
        return progressDialog != null;
    }

    public void show() {
        if (progressDialog == null || (progressDialog != null && !progressDialog.isShowing())) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(true);
            progressDialog.setIndeterminate(true);
            progressDialog.setTitle("Validating...");
            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }
    }

    public void forceShow() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Validating...");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
    }

    public void show(String msg) {
        if (progressDialog == null || (progressDialog != null && !progressDialog.isShowing())) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(true);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(msg);
            progressDialog.show();
        }
    }

    public void forceShow(String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public void close() {
        if(progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
