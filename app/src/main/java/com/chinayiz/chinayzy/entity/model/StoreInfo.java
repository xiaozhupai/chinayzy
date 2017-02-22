package com.chinayiz.chinayzy.entity.model;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/6 10:41
 * Class StoreInfo
 */
public class StoreInfo {
    private String isself;
    private String isattention;
    private String pic;
    private String bigicon;
    private String sname;
    private String introduction;

    /**
     * @param isself       是否是自营店
     * @param isattention  是否关注
     * @param pic          店铺头像
     * @param bigicon      店铺背景图
     * @param sname        店铺名
     * @param introduction 店铺简介
     */
    public StoreInfo(String isself, String isattention, String pic, String bigicon, String sname, String introduction) {
        this.isself = isself;
        this.isattention = isattention;
        this.pic = pic;
        this.bigicon = bigicon;
        this.sname = sname;
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getBigicon() {
        return bigicon;
    }

    public void setBigicon(String bigicon) {
        this.bigicon = bigicon;
    }

    public String getIsattention() {
        return isattention;
    }

    public void setIsattention(String isattention) {
        this.isattention = isattention;
    }

    public String getIsself() {
        return isself;
    }

    public void setIsself(String isself) {
        this.isself = isself;
    }

    @Override
    public String toString() {
        return "StoreInfo{" +
                "isself='" + isself + '\'' +
                ", isattention='" + isattention + '\'' +
                ", pic='" + pic + '\'' +
                ", bigicon='" + bigicon + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }
}
