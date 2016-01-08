package hy.zc.wfj.utility;

import android.app.ProgressDialog;
import android.content.Context;

import hy.zc.wfj.R;

/**
 * Created by feng on 2016/1/7.
 */
public class ProgressUtil {
    private static Context context = null;
    private static ProgressUtil instance = null;
    private static ProgressDialog progressDialog = null;

    private ProgressUtil() {
    }

    public static ProgressUtil getInstance(Context ctx) {

        context = ctx;

        if (instance == null) {
            instance = new ProgressUtil();
        }

        return instance;
    }

    /**
     * 查看对话框是否打开
     *
     * @return
     */
    public boolean isShowing() {
        return progressDialog != null;
    }

    /**
     * 显示对话框，提示文本固定，一般与close配套使用
     */
    public void show() {
        if (progressDialog == null || (progressDialog != null && !progressDialog.isShowing())) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);//设置ProgressDialog 是否可以按返回键取消
            progressDialog.setIndeterminate(true);//设置ProgressDialog 的进度条是否不明确
//            progressDialog.setTitle("Validating...");
            progressDialog.setMessage(context.getResources().getString(R.string.text_wait));
            progressDialog.show();
        }
    }

    /**
     * 重启启动一个对话框，我没用过
     */
    public void forceShow() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
//        progressDialog.setTitle("Validating...");
        progressDialog.setMessage(context.getResources().getString(R.string.text_wait));
        progressDialog.show();
    }

    /**
     * 显示对话框
     *
     * @param msg 自定义显示文本
     */
    public void show(String msg) {
        if (progressDialog == null || (progressDialog != null && !progressDialog.isShowing())) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(msg);
            progressDialog.show();
        }
    }

    /**
     * 定时自动关闭的对话框
     *
     * @param intervalClose 多少秒之后关闭对话框，单位：秒
     */
    public void autoCloseShow(final int intervalClose) {
        if (progressDialog == null || (progressDialog != null && !progressDialog.isShowing())) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);//设置ProgressDialog 是否可以按返回键取消
            progressDialog.setIndeterminate(true);//设置ProgressDialog 的进度条是否不明确
            progressDialog.setMessage(context.getResources().getString(R.string.text_wait));
            progressDialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(intervalClose * 1000);
                        progressDialog.dismiss();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 同上
     *
     * @param msg           提示文本
     * @param intervalClose 对话框持续时间，单位：秒
     */
    public void autoCloseShow(String msg, final int intervalClose) {
        if (progressDialog == null || (progressDialog != null && !progressDialog.isShowing())) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);//设置ProgressDialog 是否可以按返回键取消
            progressDialog.setIndeterminate(true);//设置ProgressDialog 的进度条是否不明确
            progressDialog.setMessage(msg);
            progressDialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(intervalClose * 1000);
                        progressDialog.dismiss();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 关闭对话框
     */
    public void close() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
