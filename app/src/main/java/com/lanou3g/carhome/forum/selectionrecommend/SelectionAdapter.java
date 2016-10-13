package com.lanou3g.carhome.forum.selectionrecommend;

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
public class SelectionAdapter extends BaseAdapter{

    Context context;
    SelectionBean bean;

    public SelectionAdapter(Context context) {
        this.context = context;
    }

    public void setBean(SelectionBean bean) {
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
        SelectionViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_selection, null);
            viewHolder = new SelectionViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SelectionViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(bean.getResult().getList().get(i).getTitle());
        viewHolder.tvDate.setText(bean.getResult().getList().get(i).getReplycounts() + "回帖");
        viewHolder.tvComment.setText(bean.getResult().getList().get(i).getBbsname());

        Picasso.with(context).load(bean.getResult().getList().get(i).getSmallpic()).into(viewHolder.ivImage);

        return view;
    }

    public void setBean1(SelectionBean response) {

        bean.getResult().getList().addAll(response.getResult().getList());
        notifyDataSetChanged();

    }

    private class SelectionViewHolder {
        private final ImageView ivImage;
        private final TextView tvComment;
        private final TextView tvDate;
        private final TextView tvTitle;
        public SelectionViewHolder(View view) {
            ivImage = (ImageView) view.findViewById(R.id.item_selection_image);
            tvComment = (TextView) view.findViewById(R.id.item_selection_comment);
            tvDate = (TextView) view.findViewById(R.id.item_selection_date);
            tvTitle = (TextView) view.findViewById(R.id.item_selection_title);
        }
    }
}
