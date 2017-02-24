package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class GoodStandardModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * standardname : 2只装
         * standardvalue : 2只装
         * price : 150.25
         * goodsid : 8
         * isdefault : 1
         * repertory : 33
         * goodsstandardid : 6
         * standerpic :
         */

        private String standardname;
        private String standardvalue;
        private double price;
        private int goodsid;
        private String isdefault;
        private int repertory;
        private int goodsstandardid;
        private String standerpic;

        public String getStandardname() {
            return standardname;
        }

        public void setStandardname(String standardname) {
            this.standardname = standardname;
        }

        public String getStandardvalue() {
            return standardvalue;
        }

        public void setStandardvalue(String standardvalue) {
            this.standardvalue = standardvalue;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public String getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(String isdefault) {
            this.isdefault = isdefault;
        }

        public int getRepertory() {
            return repertory;
        }

        public void setRepertory(int repertory) {
            this.repertory = repertory;
        }

        public int getGoodsstandardid() {
            return goodsstandardid;
        }

        public void setGoodsstandardid(int goodsstandardid) {
            this.goodsstandardid = goodsstandardid;
        }

        public String getStanderpic() {
            return standerpic;
        }

        public void setStanderpic(String standerpic) {
            this.standerpic = standerpic;
        }
    }
}
