package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * 红包专场购物车列表
 * Created by Administrator on 2017/8/1.
 */

public class ShowRedPacketCarmodel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ShoplistBean> shoplist;

        public List<ShoplistBean> getShoplist() {
            return shoplist;
        }

        public void setShoplist(List<ShoplistBean> shoplist) {
            this.shoplist = shoplist;
        }

        public static class ShoplistBean {
            /**
             * updatetime : 2017-08-01 10:41:01
             * salesvolume : 195
             * gname : 益众之天心 2017年明前一级富硒茶富硒贡芽   400g/盒
             * sname : null
             * shopid : null
             * grade : null
             * shopmessage : null
             * carid : 540
             * productarea : 湖北恩施
             * isputaway : 1
             * icon : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0428/60158913-9de7-4c08-bc41-2cbb19dd6580.jpg
             * standerpic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0421/c7dcc638-b6ec-415e-b39a-da4966a423be.jpg
             * isself : null
             * goodsstandardid : 255
             * pic : null
             * cost : 1688
             * standardname : 茶富硒贡芽
             * unit : g/盒
             * num : 4
             * price : 1350
             * repertory : 99
             * isselect : 0
             * goodsid : 152
             * brand : 益众
             * standardvalue : 400g
             */

            private String updatetime;
            private int salesvolume;
            private String gname;
            private Object sname;
            private Object shopid;
            private Object grade;
            private Object shopmessage;
            private int carid;
            private String productarea;
            private String isputaway;
            private String icon;
            private String standerpic;
            private Object isself;
            private int goodsstandardid;
            private Object pic;
            private int cost;
            private String standardname;
            private String unit;
            private int num;
            private int price;
            private int repertory;
            private int isselect;
            private int goodsid;
            private String brand;
            private String standardvalue;

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public int getSalesvolume() {
                return salesvolume;
            }

            public void setSalesvolume(int salesvolume) {
                this.salesvolume = salesvolume;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public Object getSname() {
                return sname;
            }

            public void setSname(Object sname) {
                this.sname = sname;
            }

            public Object getShopid() {
                return shopid;
            }

            public void setShopid(Object shopid) {
                this.shopid = shopid;
            }

            public Object getGrade() {
                return grade;
            }

            public void setGrade(Object grade) {
                this.grade = grade;
            }

            public Object getShopmessage() {
                return shopmessage;
            }

            public void setShopmessage(Object shopmessage) {
                this.shopmessage = shopmessage;
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

            public String getIsputaway() {
                return isputaway;
            }

            public void setIsputaway(String isputaway) {
                this.isputaway = isputaway;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getStanderpic() {
                return standerpic;
            }

            public void setStanderpic(String standerpic) {
                this.standerpic = standerpic;
            }

            public Object getIsself() {
                return isself;
            }

            public void setIsself(Object isself) {
                this.isself = isself;
            }

            public int getGoodsstandardid() {
                return goodsstandardid;
            }

            public void setGoodsstandardid(int goodsstandardid) {
                this.goodsstandardid = goodsstandardid;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public int getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getRepertory() {
                return repertory;
            }

            public void setRepertory(int repertory) {
                this.repertory = repertory;
            }

            public int getIsselect() {
                return isselect;
            }

            public void setIsselect(int isselect) {
                this.isselect = isselect;
            }

            public int getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(int goodsid) {
                this.goodsid = goodsid;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getStandardvalue() {
                return standardvalue;
            }

            public void setStandardvalue(String standardvalue) {
                this.standardvalue = standardvalue;
            }
        }
    }
}
