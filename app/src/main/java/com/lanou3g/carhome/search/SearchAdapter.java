package com.lanou3g.carhome.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.carhome.R;

/**
 *
 */
public class SearchAdapter extends BaseAdapter{

    private Context context;
    private SearchBean bean;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setBean(SearchBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getWordlist().size();
    }

    @Override
    public Object getItem(int position) {
        return bean;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search, null);
            viewHolder = new SearchViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SearchViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(bean.getResult().getWordlist().get(position).getName());

        return convertView;

    }

    private class SearchViewHolder {

        private final TextView tvName;

        public SearchViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_name_item_search);
        }
    }
}
