package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/7/14.
 */

public class BasedataModel extends BaseResponseModel {

    /**
     * data : {"ismember":"1","isresearch":"1","sys_auth":"1"}
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
         * ismember : “1”
         * isresearch : 1
         * sys_auth : 1
         */

        private String ismember;
        private String isresearch;
        private String sys_auth;

        public String getIsmember() {
            return ismember;
        }

        public void setIsmember(String ismember) {
            this.ismember = ismember;
        }

        public String getIsresearch() {
            return isresearch;
        }

        public void setIsresearch(String isresearch) {
            this.isresearch = isresearch;
        }

        public String getSys_auth() {
            return sys_auth;
        }

        public void setSys_auth(String sys_auth) {
            this.sys_auth = sys_auth;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "ismember='" + ismember + '\'' +
                    ", isresearch='" + isresearch + '\'' +
                    ", sys_auth='" + sys_auth + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BasedataModel{" +
                "data=" + data +
                '}';
    }
}
