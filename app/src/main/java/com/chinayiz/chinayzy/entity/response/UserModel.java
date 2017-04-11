package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/1/17.
 */

public class UserModel extends BaseResponseModel {


    /**
     * data : {"politics":"","birthday":"2017-04","sex":"0","weight":"","height":"","usualplace":"","nickname":"dsds","educational":"","ismarriage":"","pic":null}
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
         * politics :
         * birthday : 2017-04
         * sex : 0
         * weight :
         * height :
         * usualplace :
         * nickname : dsds
         * educational :
         * ismarriage :
         * pic : null
         */

        private String politics;
        private String birthday;
        private String sex;
        private String weight;
        private String height;
        private String usualplace;
        private String nickname;
        private String educational;
        private String ismarriage;
        private String pic;

        public String getPolitics() {
            return politics;
        }

        public void setPolitics(String politics) {
            this.politics = politics;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getUsualplace() {
            return usualplace;
        }

        public void setUsualplace(String usualplace) {
            this.usualplace = usualplace;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getEducational() {
            return educational;
        }

        public void setEducational(String educational) {
            this.educational = educational;
        }

        public String getIsmarriage() {
            return ismarriage;
        }

        public void setIsmarriage(String ismarriage) {
            this.ismarriage = ismarriage;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
