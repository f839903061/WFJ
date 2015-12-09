package hy.zc.wfj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hy.zc.wfj.R;
import hy.zc.wfj.adapter.base.BBaseAdapter;
import hy.zc.wfj.customview.MyGridView;
import hy.zc.wfj.data.CategroyJsonObject;

/**
 * 分类界面的右边的listview的适配器
 * Created by feng on 2015/10/27.
 */
public class RightListAdapter extends BBaseAdapter {
    private List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> mList = null;
    private Context mContext;

    public RightListAdapter(Context pcontext, List plist) {
        super(pcontext, plist);
        mList = plist;
        mContext = pcontext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.category_right_list_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.right_item_title);
            viewHolder.myGridView = (MyGridView) convertView.findViewById(R.id.category_right_grid);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CategroyJsonObject.DataEntity.ChildProductTypeEntity entity = mList.get(position);
        viewHolder.textView.setText(entity.getSortName());
        List<CategroyJsonObject.DataEntity.ChildProductTypeEntity> rrList = entity.getChildProductType();
        RightGridAdapter rightGridAdapter = new RightGridAdapter(mContext, rrList);
        viewHolder.myGridView.setAdapter(rightGridAdapter);
        return convertView;
    }

    public class ViewHolder {
        TextView textView;
        MyGridView myGridView;
    }

}
