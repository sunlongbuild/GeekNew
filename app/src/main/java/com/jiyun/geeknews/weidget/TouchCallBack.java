package com.jiyun.geeknews.weidget;

/**
 * Created by $sl on 2019/4/19 10:28.
 */
public interface TouchCallBack {
    //交换条目位置
    void onItemMove(int fromPosition,int toPosition);
    //删除条目
    void onItemDelete(int position);
}
