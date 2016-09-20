package com.lanou3g.carhome.recommend;

import android.content.Context;
import android.util.Log;
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
public class RecommendAdapter extends BaseAdapter {

    Context context;
    RecommendBean bean;

    public RecommendAdapter(Context context) {
        this.context = context;
    }

    public void setBean(RecommendBean bean) {
        this.bean = bean;
        Log.d("RecommendAdapter", "bean.getResult().getNewslist().size():" + bean.getResult().getNewslist().size());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getResult().getNewslist().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RecommendViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_recommend, null);
            viewHolder = new RecommendViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (RecommendViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(bean.getResult().getNewslist().get(i).getTitle());
        viewHolder.tvDate.setText(bean.getResult().getNewslist().get(i).getTime());
        if (!(bean.getResult().getNewslist().get(i).getSmallpic().equals(""))) {
            Picasso.with(context).load(bean.getResult().getNewslist().get(i).getSmallpic()).into(viewHolder.ivImage);
        }
        if (bean.getResult().getNewslist().get(i).getMediatype() == 3) {
            viewHolder.tvComment.setText(bean.getResult().getNewslist().get(i).getReplycount() + "播放");
        } else if (bean.getResult().getNewslist().get(i).getMediatype() == 2) {
            viewHolder.tvComment.setText(bean.getResult().getNewslist().get(i).getReplycount() + "评论");
        } else if (bean.getResult().getNewslist().get(i).getMediatype() == 1) {
            viewHolder.tvComment.setText(bean.getResult().getNewslist().get(i).getReplycount() + "评论");
        }
        return view;
    }

    private class RecommendViewHolder {

        private final ImageView ivImage;
        private final TextView tvDate;
        private final TextView tvComment;
        private final TextView tvTitle;

        public RecommendViewHolder(View view) {

            ivImage = (ImageView) view.findViewById(R.id.item_recommend_image);
            tvDate = (TextView) view.findViewById(R.id.item_recommend_date);
            tvComment = (TextView) view.findViewById(R.id.item_recommend_comment);
            tvTitle = (TextView) view.findViewById(R.id.item_recommend_title);
        }
    }

}
