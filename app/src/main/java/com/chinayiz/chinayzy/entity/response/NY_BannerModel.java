package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 9:32
 * Class NY_BannerResponseResult  首页Banner图
 */

public class NY_BannerModel extends BaseResponseModel {
    private List<Data> data ;
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public class Data {
        private int showtime;

        private String detaillink;

        private int aid;

        private String type;

        private String showlink;

        public void setShowtime(int showtime){
            this.showtime = showtime;
        }
        public int getShowtime(){
            return this.showtime;
        }
        public void setDetaillink(String detaillink){
            this.detaillink = detaillink;
        }
        public String getDetaillink(){
            return this.detaillink;
        }
        public void setAid(int aid){
            this.aid = aid;
        }
        public int getAid(){
            return this.aid;
        }
        public void setType(String type){
            this.type = type;
        }
        public String getType(){
            return this.type;
        }
        public void setShowlink(String showlink){
            this.showlink = showlink;
        }
        public String getShowlink(){
            return this.showlink;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "showtime=" + showtime +
                    ", detaillink='" + detaillink + '\'' +
                    ", aid=" + aid +
                    ", type='" + type + '\'' +
                    ", showlink='" + showlink + '\'' +
                    '}';
        }
    }
    @Override
    public String toString() {
        return "NY_BannerResponseResult{" +
                "data=" + data +
                '}';
    }
}
