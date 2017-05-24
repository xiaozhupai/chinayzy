package com.chinayiz.chinayzy.entity.model;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/20 17:15
 * Class ShareVipModel   分享Vip数据
 */

public class ShareVipModel {
    private String theme;
    private String introduce;
    private String icon;
    private String url;

    public ShareVipModel(String theme, String introduce, String icon, String url) {
        this.theme = theme;
        this.introduce = introduce;
        this.icon = icon;
        this.url = url;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ShareVipModel{" +
                "theme='" + theme + '\'' +
                ", introduce='" + introduce + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
