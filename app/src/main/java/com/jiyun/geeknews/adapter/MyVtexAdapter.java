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
import com.jiyun.geeknews.bean.VtexBean;

import java.util.ArrayList;

/**
 * Created by $sl on 2019/4/21 18:04.
 */
public class MyVtexAdapter extends RecyclerView.Adapter<MyVtexAdapter.ViewHolder> {
    private Context context;
    private ArrayList<VtexBean.ItemBean> list;

    public MyVtexAdapter(Context context, ArrayList<VtexBean.ItemBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyVtexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vtex, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyVtexAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.author.setText(list.get(i).getAuthor());
        Glide.with(context).load("https:" + list.get(i).getImg()).into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        private TextView author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
        }
    }
}
