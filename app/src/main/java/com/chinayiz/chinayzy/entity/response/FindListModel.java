package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

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

    public static class DataBean {
        /**
         * content : 好吃的
         * picpath : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978891802&di=0503356ebe895df517add71013824e36&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F94%2F51%2F89Y58PICVMa_1024.jpg
         * title : 啊哈哈
         * nickname : bbb
         * pic : kkk
         * bid : 1
         * iscollect : 1
         * ispraise : 1
         */

        private String content;
        private String picpath;
        private String title;
        private String nickname;
        private String pic;
        private int bid;
        private String iscollect;
        private String ispraise;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public String getIscollect() {
            return iscollect;
        }

        public void setIscollect(String iscollect) {
            this.iscollect = iscollect;
        }

        public String getIspraise() {
            return ispraise;
        }

        public void setIspraise(String ispraise) {
            this.ispraise = ispraise;
        }
    }
}
