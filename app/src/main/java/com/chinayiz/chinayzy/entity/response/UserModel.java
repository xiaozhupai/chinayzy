package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/1/17.
 */

public class UserModel extends BaseResponseModel {


    /**
     * data : {"truename":null,"sex":"0","idcard":null,"nickname":"ccc","weight":null,"pic":"http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100","tag":"嘿嘿,嘿嘿,嘿嘿,欧美,日系,韩系","email":null,"height":null}
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
         * truename : null
         * sex : 0
         * idcard : null
         * nickname : ccc
         * weight : null
         * pic : http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100
         * tag : 嘿嘿,嘿嘿,嘿嘿,欧美,日系,韩系
         * email : null
         * height : null
         */

        private String truename;
        private String sex;
        private String idcard;
        private String nickname;
        private String weight;
        private String pic;
        private String tag;
        private String email;
        private String height;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }
}
