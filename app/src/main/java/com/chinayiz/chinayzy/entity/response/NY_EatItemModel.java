package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 11:32
 * Class NY_LoveEatResponeResult 生态农业爱吃版块
 */

public class NY_EatItemModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NY_EatItemModel{" +
                "data=" + data +
                '}';
    }

    public  class DataBean {
        /**
         * icon : http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg
         * price : 50-100
         * goodsid : 1
         * salesvolume : 500
         * gname : 云雾绿茶
         * brand : 英山云雾
         * repertorytotal : 100
         * productarea : 湖北
         */

        private String icon;
        private String price;
        private int goodsid;
        private int salesvolume;
        private String gname;
        private String brand;
        private int repertorytotal;
        private String productarea;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getRepertorytotal() {
            return repertorytotal;
        }

        public void setRepertorytotal(int repertorytotal) {
            this.repertorytotal = repertorytotal;
        }

        public String getProductarea() {
            return productarea;
        }

        public void setProductarea(String productarea) {
            this.productarea = productarea;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "icon='" + icon + '\'' +
                    ", price='" + price + '\'' +
                    ", goodsid=" + goodsid +
                    ", salesvolume=" + salesvolume +
                    ", gname='" + gname + '\'' +
                    ", brand='" + brand + '\'' +
                    ", repertorytotal=" + repertorytotal +
                    ", productarea='" + productarea + '\'' +
                    '}';
        }
    }
}
