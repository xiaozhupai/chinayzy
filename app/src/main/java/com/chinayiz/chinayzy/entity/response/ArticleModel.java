package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/** 博文收藏
 * Created by Administrator on 2017/3/14.
 */

public class ArticleModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picpath : http://p9.pstatp.com/large/17ef0002154fb033db6a
         * title : 城镇化的可持续发展：如何大力发展生态农业？
         * isdianzan : false
         * synopsis : 生态农业肩负时代重任，从工业反哺农业到农业修复生态
         * blogid : 10
         * bcid : 28
         */

        private String picpath;
        private String title;
        private boolean isdianzan;
        private String synopsis;
        private int blogid;
        private int bcid;

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

        public boolean isIsdianzan() {
            return isdianzan;
        }

        public void setIsdianzan(boolean isdianzan) {
            this.isdianzan = isdianzan;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public int getBlogid() {
            return blogid;
        }

        public void setBlogid(int blogid) {
            this.blogid = blogid;
        }

        public int getBcid() {
            return bcid;
        }

        public void setBcid(int bcid) {
            this.bcid = bcid;
        }
    }
}
