package com.jiyun.geeknews.bean;

import java.io.Serializable;

/**
 * Created by $sl on 2019/4/18 16:44.
 */
public class GoldTitlesBean implements Serializable{
    public String title;
    public boolean isChecked;

    public GoldTitlesBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "GoldTitlesBean{" +
                "title='" + title + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
