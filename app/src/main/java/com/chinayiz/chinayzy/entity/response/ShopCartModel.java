package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */

public class ShopCartModel extends BaseResponseModel {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public void addItem(ShoplistBean pItemName) {
            shoplist.add(pItemName);
        }

        /**
         *  获取Item内容
         *
         * @param pPosition
         * @return
         */
        public ShoplistBean getItem(int pPosition) {
            // Category排在第一位
            if (pPosition == 0) {
                return shoplist.get(0);
            } else {
                return shoplist.get(pPosition - 1);
            }
        }

        private List<ShoplistBean> shoplist;

        public List<ShoplistBean> getShoplist() {
            return shoplist;
        }

        public void setShoplist(List<ShoplistBean> shoplist) {
            this.shoplist = shoplist;
        }

        public static class ShoplistBean {
            /**
             * icon : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978891802&di=0503356ebe895df517add71013824e36&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F94%2F51%2F89Y58PICVMa_1024.jpg
             * salesvolume : 500
             * standerpic : null
             * gname : 云雾绿茶
             * goodsstandardid : 1
             * pic : www.ccc.com
             * sname : 皇家国际饭店西餐厅
             * standardname : 2只装
             * unit : g
             * num : 3
             * shopid : 1
             * price : 100.25
             * shopmessage : 1,皇家国际饭店西餐厅
             * brand : 英山云雾
             * grade : 5
             * carid : 2
             * productarea : 湖北
             * standardvalue : 2只装
             */

            private String icon;
            private int salesvolume;
            private Object standerpic;
            private String gname;
            private int goodsstandardid;
            private String pic;
            private String sname;
            private String standardname;
            private String unit;
            private int num;
            private int shopid;
            private double price;
            private String shopmessage;
            private String brand;
            private int grade;
            private int carid;
            private String productarea;
            private String standardvalue;
            private boolean isChecked;
            private boolean isHead;
            private boolean isHeadChecked;
            private int goodsid;
            private int repertory;

            public int getRepertory() {
                return repertory;
            }

            public void setRepertory(int repertory) {
                this.repertory = repertory;
            }



            public int getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(int goodsid) {
                this.goodsid = goodsid;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public boolean isHead() {
                return isHead;
            }

            public void setHead(boolean head) {
                isHead = head;
            }

            public boolean isHeadChecked() {
                return isHeadChecked;
            }

            public void setHeadChecked(boolean headChecked) {
                isHeadChecked = headChecked;
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

            public Object getStanderpic() {
                return standerpic;
            }

            public void setStanderpic(Object standerpic) {
                this.standerpic = standerpic;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public int getGoodsstandardid() {
                return goodsstandardid;
            }

            public void setGoodsstandardid(int goodsstandardid) {
                this.goodsstandardid = goodsstandardid;
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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getShopmessage() {
                return shopmessage;
            }

            public void setShopmessage(String shopmessage) {
                this.shopmessage = shopmessage;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
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
    }
}
