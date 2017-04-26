package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/21 10:04
 * Class NY_EatThemeModel 生态农业爱吃主题
 */
public class NY_EatThemeModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NY_EatThemeModel{" +
                "data=" + data +
                '}';
    }

    public  class DataBean {
        /**
         * themename : 爱吃
         * themeid : 45
         * pic : https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2915066539,3198546158&fm=23&gp=0.jpg
         * type : 3
         */

        private String themename;
        private int themeid;
        private String pic;
        private String type;
        private String detaillink;

        public String getDetaillink() {
            return detaillink;
        }
        public void setDetaillink(String detaillink) {
            this.detaillink = detaillink;
        }
        public String getThemename() {
            return themename;
        }

        public void setThemename(String themename) {
            this.themename = themename;
        }

        public int getThemeid() {
            return themeid;
        }

        public void setThemeid(int themeid) {
            this.themeid = themeid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "themename='" + themename + '\'' +
                    ", themeid=" + themeid +
                    ", pic='" + pic + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
