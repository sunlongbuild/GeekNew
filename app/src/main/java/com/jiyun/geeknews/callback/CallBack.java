package com.jiyun.geeknews.callback;

/**
 * Created by $sl on 2019/4/17 15:51.
 */
public interface CallBack<K> {
    void onSuccess(K k);
    void onFailed(String v);
}
