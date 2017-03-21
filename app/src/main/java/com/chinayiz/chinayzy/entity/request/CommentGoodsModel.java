package com.chinayiz.chinayzy.entity.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/3/19.
 *  评论商品
 */

public class CommentGoodsModel implements Parcelable {

    private String orderid;
    private String pic;
    private String pice;
    private String gPic;
    private String goodsName;
    private String goodsCount;
    private String sName;
    private String isanonymity;
    private String descpoint;
    private String commentscontent;
    private String orderdetailid;
    public String getOrderid() {
        return orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }


    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }

    public String getgPic() {
        return gPic;
    }

    public void setgPic(String gPic) {
        this.gPic = gPic;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIsanonymity() {
        return isanonymity;
    }

    public void setIsanonymity(String isanonymity) {
        this.isanonymity = isanonymity;
    }

    public String getDescpoint() {
        return descpoint;
    }

    public void setDescpoint(String descpoint) {
        this.descpoint = descpoint;
    }

    public String getCommentscontent() {
        return commentscontent;
    }

    public void setCommentscontent(String commentscontent) {
        this.commentscontent = commentscontent;
    }

    public String getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(String orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderid);
        dest.writeString(this.pic);
        dest.writeString(this.pice);
        dest.writeString(this.gPic);
        dest.writeString(this.goodsName);
        dest.writeString(this.goodsCount);
        dest.writeString(this.sName);
        dest.writeString(this.isanonymity);
        dest.writeString(this.descpoint);
        dest.writeString(this.commentscontent);
        dest.writeString(this.orderdetailid);
    }

    public CommentGoodsModel() {
    }

    protected CommentGoodsModel(Parcel in) {
        this.orderid = in.readString();
        this.pic = in.readString();
        this.pice = in.readString();
        this.gPic = in.readString();
        this.goodsName = in.readString();
        this.goodsCount = in.readString();
        this.sName = in.readString();
        this.isanonymity = in.readString();
        this.descpoint = in.readString();
        this.commentscontent = in.readString();
        this.orderdetailid = in.readString();
    }

    public static final Parcelable.Creator<CommentGoodsModel> CREATOR = new Parcelable.Creator<CommentGoodsModel>() {
        @Override
        public CommentGoodsModel createFromParcel(Parcel source) {
            return new CommentGoodsModel(source);
        }

        @Override
        public CommentGoodsModel[] newArray(int size) {
            return new CommentGoodsModel[size];
        }
    };

    @Override
    public String toString() {
        return "CommentGoodsModel{" +
                "orderid='" + orderid + '\'' +
                ", pic='" + pic + '\'' +
                ", pice='" + pice + '\'' +
                ", gPic='" + gPic + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount='" + goodsCount + '\'' +
                ", sName='" + sName + '\'' +
                ", isanonymity='" + isanonymity + '\'' +
                ", descpoint='" + descpoint + '\'' +
                ", commentscontent='" + commentscontent + '\'' +
                ", orderdetailid='" + orderdetailid + '\'' +
                '}';
    }
}
