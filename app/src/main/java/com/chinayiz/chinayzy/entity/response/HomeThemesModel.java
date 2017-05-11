package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/10 14:56
 * Class HomeThemesModel
 */

public class HomeThemesModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * showtime : 3
         * detaillink : 76
         * aid : 26
         * showlink : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0427/1f12d03c-9ca1-414b-9ba1-61261a1d6019.jpg
         */

        private String showtime;
        private String detaillink;
        private String aid;
        private String showlink;

        public String getShowtime() {
            return showtime;
        }

        public void setShowtime(String showtime) {
            this.showtime = showtime;
        }

        public String getDetaillink() {
            return detaillink;
        }

        public void setDetaillink(String detaillink) {
            this.detaillink = detaillink;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getShowlink() {
            return showlink;
        }

        public void setShowlink(String showlink) {
            this.showlink = showlink;
        }
    }
}
