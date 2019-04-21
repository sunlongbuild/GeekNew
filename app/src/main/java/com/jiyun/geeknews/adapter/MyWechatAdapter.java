package com.jiyun.geeknews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.geeknews.R;
import com.jiyun.geeknews.bean.WechatBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by $sl on 2019/4/19 11:19.
 */
public class MyWechatAdapter extends RecyclerView.Adapter<MyWechatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<WechatBean.NewslistBean> list;

    public MyWechatAdapter(Context context, ArrayList<WechatBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wechat, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final WechatBean.NewslistBean bean = list.get(i);
        viewHolder.title.setText(bean.getTitle());
        viewHolder.content.setText(bean.getDescription());
        viewHolder.time.setText(bean.getCtime());
        Glide.with(context).load(bean.getPicUrl()).into(viewHolder.img);
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (OnItemClickListener != null) {
                    OnItemClickListener.OnClickListener(bean);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ButterKnife.bind(this,itemView);
            ButterKnife.bind(this,itemView);
            ButterKnife.bind(this,itemView);

        }
    }

    private OnItemClickListener OnItemClickListener;

    public void setOnItemClickListener(MyWechatAdapter.OnItemClickListener onItemClickListener) {
        OnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnClickListener(WechatBean.NewslistBean listBean);
    }
}
