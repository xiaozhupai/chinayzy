package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7. 搜索结果
 */

public class SearchFarmModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * unit : g
         * icon : http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg
         * price : 50-100
         * goodsid : 1
         * gname : 云雾绿茶
         * brand : 英山云雾
         * productarea : 湖北
         */

        private String unit;
        private String icon;
        private String price;
        private int goodsid;
        private String gname;
        private String brand;
        private String productarea;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

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

        public String getProductarea() {
            return productarea;
        }

        public void setProductarea(String productarea) {
            this.productarea = productarea;
        }
    }
}
