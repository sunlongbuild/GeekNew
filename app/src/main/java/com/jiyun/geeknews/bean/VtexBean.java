package com.jiyun.geeknews.bean;

import android.text.style.TtsSpan;

import java.util.List;

/**
 * Created by $sl on 2019/4/21 14:53.
 */
public class VtexBean {
    private List<TabBean> tabBeans;
    private List<ItemBean> itemBeans;

    public static class TabBean{
        private String link;
        private String tab;

        public TabBean(String link, String tab) {
            this.link = link;
            this.tab = tab;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        @Override
        public String toString() {
            return "TabBean{" +
                    "link='" + link + '\'' +
                    ", tab='" + tab + '\'' +
                    '}';
        }
    }
    public static class ItemBean{
        private String img;
        private String title;
        private String author;
        private String lastDiscuss;
        private String secTab;

        public ItemBean(String img, String title, String author, String lastDiscuss, String secTab) {
            this.img = img;
            this.title = title;
            this.author = author;
            this.lastDiscuss = lastDiscuss;
            this.secTab = secTab;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getLastDiscuss() {
            return lastDiscuss;
        }

        public void setLastDiscuss(String lastDiscuss) {
            this.lastDiscuss = lastDiscuss;
        }

        public String getSecTab() {
            return secTab;
        }

        public void setSecTab(String secTab) {
            this.secTab = secTab;
        }

        @Override
        public String toString() {
            return "ItemBean{" +
                    "img='" + img + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", lastDiscuss='" + lastDiscuss + '\'' +
                    ", secTab='" + secTab + '\'' +
                    '}';
        }
    }

    public VtexBean(List<TabBean> tabBeans, List<ItemBean> itemBeans) {
        this.tabBeans = tabBeans;
        this.itemBeans = itemBeans;
    }

    public List<TabBean> getTabBeans() {
        return tabBeans;
    }

    public void setTabBeans(List<TabBean> tabBeans) {
        this.tabBeans = tabBeans;
    }

    public List<ItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<ItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }

    @Override
    public String toString() {
        return "VtexBean{" +
                "tabBeans=" + tabBeans +
                ", itemBeans=" + itemBeans +
                '}';
    }
}
