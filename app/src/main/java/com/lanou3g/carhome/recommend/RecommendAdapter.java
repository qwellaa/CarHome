package com.lanou3g.carhome.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lanou3g.carhome.R;

import java.util.ArrayList;

/**
 *
 */
public class RecommendAdapter extends BaseAdapter{

    Context context;
    ArrayList<RecommendBean> arrayList;

    public RecommendAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<RecommendBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RecommendViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_recommend, null);
        }
        return null;
    }

    private class RecommendViewHolder{
        public RecommendViewHolder(View view) {

        }
    }
}
