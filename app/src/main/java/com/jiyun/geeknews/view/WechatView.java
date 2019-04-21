package com.jiyun.geeknews.view;

import com.jiyun.geeknews.base.BaseMainView;
import com.jiyun.geeknews.bean.WechatBean;

/**
 * Created by $sl on 2019/4/3 16:14.
 */
public interface WechatView extends BaseMainView {
    void onSuccess(WechatBean wechatBean);

    void onFailed(String string);
}
