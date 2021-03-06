package com.lanou3g.carhome.discover.businessenty;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.discover.DiscoverBean;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder>{

    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private OnRecyclerItemLongClickListener onRecyclerItemLongClickListener;

    private Context context;
    private DiscoverBean bean;
    private int id;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setOnRecyclerItemLongClickListener(OnRecyclerItemLongClickListener onRecyclerItemLongClickListener) {
        this.onRecyclerItemLongClickListener = onRecyclerItemLongClickListener;
    }

    public BusinessAdapter(Context context) {
        this.context = context;
    }

    public void setBean(DiscoverBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_discover_business_entry, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv.setText(bean.getResult().getCardlist().get(id).getData().get(position).getTitle());
        Picasso.with(context).load(bean.getResult().getCardlist().get(id).getData().get(position).getImageurl())
                .placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_carback).into(holder.image);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerItemClickListener.click(position, holder);
            }
        });

        holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onRecyclerItemLongClickListener.click(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getResult().getCardlist().get(id).getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final ImageView image;
        private final LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_item_business_entry);
            tv = (TextView) itemView.findViewById(R.id.tv_item_business_entry);
            image = (ImageView) itemView.findViewById(R.id.iv_item_business_entry);
        }
    }
}
