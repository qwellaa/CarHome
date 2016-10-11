package com.lanou3g.carhome.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.carhome.R;

import java.util.List;

/**
 *
 */
public class SearchHistoryAdapter extends BaseAdapter{

    private List<SearchHistoryBean> beanList;
    private Context context;

    public SearchHistoryAdapter(Context context) {
        this.context = context;
    }

    public void setBeanList(List<SearchHistoryBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search, null);
            viewHolder = new HistoryViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HistoryViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(beanList.get(position).getName());

        return convertView;
    }

    private class HistoryViewHolder {
        private final TextView tvName;
        public HistoryViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_name_item_search);
        }
    }
}
