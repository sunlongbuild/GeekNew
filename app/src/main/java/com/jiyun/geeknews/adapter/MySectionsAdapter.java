package com.jiyun.geeknews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jiyun.geeknews.R;
import com.jiyun.geeknews.bean.SectionsBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by $sl on 2019/4/17 20:34.
 */
public class MySectionsAdapter extends RecyclerView.Adapter<MySectionsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SectionsBean.DataBean> list;

    public MySectionsAdapter(Context context, ArrayList<SectionsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sections, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final SectionsBean.DataBean bean = list.get(i);
        //viewHolder.txt1.setText(bean.getDescription());
        viewHolder.txt2.setText(bean.getName());

        RoundedCorners roundedCorners = new RoundedCorners(30);
        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners);
        Glide.with(context).load(bean.getThumbnail()).apply(options).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.txt2)
        TextView txt2;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ButterKnife.bind(this, itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
