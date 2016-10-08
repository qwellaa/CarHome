package com.lanou3g.carhome.recommend.tabmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class TabMarketAdapter extends BaseAdapter{

    private Context context;
    private TabMarketBean bean;

    public TabMarketAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TabMarketBean bean) {
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
        TabMarketViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_market, null);
            viewHolder = new TabMarketViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TabMarketViewHolder) convertView.getTag();
        }

        viewHolder.tvTilte.setText(bean.getResult().getNewslist().get(position).getTitle());
        viewHolder.tvDate.setText(bean.getResult().getNewslist().get(position).getTime());

        Picasso.with(context).load(bean.getResult().getNewslist().get(position).getSmallpic()).into(viewHolder.image);

        return convertView;
    }

    private class TabMarketViewHolder {

        private final ImageView image;
        private final TextView tvDate;
        private final TextView tvTilte;

        public TabMarketViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.iv_item_recommend_market);
            tvDate = (TextView) view.findViewById(R.id.tv_date_item_recommend_market);
            tvTilte = (TextView) view.findViewById(R.id.tv_title_item_recommend_market);
        }
    }
}
