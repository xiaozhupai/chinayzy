package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/4/21.
 */

public class HyqyAndYhxyModel extends BaseResponseModel {

    /**
     * data : {"hyqy":"\u201c大大大\u201d","yhxy":"大大四大"}
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
         * hyqy : “大大大”
         * yhxy : 大大四大
         */

        private String hyqy;
        private String yhxy;

        public String getHyqy() {
            return hyqy;
        }

        public void setHyqy(String hyqy) {
            this.hyqy = hyqy;
        }

        public String getYhxy() {
            return yhxy;
        }

        public void setYhxy(String yhxy) {
            this.yhxy = yhxy;
        }
    }
}
