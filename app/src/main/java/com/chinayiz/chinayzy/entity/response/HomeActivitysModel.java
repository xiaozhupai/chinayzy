package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/7/14 15:13
 * Class HomeActivitysModel
 */

public class HomeActivitysModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * crowdfid : 12
         * cost : 12
         * price : 12
         * icon : dsfdfdf
         */

        private String crowdfid;
        private String cost;
        private String price;
        private String icon;

        public String getCrowdfid() {
            return crowdfid;
        }

        public void setCrowdfid(String crowdfid) {
            this.crowdfid = crowdfid;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
