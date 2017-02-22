package com.chinayiz.chinayzy.entity.model;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/7 14:18
 * Class ResponseModel
 */
public class ResponseModel extends BaseResponseModel {

    /**
     * data : {}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public  class DataBean {

        @Override
        public String toString() {
            return "ResponseModel{" +
                    "data=" + data +
                    '}';
        }
    }
}
