package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/8 14:32
 * Class DealListModel 积分交易列表
 */
public class DealListModel extends BaseResponseModel {

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    private List<DataBean> data;

    public static class DataBean {
        /**
         * operatetime : null
         * name : 充值
         * type : 1
         *  point  : +50.00
         */
        private Object operatetime;
        private String name;
        private String type;
        private String point;

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public Object getOperatetime() {
            return operatetime;
        }

        public void setOperatetime(Object operatetime) {
            this.operatetime = operatetime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
