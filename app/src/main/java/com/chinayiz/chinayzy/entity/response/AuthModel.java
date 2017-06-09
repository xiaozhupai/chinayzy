package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/6/9.
 */

public class AuthModel extends BaseResponseModel {

    /**
     * data : {"sys_auth":"1"}
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
         * sys_auth : 1
         */

        private String sys_auth;

        public String getSys_auth() {
            return sys_auth;
        }

        public void setSys_auth(String sys_auth) {
            this.sys_auth = sys_auth;
        }
    }
}
