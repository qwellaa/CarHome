package com.lanou3g.carhome.forum.select;

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
public class SelectAdapter extends BaseAdapter{

    private Context context;
    private SelectBean bean;

    public SelectAdapter(Context context) {
        this.context = context;
    }

    public void setBean(SelectBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return bean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SelectViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_select, null);
            viewHolder = new SelectViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SelectViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(bean.getResult().getList().get(i).getTitle());
        viewHolder.tvDate.setText(bean.getResult().getList().get(i).getReplycounts() + "回帖");
        viewHolder.tvComment.setText(bean.getResult().getList().get(i).getBbsname());

        Picasso.with(context).load(bean.getResult().getList().get(i).getSmallpic()).into(viewHolder.ivImage);

        return view;
    }

    private class SelectViewHolder {

        private final ImageView ivImage;
        private final TextView tvComment;
        private final TextView tvDate;
        private final TextView tvTitle;

        public SelectViewHolder(View view) {
            ivImage = (ImageView) view.findViewById(R.id.item_select_image);
            tvComment = (TextView) view.findViewById(R.id.item_select_comment);
            tvDate = (TextView) view.findViewById(R.id.item_select_date);
            tvTitle = (TextView) view.findViewById(R.id.item_select_title);
        }
    }
}
