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
     * data : {"recommentlist":[{"repertorytotal":100,"productarea":"湖北","goodsid":1,"price":"50-100","icon":"http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg","salesvolume":500,"gname":"云雾绿茶","brand":"英山云雾"},{"repertorytotal":100,"productarea":"湖北","goodsid":7,"price":"50-100","icon":"http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg","salesvolume":500,"gname":"云雾毛尖","brand":"英山云雾"},{"repertorytotal":100,"productarea":"湖北","goodsid":8,"price":"50-100","icon":"http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg","salesvolume":500,"gname":"云雾红茶","brand":"英山云雾"}],"themelist":[{"themename":"打折","themeid":3,"pic":"https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg","type":"1"},{"themename":"聚优美","themeid":4,"pic":"https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg","type":"1"},{"themename":"亲友推荐","themeid":5,"pic":"https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg","type":"1"},{"themename":"唯品会","themeid":6,"pic":"https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg","type":"1"}]}
     */
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NY_RecommentModel{" +
                "data=" + data +
                '}';
    }

    public  class DataBean {
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "recommentlist=" + recommentlist +
                    ", themelist=" + themelist +
                    '}';
        }

        public  class RecommentlistBean {
            /**
             * repertorytotal : 100
             * productarea : 湖北
             * goodsid : 1
             * price : 50-100
             * icon : http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg
             * salesvolume : 500
             * gname : 云雾绿茶
             * brand : 英山云雾
             */

            private int repertorytotal;
            private String productarea;
            private int goodsid;
            private String price;
            private String icon;
            private int salesvolume;
            private String gname;
            private String brand;
            private String isself;

            public String getIsself() {
                return isself;
            }

            public void setIsself(String isself) {
                this.isself = isself;
            }

            public int getRepertorytotal() {
                return repertorytotal;
            }

            public void setRepertorytotal(int repertorytotal) {
                this.repertorytotal = repertorytotal;
            }

            public String getProductarea() {
                return productarea;
            }

            public void setProductarea(String productarea) {
                this.productarea = productarea;
            }

            public int getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(int goodsid) {
                this.goodsid = goodsid;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getSalesvolume() {
                return salesvolume;
            }

            public void setSalesvolume(int salesvolume) {
                this.salesvolume = salesvolume;
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

            @Override
            public String toString() {
                return "RecommentlistBean{" +
                        "repertorytotal=" + repertorytotal +
                        ", productarea='" + productarea + '\'' +
                        ", goodsid=" + goodsid +
                        ", price='" + price + '\'' +
                        ", icon='" + icon + '\'' +
                        ", salesvolume=" + salesvolume +
                        ", gname='" + gname + '\'' +
                        ", brand='" + brand + '\'' +
                        '}';
            }
        }

        public  class ThemelistBean {
            /**
             * themename : 打折
             * themeid : 3
             * pic : https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg
             * type : 1
             */
            private String themename;
            private int themeid;
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
                return "ThemelistBean{" +
                        "themename='" + themename + '\'' +
                        ", themeid=" + themeid +
                        ", pic='" + pic + '\'' +
                        ", type='" + type + '\'' +
                        '}';
            }
        }
    }
}
