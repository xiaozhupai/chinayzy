package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/6/15.
 */

public class ActivityResultModel extends BaseResponseModel {

    /**
     * data : {"icon":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0427/c69d0c26-baf8-40e4-bddf-6cfe82c001c9.jpg","price":39.9,"status":"1","activitybalance":100,"gname":"EDGER 2017新款韩版女鞋高跟鞋女细跟单鞋   原价1250元 现价437元","crowdfid":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * icon : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0427/c69d0c26-baf8-40e4-bddf-6cfe82c001c9.jpg
         * price : 39.9
         * status : 1
         * activitybalance : 100
         * gname : EDGER 2017新款韩版女鞋高跟鞋女细跟单鞋   原价1250元 现价437元
         * crowdfid : 1
         */

        private String icon;
        private double price;
        private String status;
        private double activitybalance;
        private String gname;
        private int crowdfid;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getActivitybalance() {
            return activitybalance;
        }

        public void setActivitybalance(double activitybalance) {
            this.activitybalance = activitybalance;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public int getCrowdfid() {
            return crowdfid;
        }

        public void setCrowdfid(int crowdfid) {
            this.crowdfid = crowdfid;
        }
    }
}
