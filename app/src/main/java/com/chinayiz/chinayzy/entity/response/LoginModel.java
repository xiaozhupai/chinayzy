package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/2/4.
 */

public class LoginModel extends BaseResponseModel {


    /**
     * data : {"userid":1,"sys_auth":"1","ismember":"1"}
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
         * userid : 1
         * sys_auth : 1
         * ismember : 1
         */

        private int userid;
        private String sys_auth;
        private String ismember;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getSys_auth() {
            return sys_auth;
        }

        public void setSys_auth(String sys_auth) {
            this.sys_auth = sys_auth;
        }

        public String getIsmember() {
            return ismember;
        }

        public void setIsmember(String ismember) {
            this.ismember = ismember;
        }
    }
}
