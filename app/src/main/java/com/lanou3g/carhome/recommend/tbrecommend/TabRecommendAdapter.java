package com.lanou3g.carhome.recommend.tbrecommend;

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
public class TabRecommendAdapter extends BaseAdapter {

    Context context;
    TabRecommendBean bean;
    private final int TYPE_COUNT = 2;
    private final int TYPE_NORMAL = 0;
    private final int TYPE_THREE_IMAGE = 1;

    public TabRecommendAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TabRecommendBean bean) {
        this.bean = bean;
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
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (bean.getResult().getNewslist().get(position).getMediatype() == 6){
            return TYPE_THREE_IMAGE;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RecommendViewHolder viewHolder = null;
        RecommendViewHolder1 viewHolder1 = null;
        int type = getItemViewType(i);
        if (view == null) {
            if (type == TYPE_THREE_IMAGE) {
                view = LayoutInflater.from(context).inflate(R.layout.item_recommend_img, null);
                viewHolder1 = new RecommendViewHolder1(view);
                view.setTag(viewHolder1);
            } else if (type == TYPE_NORMAL){
                view = LayoutInflater.from(context).inflate(R.layout.item_recommend, null);
                viewHolder = new RecommendViewHolder(view);
                view.setTag(viewHolder);
            }

        } else {
            if (type == TYPE_THREE_IMAGE) {
                viewHolder1 = (RecommendViewHolder1) view.getTag();
            } else if (type == TYPE_NORMAL){
                viewHolder = (RecommendViewHolder) view.getTag();
            }
        }

        if (type == TYPE_THREE_IMAGE){

            String url = bean.getResult().getNewslist().get(i).getIndexdetail();
            String[] args = url.split("㊣");
            String[] args1 = args[2].split(",");
            String one = args1[0];
            String two = args1[1];
            String three = args1[2];

            Picasso.with(context).load(one).into(viewHolder1.imgIvImageOne);
            Picasso.with(context).load(two).into(viewHolder1.imgIvImageTwo);
            Picasso.with(context).load(three).into(viewHolder1.imgIvImageThree);

            viewHolder1.imgTvTitle.setText(bean.getResult().getNewslist().get(i).getTitle());
            viewHolder1.imgTvComment.setText(bean.getResult().getNewslist().get(i).getReplycount() + "图片");
            viewHolder1.imgTvDate.setText(bean.getResult().getNewslist().get(i).getTime());

        } else if (type == TYPE_NORMAL){
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

    private class RecommendViewHolder1 {

        private final ImageView imgIvImageOne;
        private final ImageView imgIvImageTwo;
        private final ImageView imgIvImageThree;
        private final TextView imgTvDate;
        private final TextView imgTvComment;
        private final TextView imgTvTitle;

        public RecommendViewHolder1(View view) {

            imgIvImageOne = (ImageView) view.findViewById(R.id.item_recommend_img_image_one);
            imgIvImageTwo = (ImageView) view.findViewById(R.id.item_recommend_img_image_two);
            imgIvImageThree = (ImageView) view.findViewById(R.id.item_recommend_img_image_three);
            imgTvDate = (TextView) view.findViewById(R.id.item_recommend_img_date);
            imgTvComment = (TextView) view.findViewById(R.id.item_recommend_img_comment);
            imgTvTitle = (TextView) view.findViewById(R.id.item_recommend_img_title);
        }
    }
}
