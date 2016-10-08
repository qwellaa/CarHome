package com.lanou3g.carhome.recommend.tbumicro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.forum.tyfourhours.TyFourHoursCircleImage;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class TabUMicroAdapter extends BaseAdapter{

    Context context;
    TabUMicroBean bean;

    public TabUMicroAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TabUMicroBean bean) {
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
        TabUMicroViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tab_u_micro, null);
            viewHolder = new TabUMicroViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TabUMicroViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(bean.getResult().getNewslist().get(position).getUsername());
        viewHolder.tvTitle.setText(bean.getResult().getNewslist().get(position).getTitle());
        viewHolder.tvDate.setText(bean.getResult().getNewslist().get(position).getPublishtime());
        viewHolder.tvRpc.setText(bean.getResult().getNewslist().get(position).getReplycount() + "");
        viewHolder.tvZan.setText(bean.getResult().getNewslist().get(position).getPraisenum() + "");

        if (bean.getResult().getNewslist().get(position).getUserpic().equals("")) {
            viewHolder.image.setImageResource(R.mipmap.ahlib_userpic_default);
        } else {
            Picasso.with(context).load(bean.getResult().getNewslist().get(position).getUserpic()).into(viewHolder.imageHead);
        }

        if (3 == bean.getResult().getNewslist().get(position).getMediatype()) {
            Picasso.with(context).load(bean.getResult().getNewslist().get(position).getIndexdetail().get(0)).into(viewHolder.image);
        } else if (1 == bean.getResult().getNewslist().get(position).getMediatype()) {
            Picasso.with(context).load(bean.getResult().getNewslist().get(position).getThumbnailpics().get(0)).into(viewHolder.image);
        }

        return convertView;
    }

    private class TabUMicroViewHolder {

        private final TyFourHoursCircleImage imageHead;
        private final TextView tvZan;
        private final TextView tvRpc;
        private final TextView tvDate;
        private final ImageView image;
        private final TextView tvTitle;
        private final TextView tvName;

        public TabUMicroViewHolder(View view) {
            tvZan = (TextView) view.findViewById(R.id.tv_zan_item_tab_u_micro);
            tvRpc = (TextView) view.findViewById(R.id.tv_rpc_item_tab_u_micro);
            tvDate = (TextView) view.findViewById(R.id.tv_date_item_tab_u_micro);
            image = (ImageView) view.findViewById(R.id.iv_item_tab_u_micro);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_item_tab_u_micro);
            tvName = (TextView) view.findViewById(R.id.tv_name_item_tab_u_micro);
            imageHead = (TyFourHoursCircleImage) view.findViewById(R.id.iv_head_item_tab_u_micro);
        }
    }
}
