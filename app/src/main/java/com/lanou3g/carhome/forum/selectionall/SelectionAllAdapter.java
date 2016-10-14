package com.lanou3g.carhome.forum.selectionall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.carhome.R;

import java.util.ArrayList;

/**
 *
 */
public class SelectionAllAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<String> arrayList;

    public SelectionAllAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
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
        AllViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_selection_all, null);
            viewHolder = new AllViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (AllViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(arrayList.get(i));

        return view;
    }

    private class AllViewHolder {

        private final TextView tvTitle;

        public AllViewHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_title_item_selection_all);
        }
    }
}
