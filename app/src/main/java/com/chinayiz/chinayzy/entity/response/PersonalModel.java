package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/2/22.  个人中心用户信息
 */

public class PersonalModel extends BaseResponseModel {

    /**
     * data : {"relationshipcount":0,"sex":"0","nickname":"王先生","waittakecount":0,"waitpaycount":1,"ismember":"1","waitdelivercount":0,"pid":"CNYZ-WANGJIANCHAO-55055","pic":null,"aftercount":0}
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
         * relationshipcount : 0
         * sex : 0
         * nickname : 王先生
         * waittakecount : 0
         * waitpaycount : 1
         * ismember : 1
         * waitdelivercount : 0
         * pid : CNYZ-WANGJIANCHAO-55055
         * pic : null
         * aftercount : 0
         */

        private int relationshipcount;
        private String sex;
        private String nickname;
        private int waittakecount;
        private int waitpaycount;
        private String ismember;
        private int waitdelivercount;
        private String pid;
        private String pic;
        private int aftercount;

        public int getRelationshipcount() {
            return relationshipcount;
        }

        public void setRelationshipcount(int relationshipcount) {
            this.relationshipcount = relationshipcount;
        }

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
