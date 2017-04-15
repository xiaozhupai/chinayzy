package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 */

public class FindListModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * logo : http://chinayiz.cn/v1/2017/0407/415ab22a-6aa0-4739-9ada-c3d314bf6f0d.png
         * picpath : http://www.stny.cn/UploadFiles/2014-10/2/201410311516407812864.jpg
         * title : 有机农业对防治土壤荒漠化有哪些作用
         * synopsis : 有机农业结合了传统农业、创新思维和科学技术，有利于保护我们所共享的生存环境，也有利于促进包括人类在内的自然界所有成员间的公平与和谐共生
         * logowenzi : 中国亿众
         * bid : 1
         */

        private String logo;
        private String picpath;
        private String title;
        private String synopsis;
        private String logowenzi;
        private int bid;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPicpath() {
            return picpath;
        }

        public void setPicpath(String picpath) {
            this.picpath = picpath;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getLogowenzi() {
            return logowenzi;
        }

        public void setLogowenzi(String logowenzi) {
            this.logowenzi = logowenzi;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }
    }
}
