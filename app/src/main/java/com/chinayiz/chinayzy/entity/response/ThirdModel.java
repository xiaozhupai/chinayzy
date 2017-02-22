package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * 第三方登录
 * Created by Administrator on 2017/2/15. 
 */

public class ThirdModel extends BaseResponseModel {

    /**
     * data : {"sex":null,"thirdid":"123456","nickname":null,"logintime":"2017-02-10 11:22:23","registertime":"2017-02-10 11:22:23","imei":null,"userid":10,"pid":"","logintype":"1","pic":null}
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
         * sex : null
         * thirdid : 123456
         * nickname : null
         * logintime : 2017-02-10 11:22:23
         * registertime : 2017-02-10 11:22:23
         * imei : null
         * userid : 10
         * pid :
         * logintype : 1
         * pic : null
         */

        private String sex;
        private String thirdid;
        private String nickname;
        private String logintime;
        private String registertime;
        private String imei;
        private int userid;
        private String pid;
        private String logintype;
        private String pic;

        public Object getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getThirdid() {
            return thirdid;
        }

        public void setThirdid(String thirdid) {
            this.thirdid = thirdid;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getLogintime() {
            return logintime;
        }

        public void setLogintime(String logintime) {
            this.logintime = logintime;
        }

        public String getRegistertime() {
            return registertime;
        }

        public void setRegistertime(String registertime) {
            this.registertime = registertime;
        }

        public Object getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getLogintype() {
            return logintype;
        }

        public void setLogintype(String logintype) {
            this.logintype = logintype;
        }

        public Object getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
