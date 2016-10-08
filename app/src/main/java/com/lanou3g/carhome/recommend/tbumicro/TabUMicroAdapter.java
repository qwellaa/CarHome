package com.lanou3g.carhome.recommend.tbumicro;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 *
 */
public class TabUMicroAdapter extends BaseAdapter{

    Context context;
    TabUMicroBean bean;

    public TabUMicroAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TabUMicroBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getNewslist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
