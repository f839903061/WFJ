package hy.zc.wfj.adapter.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hy.zc.wfj.utility.LogUtil;

/**
 * Created by feng on 2015/11/25.
 */
public abstract class BBaseAdapter extends BaseAdapter implements LogUtil{


    private Context mContex;
    private LayoutInflater mInflater;
    private List mList=new ArrayList();

    public BBaseAdapter(Context pcontext,List plist) {
        mContex=pcontext;
        mInflater=LayoutInflater.from(mContex);
        mList.addAll(plist);
    }

    public LayoutInflater getInflater() {
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

    @Override
    public void showLogi(String ptext) {
        Log.i(TAG, getClass().getName() + " " + ptext);
    }

    @Override
    public void showLogd(String ptext) {
        Log.d(TAG, getClass().getName() + " " + ptext);
    }

    @Override
    public void showLoge(String ptext) {
        Log.e(TAG, getClass().getName() + " " + ptext);
    }

    @Override
    public void showLogw(String ptext) {
        Log.w(TAG, getClass().getName() + " " + ptext);
    }

    public void showToast(String ptext){
        Toast.makeText(mContex,ptext,Toast.LENGTH_SHORT).show();
    }
}
