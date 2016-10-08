package com.lanou3g.carhome.recommend.tbvideo;

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
public class TabVideoAdapter extends BaseAdapter{

    private Context context;
    private TabVideoBean bean;

    public TabVideoAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TabVideoBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TabVideoViewHolder videoViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_video, null);
            videoViewHolder = new TabVideoViewHolder(convertView);
            convertView.setTag(videoViewHolder);
        } else {
            videoViewHolder = (TabVideoViewHolder) convertView.getTag();
        }

        videoViewHolder.tvTitle.setText(bean.getResult().getList().get(position).getTitle());
        videoViewHolder.tvDate.setText(bean.getResult().getList().get(position).getTime());
        videoViewHolder.tvPlays.setText(bean.getResult().getList().get(position).getPlaycount() + "播放");
        videoViewHolder.tvRpc.setText(bean.getResult().getList().get(position).getReplycount() + "评论");

        Picasso.with(context).load(bean.getResult().getList().get(position).getSmallimg()).into(videoViewHolder.image);

        return convertView;
    }

    private class TabVideoViewHolder {

        private final TextView tvDate;
        private final TextView tvRpc;
        private final ImageView image;
        private final TextView tvTitle;
        private final TextView tvPlays;

        public TabVideoViewHolder(View view) {
            tvPlays = (TextView) view.findViewById(R.id.tv_plays_item_recommend_video);
            tvDate = (TextView) view.findViewById(R.id.tv_date_item_recommdend_video);
            tvRpc = (TextView) view.findViewById(R.id.tv_rpc_item_recommend_video);
            image = (ImageView) view.findViewById(R.id.iv_item_recommdend_video);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_item_recommend_video);
        }
    }
}
