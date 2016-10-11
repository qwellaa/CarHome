package com.lanou3g.carhome.discover.activityzone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.discover.DiscoverBean;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class ActivityZoneAdapter extends RecyclerView.Adapter<ActivityZoneAdapter.ViewHolder>{

    private Context context;
    private DiscoverBean bean;
    private int id;
    OnZoneRecyclerItemClickListener onZoneRecyclerItemClickListener;

    public void setOnZoneRecyclerItemClickListener(OnZoneRecyclerItemClickListener onZoneRecyclerItemClickListener) {
        this.onZoneRecyclerItemClickListener = onZoneRecyclerItemClickListener;
    }

    public ActivityZoneAdapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_discover_activity_zone, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(bean.getResult().getCardlist().get(id).getData().get(position).getImageurl())
                .placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_carback).into(holder.image);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onZoneRecyclerItemClickListener.click(position, holder, bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getResult().getCardlist().get(id).getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_item_discover_activity_zone);
            image = (ImageView) itemView.findViewById(R.id.item_discover_activity_zone_image);
        }
    }
}
