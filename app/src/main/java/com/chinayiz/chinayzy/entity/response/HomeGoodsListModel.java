package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 16:39
 * Class HomeGoodsListModel
 */

public class HomeGoodsListModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * icon : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0427/287581c1-7497-4225-9e62-9b60ac6cc5b5.jpg
         * price : 468
         * updatetime : 2017-04-27 19:23:38
         * goodsid : 147
         * isself : 1
         * gname : EDGER 2017新款女士时尚休闲鞋  潮鞋春夏款      原价1250元 现价468元
         * brand : 益众
         * goodsstandardid : 245
         */

        private String icon;
        private String price;
        private String updatetime;
        private String goodsid;
        private String isself;
        private String gname;
        private String brand;
        private String goodsstandardid;

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

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getIsself() {
            return isself;
        }

        public void setIsself(String isself) {
            this.isself = isself;
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

        public String getGoodsstandardid() {
            return goodsstandardid;
        }

        public void setGoodsstandardid(String goodsstandardid) {
            this.goodsstandardid = goodsstandardid;
        }
    }
}
