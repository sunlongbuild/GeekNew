package com.jiyun.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.bean.GoldTitlesBean;
import com.jiyun.geeknews.weidget.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by $sl on 2019/4/18 18:45.
 */
public class MyGoldShowAdapter extends RecyclerView.Adapter<MyGoldShowAdapter.ViewHolder> implements TouchCallBack {
    private Context context;
    private ArrayList<GoldTitlesBean> list;

    public MyGoldShowAdapter(Context context, ArrayList<GoldTitlesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final GoldTitlesBean goldTitlesBean = list.get(i);
        viewHolder.txt.setText(goldTitlesBean.title);
        viewHolder.sc.setChecked(goldTitlesBean.isChecked);
        viewHolder.sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goldTitlesBean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换集合中两个数据的位置
        Collections.swap(list, fromPosition, toPosition);
        //刷新界面,局部刷新,索引会混乱
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        //删除数据
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt)
        TextView txt;
        @BindView(R.id.sc)
        SwitchCompat sc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
