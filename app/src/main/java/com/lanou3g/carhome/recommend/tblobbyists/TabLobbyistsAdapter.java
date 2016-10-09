package com.lanou3g.carhome.recommend.tblobbyists;

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
public class TabLobbyistsAdapter extends BaseAdapter {

    private Context context;
    private TabLobbysitsBean bean;
    private TabNewsBean newsBean;
    private TabLettersBean lettersBean;
    private int count;

    public TabLobbyistsAdapter(Context context, int count) {
        this.context = context;
        this.count = count;
    }

    public void setBean(TabLobbysitsBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setNewsBean(TabNewsBean newsBean) {
        this.newsBean = newsBean;
        notifyDataSetChanged();
    }

    public void setLettersBean(TabLettersBean lettersBean) {
        this.lettersBean = lettersBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (2 == count) {
            return bean == null ? 0 : bean.getResult().getList().size();
        } else if (4 == count) {
            return lettersBean == null ? 0 : lettersBean.getResult().getList().size();
        } else {
            return newsBean == null ? 0 : newsBean.getResult().getNewslist().size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (2 == count) {
            return bean;
        } else if (4 == count) {
            return lettersBean;
        } else {
            return newsBean;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TabLobbysitsViewHolder viewHolder = null;
        TabLettersViewHolder viewHolder1 = null;
        if (convertView == null) {
            if (4 == count) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_letters, null);
                viewHolder1 = new TabLettersViewHolder(convertView);
                convertView.setTag(viewHolder1);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend, null);
                viewHolder = new TabLobbysitsViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
        } else {
            if (4 == count) {
                viewHolder1 = (TabLettersViewHolder) convertView.getTag();
            } else {
                viewHolder = (TabLobbysitsViewHolder) convertView.getTag();
            }
        }

        if (4 == count) {
            viewHolder1.tvTitle.setText(lettersBean.getResult().getList().get(position).getTitle());
            viewHolder1.tvDate.setText(lettersBean.getResult().getList().get(position).getCreatetime());
            viewHolder1.tvRpc.setText(Math.round((lettersBean.getResult().getList().get(position).getReviewcount() / 10000.0) * 10) / 10.0 + "万位观众");
            Picasso.with(context).load(lettersBean.getResult().getList().get(position).getBgimage()).into(viewHolder1.ivImage);
        } else {
            if (2 == count) {
                viewHolder.tvTitle.setText(bean.getResult().getList().get(position).getTitle());
                viewHolder.tvComment.setText(bean.getResult().getList().get(position).getReplycount() + "评论");
                viewHolder.tvDate.setText(bean.getResult().getList().get(position).getTime());

                Picasso.with(context).load(bean.getResult().getList().get(position).getSmallpic()).into(viewHolder.ivImage);
            } else {
                viewHolder.tvTitle.setText(newsBean.getResult().getNewslist().get(position).getTitle());
                viewHolder.tvComment.setText(newsBean.getResult().getNewslist().get(position).getReplycount() + "评论");
                viewHolder.tvDate.setText(newsBean.getResult().getNewslist().get(position).getTime());

                Picasso.with(context).load(newsBean.getResult().getNewslist().get(position).getSmallpic()).into(viewHolder.ivImage);
            }
        }
        return convertView;
    }

    private class TabLobbysitsViewHolder {
        private final ImageView ivImage;
        private final TextView tvDate;
        private final TextView tvComment;
        private final TextView tvTitle;

        public TabLobbysitsViewHolder(View view) {
            ivImage = (ImageView) view.findViewById(R.id.item_recommend_image);
            tvDate = (TextView) view.findViewById(R.id.item_recommend_date);
            tvComment = (TextView) view.findViewById(R.id.item_recommend_comment);
            tvTitle = (TextView) view.findViewById(R.id.item_recommend_title);
        }
    }

    private class TabLettersViewHolder {

        private final TextView tvDate;
        private final TextView tvRpc;
        private final ImageView ivImage;
        private final TextView tvTitle;

        public TabLettersViewHolder(View view) {
            tvDate = (TextView) view.findViewById(R.id.tv_date_item_recommdend_letters);
            tvRpc = (TextView) view.findViewById(R.id.rpc_item_recommdend_letters);
            ivImage = (ImageView) view.findViewById(R.id.iv_item_recommdend_letters);
            tvTitle = (TextView) view.findViewById(R.id.tv_item_recommdend_letters);
        }
    }
}
