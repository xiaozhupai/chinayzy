package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/2/22.  个人中心用户信息
 */

public class PersonalModel extends BaseResponseModel {

    /**
     * data : {"sex":0,"waittakecount":0,"waitpaycount":1,"waitdelivercount":1,"aftercount":0,"nickname":"ccc","pic":"http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100","ismember":"1","pid":"1234567"}
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
         * sex : 0
         * waittakecount : 0
         * waitpaycount : 1
         * waitdelivercount : 1
         * aftercount : 0
         * nickname : ccc
         * pic : http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100
         * ismember : 1
         * pid : 1234567
         */

        private int sex;
        private int waittakecount;
        private int waitpaycount;
        private int waitdelivercount;
        private int aftercount;
        private String nickname;
        private String pic;
        private String ismember;
        private String pid;

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getWaittakecount() {
            return waittakecount;
        }

        public void setWaittakecount(int waittakecount) {
            this.waittakecount = waittakecount;
        }

        public int getWaitpaycount() {
            return waitpaycount;
        }

        public void setWaitpaycount(int waitpaycount) {
            this.waitpaycount = waitpaycount;
        }

        public int getWaitdelivercount() {
            return waitdelivercount;
        }

        public void setWaitdelivercount(int waitdelivercount) {
            this.waitdelivercount = waitdelivercount;
        }

        public int getAftercount() {
            return aftercount;
        }

        public void setAftercount(int aftercount) {
            this.aftercount = aftercount;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIsmember() {
            return ismember;
        }

        public void setIsmember(String ismember) {
            this.ismember = ismember;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
    }
}
