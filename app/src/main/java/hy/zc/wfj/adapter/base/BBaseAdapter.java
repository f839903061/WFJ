package hy.zc.wfj.adapter.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng on 2015/11/25.
 */
public abstract class BBaseAdapter extends BaseAdapter{

    public static final String TAG = "fengluchun";
    private Context mContex;
    private LayoutInflater mInflater;
    private List mList=new ArrayList();

    public BBaseAdapter(Context pcontext,List plist) {
        mContex=pcontext;
        mInflater=LayoutInflater.from(mContex);
        mList.addAll(plist);
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public  void showLogi(String ptext){
        Log.i(TAG, getClass().getName() + " " + ptext);
    }
    public  void showLogd(String ptext){
        Log.d(TAG, getClass().getName() + " " + ptext);
    }
    public  void showLogw(String ptext){
        Log.w(TAG, getClass().getName() + " " + ptext);
    }
    public  void showLoge(String ptext){
        Log.e(TAG, getClass().getName() + " " + ptext);
    }

}
