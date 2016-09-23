package com.lanou3g.carhome.discover.formerecommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.discover.DiscoverBean;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class ForMeRecommedAdapter extends RecyclerView.Adapter<ForMeRecommedAdapter.ForMeViewHolder>{

    Context context;
    DiscoverBean bean;
    private int id;

    public ForMeRecommedAdapter(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    public void setBean(DiscoverBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public ForMeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_discover_for_me, parent, false);
        ForMeViewHolder viewHolder = new ForMeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForMeViewHolder holder, int position) {

        holder.tvTitle.setText(bean.getResult().getCardlist().get(id).getData().get(position).getTitle());
        holder.tvBody.setText(bean.getResult().getCardlist().get(id).getData().get(position).getSubtitle());
        holder.tvNowPrice.setText(bean.getResult().getCardlist().get(id).getData().get(position).getCurrentprice());

        if (bean.getResult().getCardlist().get(id).getData().get(position).getPrice().equals("")) {
            holder.tvOrPrice.setText("");
        } else {
            holder.tvOrPrice.setText(bean.getResult().getCardlist().get(id).getData().get(position).getPrice());
        }

        Picasso.with(context).load(bean.getResult().getCardlist().get(id).getData().get(position).getImageurl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getResult().getCardlist().get(id).getData().size();
    }

    public class ForMeViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvOrPrice;
        private final TextView tvNowPrice;
        private final TextView tvBody;
        private final TextView tvTitle;
        private final ImageView image;

        public ForMeViewHolder(View itemView) {
            super(itemView);

            tvOrPrice = (TextView) itemView.findViewById(R.id.item_discover_for_me_original_price);
            tvNowPrice = (TextView) itemView.findViewById(R.id.item_discover_for_me_now_price);
            tvBody = (TextView) itemView.findViewById(R.id.item_discover_for_me_body);
            tvTitle = (TextView) itemView.findViewById(R.id.item_discover_for_me_title);
            image = (ImageView) itemView.findViewById(R.id.item_discover_for_me_image);
        }
    }
}
