package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/2/22.  个人中心用户信息
 */

public class PersonalModel extends BaseResponseModel {

    /**
     * data : {"sex":"","nickname":"","waittakecount":0,"waitpaycount":0,"ismember":"1","waitdelivercount":0,"pid":"CNYZ-YUANKUNKUN-80316","pic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1%2F2017%2F04%2F20%2F03%2F30%2F59.png","aftercount":0}
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
         * sex :
         * nickname :
         * waittakecount : 0
         * waitpaycount : 0
         * ismember : 1
         * waitdelivercount : 0
         * pid : CNYZ-YUANKUNKUN-80316
         * pic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1%2F2017%2F04%2F20%2F03%2F30%2F59.png
         * aftercount : 0
         */

        private String sex;
        private String nickname;
        private int waittakecount;
        private int waitpaycount;
        private String ismember;
        private int waitdelivercount;
        private String pid;
        private String pic;
        private int aftercount;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getIsmember() {
            return ismember;
        }

        public void setIsmember(String ismember) {
            this.ismember = ismember;
        }

        public int getWaitdelivercount() {
            return waitdelivercount;
        }

        public void setWaitdelivercount(int waitdelivercount) {
            this.waitdelivercount = waitdelivercount;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getAftercount() {
            return aftercount;
        }

        public void setAftercount(int aftercount) {
            this.aftercount = aftercount;
        }
    }
}
