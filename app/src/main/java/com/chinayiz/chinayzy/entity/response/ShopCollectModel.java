package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ShopCollectModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shopid : 3
         * synthesizepoint : 4.0
         * pic : http://chinayiz.cn/v1/2017/0216/64aae6de-928e-46fe-8f7a-d9571fadab95.png
         * sname : 亿众自营店
         * collectnum : 12
         * scid : 46
         */

        private int shopid;
        private double synthesizepoint;
        private String pic;
        private String sname;
        private int collectnum;
        private int scid;

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public double getSynthesizepoint() {
            return synthesizepoint;
        }

        public void setSynthesizepoint(double synthesizepoint) {
            this.synthesizepoint = synthesizepoint;
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

        public int getCollectnum() {
            return collectnum;
        }

        public void setCollectnum(int collectnum) {
            this.collectnum = collectnum;
        }

        public int getScid() {
            return scid;
        }

        public void setScid(int scid) {
            this.scid = scid;
        }
    }
}
