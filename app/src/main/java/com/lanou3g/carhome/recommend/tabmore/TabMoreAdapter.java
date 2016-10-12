package com.lanou3g.carhome.recommend.tabmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class TabMoreAdapter extends RecyclerView.Adapter<TabMoreAdapter.ViewHolder>{

    private Context context;
    private TabMoreBean bean;

    public TabMoreAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TabMoreBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_tabmore, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(bean.getResult().getMetalist().get(0).getList().get(position).getName());

        Picasso.with(context).load(bean.getResult().getMetalist().get(0).getList().get(position).getIconurl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getResult().getMetalist().get(0).getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final ImageView image;
        private final LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_recommdend_tabmore);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_recommdend_tabmore);
            image = (ImageView) itemView.findViewById(R.id.iv_item_recommdend_tabmore);
        }
    }
}
