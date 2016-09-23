package com.lanou3g.carhome.discover.goodslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.discover.DiscoverBean;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class GoodsListAdapter extends BaseAdapter{

    private Context context;
    private DiscoverBean bean;

    public GoodsListAdapter(Context context) {
        this.context = context;
    }

    public void setBean(DiscoverBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getCardlist().get(11).getData().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getResult().getCardlist().get(11).getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GoodsListViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_discover_goods_list, null);
            viewHolder = new GoodsListViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (GoodsListViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(bean.getResult().getCardlist().get(11).getData().get(i).getShorttitle());
        viewHolder.tvBody.setText(bean.getResult().getCardlist().get(11).getData().get(i).getAdinfo());
        viewHolder.tvNowPrice.setText(bean.getResult().getCardlist().get(11).getData().get(i).getPrice());
        if (bean.getResult().getCardlist().get(11).getData().get(i).getFctprice().equals("")) {
            viewHolder.tvOrPrice.setText("");
        } else {
            viewHolder.tvOrPrice.setText(bean.getResult().getCardlist().get(11).getData().get(i).getFctprice());
        }

        Picasso.with(context).load(bean.getResult().getCardlist().get(11).getData().get(i).getLogo()).into(viewHolder.image);

        return view;
    }

    private class GoodsListViewHolder {

        private final TextView tvOrPrice;
        private final TextView tvNowPrice;
        private final TextView tvBody;
        private final TextView tvTitle;
        private final ImageView image;

        public GoodsListViewHolder(View view) {
            tvOrPrice = (TextView) view.findViewById(R.id.item_discover_goods_list_original_price);
            tvNowPrice = (TextView) view.findViewById(R.id.item_discover_goods_list_now_price);
            tvBody = (TextView) view.findViewById(R.id.item_discover_goods_list_body);
            tvTitle = (TextView) view.findViewById(R.id.item_discover_goods_list_title);
            image = (ImageView) view.findViewById(R.id.item_discover_goods_list_image);
        }
    }
}
