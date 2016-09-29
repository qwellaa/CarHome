package com.lanou3g.carhome.findcar.newcar.popularbrand;

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
public class PopularBrandAdapter extends RecyclerView.Adapter<PopularBrandAdapter.ViewHorlder>{

    private Context context;
    private PopularBrandBean bean;

    public PopularBrandAdapter(Context context) {
        this.context = context;
    }

    public void setBean(PopularBrandBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHorlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_car_popular_brand, parent, false);
        ViewHorlder viewHorlder = new ViewHorlder(view);
        return viewHorlder;
    }

    @Override
    public void onBindViewHolder(ViewHorlder holder, int position) {
        holder.brand.setText(bean.getResult().getList().get(position).getName());
        Picasso.with(context).load(bean.getResult().getList().get(position).getImg()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getResult().getList().size();
    }

    public class ViewHorlder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView brand;

        public ViewHorlder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_item_new_car_popular_brand);
            brand = (TextView) itemView.findViewById(R.id.tv_item_new_car_popular_brand);
        }
    }
}
