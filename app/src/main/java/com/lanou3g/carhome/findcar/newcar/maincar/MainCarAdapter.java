package com.lanou3g.carhome.findcar.newcar.maincar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class MainCarAdapter extends RecyclerView.Adapter<MainCarAdapter.ViewHolder>{

    private Context context;
    private MainCarBean bean;

    public MainCarAdapter(Context context) {
        this.context = context;
    }

    public void setBean(MainCarBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_car_popular_brand, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(bean.getResult().getList().get(position).getSeriesname());
        Picasso.with(context).load(bean.getResult().getList().get(position).getImg()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getResult().getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_item_new_car_popular_brand);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_new_car_popular_brand);
        }
    }
}
