package com.chinayiz.chinayzy.entity.response;

/**
 * Created by Administrator on 2017/1/17.
 */

public class ShopCartModel {
    private String icon;  //商品小图标
    private int salesvolume;  //月销量
    private String standerpic;  //规格图片
    private String gname;   //商品名称
    private String pic;   //店铺logo
    private String sname;   //店铺名称
    private  String standardname;   //规格名称
    private String unit;   //单位
    private int  num;    //数量
    private int  shopid;   //店铺id
    private int price;    //价格
    private String shopmessage;   //
    private int grade;    //店铺等级
    private String brand;   //品牌
    private  int carid;    //购物车唯一id
    private String productarea;   //产地
    private String standardvalue;   //规格值
    private boolean isChecked;


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSalesvolume() {
        return salesvolume;
    }

    public void setSalesvolume(int salesvolume) {
        this.salesvolume = salesvolume;
    }

    public String getStanderpic() {
        return standerpic;
    }

    public void setStanderpic(String standerpic) {
        this.standerpic = standerpic;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
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

    public String getStandardname() {
        return standardname;
    }

    public void setStandardname(String standardname) {
        this.standardname = standardname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShopmessage() {
        return shopmessage;
    }

    public void setShopmessage(String shopmessage) {
        this.shopmessage = shopmessage;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getProductarea() {
        return productarea;
    }

    public void setProductarea(String productarea) {
        this.productarea = productarea;
    }

    public String getStandardvalue() {
        return standardvalue;
    }

    public void setStandardvalue(String standardvalue) {
        this.standardvalue = standardvalue;
    }
}
