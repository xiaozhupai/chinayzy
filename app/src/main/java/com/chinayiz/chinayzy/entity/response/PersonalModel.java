package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/2/22.  个人中心用户信息
 */

public class PersonalModel extends BaseResponseModel {

    /**
     * data : {"waitpaycount":1,"aftercount":0,"sex":"0","nickname":"ccc","pic":"http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100","waitdelivercount":1,"waittakecount":0}
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
         * waitpaycount : 1
         * aftercount : 0
         * sex : 0
         * nickname : ccc
         * pic : http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100
         * waitdelivercount : 1
         * waittakecount : 0
         */

        private int waitpaycount;
        private int aftercount;
        private String sex;
        private String nickname;
        private String pic;
        private int waitdelivercount;
        private int waittakecount;

        public int getWaitpaycount() {
            return waitpaycount;
        }

        public void setWaitpaycount(int waitpaycount) {
            this.waitpaycount = waitpaycount;
        }

        public int getAftercount() {
            return aftercount;
        }

        public void setAftercount(int aftercount) {
            this.aftercount = aftercount;
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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getWaitdelivercount() {
            return waitdelivercount;
        }

        public void setWaitdelivercount(int waitdelivercount) {
            this.waitdelivercount = waitdelivercount;
        }

        public int getWaittakecount() {
            return waittakecount;
        }

        public void setWaittakecount(int waittakecount) {
            this.waittakecount = waittakecount;
        }
    }
}
