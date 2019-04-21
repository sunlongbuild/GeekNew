package com.jiyun.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.geeknews.R;
import com.jiyun.geeknews.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by $sl on 2019/4/17 19:05.
 */
public class MyDailyNewAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<DailyNewsBean.TopStoriesBean> topStoriesBeans;
    private ArrayList<DailyNewsBean.StoriesBean> storiesBeans;
    private  int TYPE_BANNER = 0;
    private  int TYPE_TIME = 1;
    private  int TYPE_ARTICLE = 2;
    private String date;

    public MyDailyNewAdapter(Context context, ArrayList<DailyNewsBean.TopStoriesBean> topStoriesBeans, ArrayList<DailyNewsBean.StoriesBean> storiesBeans) {
        this.context = context;
        this.topStoriesBeans = topStoriesBeans;
        this.storiesBeans = storiesBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (i == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.item_banner, null);
            return new BannerViewHolder(view);
        } else if (i == TYPE_TIME) {
            View view = inflater.inflate(R.layout.item_time, null);
            return new TimeViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_article, null);
            return new ArticleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof BannerViewHolder) {
            final BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
            bannerViewHolder.banner.setImages(topStoriesBeans);
            bannerViewHolder.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DailyNewsBean.TopStoriesBean topStoriesBean = (DailyNewsBean.TopStoriesBean) path;
                    Glide.with(context).load(topStoriesBean.getImage()).into(imageView);
                }
            }).start();
        } else if (viewHolder instanceof TimeViewHolder) {
            TimeViewHolder timeViewHolder = (TimeViewHolder) viewHolder;
            if (date!=null){
                timeViewHolder.time.setText(date);
            }else {
                timeViewHolder.time.setText("今日要闻");

            }
        } else {
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
            int newPosition = i - 1;
            if (topStoriesBeans.size() > 0) {
                newPosition -= 1;
            }
            DailyNewsBean.StoriesBean storiesBean = storiesBeans.get(newPosition);
            articleViewHolder.title.setText(storiesBean.getTitle());
            Glide.with(context).load(storiesBean.getImages().get(0)).into(articleViewHolder.img);
        }
    }

    @Override
    public int getItemCount() {
        if (topStoriesBeans.size() > 0) {
            return 1 + 1 + storiesBeans.size();
        } else {
            return 1 + storiesBeans.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (topStoriesBeans.size() > 0) {
            if (position == 0) {
                return TYPE_BANNER;
            } else if (position == 1) {
                return TYPE_TIME;
            } else {
                return TYPE_ARTICLE;
            }
        } else {
            if (position == 0) {
                return TYPE_TIME;
            } else {
                return TYPE_ARTICLE;
            }
        }
    }

    public void setData(DailyNewsBean data) {
        date = data.getDate();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TimeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;

        public TimeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
