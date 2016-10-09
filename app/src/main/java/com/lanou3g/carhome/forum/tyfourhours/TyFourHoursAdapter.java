package com.lanou3g.carhome.forum.tyfourhours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class TyFourHoursAdapter extends BaseAdapter{

    Context context;
    TyFourHoursBean bean;

    public TyFourHoursAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TyFourHoursBean bean) {
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
        TfHoursViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tyfour_hours, null);
            viewHolder = new TfHoursViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (TfHoursViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(bean.getResult().getList().get(i).getTitle());
        viewHolder.tvReply.setText(bean.getResult().getList().get(i).getReplycounts() + "回");
        viewHolder.tvForumType.setText(bean.getResult().getList().get(i).getBbsname());
        viewHolder.tvDate.setText(bean.getResult().getList().get(i).getPostdate().toString().substring(5,16));
        viewHolder.tvName.setText(bean.getResult().getList().get(i).getNickname());

        if (bean.getResult().getList().get(i).getHeadimg().equals("")) {
            viewHolder.image.setImageResource(R.mipmap.ahlib_userpic_default);
        } else {
            Picasso.with(context).load(bean.getResult().getList().get(i).getHeadimg()).into(viewHolder.image);
        }

        if (bean.getResult().getList().get(i).getAuthseries().equals("")) {
            viewHolder.tvCarName.setText("");
        } else {
            viewHolder.tvCarName.setText(bean.getResult().getList().get(i).getAuthseries());
        }

        if (bean.getResult().getList().get(i).getTopicinfo().equals("")) {
            viewHolder.tvContent.setText("");
        } else {
            viewHolder.tvContent.setText(bean.getResult().getList().get(i).getTopicinfo());
        }


        return view;
    }

    private class TfHoursViewHolder {

        private final TyFourHoursCircleImage image;
        private final TextView tvForumType;
        private final TextView tvReply;
        private final TextView tvContent;
        private final TextView tvTitle;
        private final TextView tvDate;
        private final TextView tvCarName;
        private final TextView tvName;

        public TfHoursViewHolder(View view) {

            tvForumType = (TextView) view.findViewById(R.id.tv_forum_type_item_tyfour_hours);
            tvReply = (TextView) view.findViewById(R.id.tv_reply_item_tyfour_hours);
            tvContent = (TextView) view.findViewById(R.id.tv_content_item_tyfour_hours);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_item_tyfour_hours);
            tvDate = (TextView) view.findViewById(R.id.tv_date_item_tyfour_hours);
            tvCarName = (TextView) view.findViewById(R.id.tv_carname_item_tyfour_hours);
            tvName = (TextView) view.findViewById(R.id.tv_name_item_tyfour_hours);
            image = (TyFourHoursCircleImage) view.findViewById(R.id.iv_item_tyfour_hours);
        }
    }
}
