package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/15 14:05
 * Class GoodsGroupModel 商品套餐
 */
public class GoodsGroupModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoodsGroupModel{" +
                "data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * price : 100
         * isdefault : 1
         * repertory : 11
         * standerpic : null
         * goodsstandardid : 1
         * standardvalue : 2只装
         * standardname : 2只装
         */

        private int price;
        private String isdefault;
        private int repertory;
        private Object standerpic;
        private int goodsstandardid;
        private String standardvalue;
        private String standardname;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
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

        public Object getStanderpic() {
            return standerpic;
        }

        public void setStanderpic(Object standerpic) {
            this.standerpic = standerpic;
        }

        public int getGoodsstandardid() {
            return goodsstandardid;
        }

        public void setGoodsstandardid(int goodsstandardid) {
            this.goodsstandardid = goodsstandardid;
        }

        public String getStandardvalue() {
            return standardvalue;
        }

        public void setStandardvalue(String standardvalue) {
            this.standardvalue = standardvalue;
        }

        public String getStandardname() {
            return standardname;
        }

        public void setStandardname(String standardname) {
            this.standardname = standardname;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "goodsstandardid=" + goodsstandardid +
                    ", price=" + price +
                    ", isdefault='" + isdefault + '\'' +
                    ", repertory=" + repertory +
                    ", standerpic=" + standerpic +
                    ", standardvalue='" + standardvalue + '\'' +
                    ", standardname='" + standardname + '\'' +
                    '}';
        }
    }
}
