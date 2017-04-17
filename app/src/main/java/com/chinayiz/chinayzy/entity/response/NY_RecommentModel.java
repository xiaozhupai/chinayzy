package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/18 9:44
 * Class NY_RecommentResponseResult 生态农业首页精选推荐版块
 */

public class NY_RecommentModel extends BaseResponseModel {

    /**
     * data : {"recommentlist":[{"icon":"http://chinayiz.cn/m1/2017/0330/4f4797af-0cc8-409d-ae90-399ebd27a9ee.jpg","price":"1125.00-2250.00","goodsid":71,"salesvolume":0,"isself":"1","gname":"富硒纯手工玉露","brand":"益众","productarea":"湖北恩施"},{"icon":"http://chinayiz.cn/m1/2017/0330/724e7bcf-9cbe-406a-b55e-a3058e7c2bcf.jpg","price":"165.00-325.00","goodsid":72,"salesvolume":0,"isself":"1","gname":"富硒蜂蜜","brand":"益众","productarea":"湖北恩施"},{"icon":"http://chinayiz.cn/m1/2017/0330/73011f81-fb84-4fb1-b60e-4949d9d444fd.jpg","price":"725.00-1450.00","goodsid":73,"salesvolume":0,"isself":"1","gname":"富硒贡芽","brand":"益众","productarea":"湖北恩施"}],"themelist":[{"themename":"推荐","themeid":3,"detaillink":"75","pic":"http://chinayiz.cn/v1/2017/0414/c450aa8f-c6df-4425-9ff8-227a234995cd.png","type":"1"},{"themename":"推荐","themeid":4,"detaillink":"73","pic":"http://chinayiz.cn/v1/2017/0414/1ed5a662-b972-47ba-88ab-e3f892362942.png","type":"1"},{"themename":"推荐","themeid":5,"detaillink":"76","pic":"http://chinayiz.cn/v1/2017/0414/db2e47e9-763a-4573-95b1-30395aede621.png","type":"1"},{"themename":"推荐","themeid":6,"detaillink":"74","pic":"http://chinayiz.cn/v1/2017/0414/a05ec361-48fe-4655-b47b-b277b02c253b.png","type":"1"},{"themename":"推荐","themeid":7,"detaillink":"72","pic":"http://chinayiz.cn/v1/2017/0414/337dc224-ab80-4766-a67c-bb4a4d9f27cd.png","type":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RecommentlistBean> recommentlist;
        private List<ThemelistBean> themelist;

        public List<RecommentlistBean> getRecommentlist() {
            return recommentlist;
        }

        public void setRecommentlist(List<RecommentlistBean> recommentlist) {
            this.recommentlist = recommentlist;
        }

        public List<ThemelistBean> getThemelist() {
            return themelist;
        }

        public void setThemelist(List<ThemelistBean> themelist) {
            this.themelist = themelist;
        }

        public static class RecommentlistBean {
            /**
             * icon : http://chinayiz.cn/m1/2017/0330/4f4797af-0cc8-409d-ae90-399ebd27a9ee.jpg
             * price : 1125.00-2250.00
             * goodsid : 71
             * salesvolume : 0
             * isself : 1
             * gname : 富硒纯手工玉露
             * brand : 益众
             * productarea : 湖北恩施
             */

            private String icon;
            private String price;
            private int goodsid;
            private int salesvolume;
            private String isself;
            private String gname;
            private String brand;
            private String productarea;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(int goodsid) {
                this.goodsid = goodsid;
            }

            public int getSalesvolume() {
                return salesvolume;
            }

            public void setSalesvolume(int salesvolume) {
                this.salesvolume = salesvolume;
            }

            public String getIsself() {
                return isself;
            }

            public void setIsself(String isself) {
                this.isself = isself;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getProductarea() {
                return productarea;
            }

            public void setProductarea(String productarea) {
                this.productarea = productarea;
            }
        }

        public static class ThemelistBean {
            /**
             * themename : 推荐
             * themeid : 3
             * detaillink : 75
             * pic : http://chinayiz.cn/v1/2017/0414/c450aa8f-c6df-4425-9ff8-227a234995cd.png
             * type : 1
             */

            private String themename;
            private int themeid;
            private String detaillink;
            private String pic;
            private String type;

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

            public String getDetaillink() {
                return detaillink;
            }

            public void setDetaillink(String detaillink) {
                this.detaillink = detaillink;
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
        }
    }
}
