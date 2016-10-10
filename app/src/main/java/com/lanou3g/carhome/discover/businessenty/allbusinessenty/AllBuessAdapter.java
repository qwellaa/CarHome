package com.lanou3g.carhome.discover.businessenty.allbusinessenty;

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
public class AllBuessAdapter extends RecyclerView.Adapter<AllBuessAdapter.ViewHolder>{

    private Context context;
    private AllBuessBean bean;
    private int count;

    public AllBuessAdapter(Context context, int count) {
        this.context = context;
        this.count = count;
    }

    public void setBean(AllBuessBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_businessenty, parent, false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(bean.getResult().get(count).getEntrylist().get(position).getTitle());
        Picasso.with(context).load(bean.getResult().get(count).getEntrylist().get(position).getIconurl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getResult().get(count).getEntrylist().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_item_all_business);
            tv = (TextView) itemView.findViewById(R.id.tv_item_all_business);
        }
    }
}
