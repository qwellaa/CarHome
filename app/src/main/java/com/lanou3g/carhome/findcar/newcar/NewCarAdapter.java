package com.lanou3g.carhome.findcar.newcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.carhome.R;

import java.util.List;



/**
 * Created by dllo on 16/9/27.
 */
public class NewCarAdapter extends BaseAdapter {
    private Context context;
    private List<CarNameBean> list;
    private ViewHolder viewHolder;

    public NewCarAdapter(Context context, List<CarNameBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        if (list.get(position).getName().length() == 1)// 如果是字母索引
            return false;// 表示不能点击
        return super.isEnabled(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = list.get(position).getName();
        viewHolder = new ViewHolder();
        if (item.length() == 1) {
            convertView = LayoutInflater.from(context).inflate(R.layout.index, null);
            viewHolder.indexTv = (TextView) convertView.findViewById(R.id.indexTv);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_new_car, null);
            viewHolder.itemTv = (TextView) convertView.findViewById(R.id.tv_item_new_car);
        }
        if (item.length() == 1) {
            viewHolder.indexTv.setText(list.get(position).getName());
        } else {


            viewHolder.itemTv.setText(list.get(position).getName());

        }
        return convertView;
    }

    private class ViewHolder {
        private TextView indexTv;
        private TextView itemTv;
    }

}

