package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/4 9:12
 * Class GoodsModel 商品详细信息
 */
public class GoodsModel extends BaseResponseModel {

    /**
     * data : {"servicepoint":9.7,"providerid":1,"goodsavgpoint":9,"commentlist":[{"isanonymity":"1","createtime":"2017-01-17 17:24:01","commentscontent":"aaaa","nickname":"bbb","cpic":null,"descpoint":3,"pic":"kkk","cid":1,"deliverypoint":4}],"salesvolume":500,"gpic":"http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg","gname":"云雾绿茶","sname":"皇家国际饭店西餐厅","commentnum":2,"istrue":"1","shopid":1,"repertorytotal":100,"productarea":"湖北","isseven":"0","collectnum":9,"icon":"http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg","sgrade":5,"isfeature":"1","isquality":"1","isself":"1","goodsstandardid":1,"pid":"222222","spic":"www.ccc.com","standardname":"2只装","unit":"g","logisticspoint":3,"rfid":"","price":"100.00-150.00","goodsid":1,"goodsnum":8,"service":"真实货源,治疗鉴定,","brand":"英山云雾","goodsdesc":"好吃"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public  class DataBean {
        /**
         * servicepoint : 9.7
         * providerid : 1
         * goodsavgpoint : 9
         * commentlist : [{"isanonymity":"1","createtime":"2017-01-17 17:24:01","commentscontent":"aaaa","nickname":"bbb","cpic":null,"descpoint":3,"pic":"kkk","cid":1,"deliverypoint":4}]
         * salesvolume : 500
         * gpic : http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg
         * gname : 云雾绿茶
         * sname : 皇家国际饭店西餐厅
         * commentnum : 2
         * istrue : 1
         * shopid : 1
         * repertorytotal : 100
         * productarea : 湖北
         * isseven : 0
         * collectnum : 9
         * icon : http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg
         * sgrade : 5
         * isfeature : 1
         * isquality : 1
         * isself : 1
         * goodsstandardid : 1
         * pid : 222222
         * spic : www.ccc.com
         * standardname : 2只装
         * unit : g
         * logisticspoint : 3
         * rfid :
         * price : 100.00-150.00
         * goodsid : 1
         * goodsnum : 8
         * service : 真实货源,治疗鉴定,
         * brand : 英山云雾
         * goodsdesc : 好吃
         */

        private double servicepoint;
        private int providerid;
        private int goodsavgpoint;
        private int salesvolume;
        private String gpic;
        private String gname;
        private String sname;
        private int commentnum;
        private String istrue;
        private int shopid;
        private int repertorytotal;
        private String productarea;
        private String isseven;
        private int collectnum;
        private String icon;
        private int sgrade;
        private String isfeature;
        private String isquality;
        private String isself;
        private int goodsstandardid;
        private String pid;
        private String spic;
        private String standardname;
        private String unit;
        private int logisticspoint;
        private String rfid;
        private String price;
        private int goodsid;
        private int goodsnum;
        private String service;
        private String brand;
        private String goodsdesc;
        private List<CommentlistBean> commentlist;

        public double getServicepoint() {
            return servicepoint;
        }

        public void setServicepoint(double servicepoint) {
            this.servicepoint = servicepoint;
        }

        public int getProviderid() {
            return providerid;
        }

        public void setProviderid(int providerid) {
            this.providerid = providerid;
        }

        public int getGoodsavgpoint() {
            return goodsavgpoint;
        }

        public void setGoodsavgpoint(int goodsavgpoint) {
            this.goodsavgpoint = goodsavgpoint;
        }

        public int getSalesvolume() {
            return salesvolume;
        }

        public void setSalesvolume(int salesvolume) {
            this.salesvolume = salesvolume;
        }

        public String getGpic() {
            return gpic;
        }

        public void setGpic(String gpic) {
            this.gpic = gpic;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public String getIstrue() {
            return istrue;
        }

        public void setIstrue(String istrue) {
            this.istrue = istrue;
        }

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
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

        public String getIsseven() {
            return isseven;
        }

        public void setIsseven(String isseven) {
            this.isseven = isseven;
        }

        public int getCollectnum() {
            return collectnum;
        }

        public void setCollectnum(int collectnum) {
            this.collectnum = collectnum;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getSgrade() {
            return sgrade;
        }

        public void setSgrade(int sgrade) {
            this.sgrade = sgrade;
        }

        public String getIsfeature() {
            return isfeature;
        }

        public void setIsfeature(String isfeature) {
            this.isfeature = isfeature;
        }

        public String getIsquality() {
            return isquality;
        }

        public void setIsquality(String isquality) {
            this.isquality = isquality;
        }

        public String getIsself() {
            return isself;
        }

        public void setIsself(String isself) {
            this.isself = isself;
        }

        public int getGoodsstandardid() {
            return goodsstandardid;
        }

        public void setGoodsstandardid(int goodsstandardid) {
            this.goodsstandardid = goodsstandardid;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getSpic() {
            return spic;
        }

        public void setSpic(String spic) {
            this.spic = spic;
        }

        public String getStandardname() {
            return standardname;
        }

        public void setStandardname(String standardname) {
            this.standardname = standardname;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getLogisticspoint() {
            return logisticspoint;
        }

        public void setLogisticspoint(int logisticspoint) {
            this.logisticspoint = logisticspoint;
        }

        public String getRfid() {
            return rfid;
        }

        public void setRfid(String rfid) {
            this.rfid = rfid;
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

        public int getGoodsnum() {
            return goodsnum;
        }

        public void setGoodsnum(int goodsnum) {
            this.goodsnum = goodsnum;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getGoodsdesc() {
            return goodsdesc;
        }

        public void setGoodsdesc(String goodsdesc) {
            this.goodsdesc = goodsdesc;
        }

        public List<CommentlistBean> getCommentlist() {
            return commentlist;
        }

        public void setCommentlist(List<CommentlistBean> commentlist) {
            this.commentlist = commentlist;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "servicepoint=" + servicepoint +
                    ", providerid=" + providerid +
                    ", goodsavgpoint=" + goodsavgpoint +
                    ", salesvolume=" + salesvolume +
                    ", gpic='" + gpic + '\'' +
                    ", gname='" + gname + '\'' +
                    ", sname='" + sname + '\'' +
                    ", commentnum=" + commentnum +
                    ", istrue='" + istrue + '\'' +
                    ", shopid=" + shopid +
                    ", repertorytotal=" + repertorytotal +
                    ", productarea='" + productarea + '\'' +
                    ", isseven='" + isseven + '\'' +
                    ", collectnum=" + collectnum +
                    ", icon='" + icon + '\'' +
                    ", sgrade=" + sgrade +
                    ", isfeature='" + isfeature + '\'' +
                    ", isquality='" + isquality + '\'' +
                    ", isself='" + isself + '\'' +
                    ", goodsstandardid=" + goodsstandardid +
                    ", pid='" + pid + '\'' +
                    ", spic='" + spic + '\'' +
                    ", standardname='" + standardname + '\'' +
                    ", unit='" + unit + '\'' +
                    ", logisticspoint=" + logisticspoint +
                    ", rfid='" + rfid + '\'' +
                    ", price='" + price + '\'' +
                    ", goodsid=" + goodsid +
                    ", goodsnum=" + goodsnum +
                    ", service='" + service + '\'' +
                    ", brand='" + brand + '\'' +
                    ", goodsdesc='" + goodsdesc + '\'' +
                    ", commentlist=" + commentlist +
                    '}';
        }

        public  class CommentlistBean {
            /**
             * isanonymity : 1
             * createtime : 2017-01-17 17:24:01
             * commentscontent : aaaa
             * nickname : bbb
             * cpic : null
             * descpoint : 3
             * pic : kkk
             * cid : 1
             * deliverypoint : 4
             */

            private String isanonymity;
            private String createtime;
            private String commentscontent;
            private String nickname;
            private Object cpic;
            private int descpoint;
            private String pic;
            private int cid;
            private int deliverypoint;

            public String getIsanonymity() {
                return isanonymity;
            }

            public void setIsanonymity(String isanonymity) {
                this.isanonymity = isanonymity;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getCommentscontent() {
                return commentscontent;
            }

            public void setCommentscontent(String commentscontent) {
                this.commentscontent = commentscontent;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Object getCpic() {
                return cpic;
            }

            public void setCpic(Object cpic) {
                this.cpic = cpic;
            }

            public int getDescpoint() {
                return descpoint;
            }

            public void setDescpoint(int descpoint) {
                this.descpoint = descpoint;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getDeliverypoint() {
                return deliverypoint;
            }

            public void setDeliverypoint(int deliverypoint) {
                this.deliverypoint = deliverypoint;
            }

            @Override
            public String toString() {
                return "CommentlistBean{" +
                        "isanonymity='" + isanonymity + '\'' +
                        ", createtime='" + createtime + '\'' +
                        ", commentscontent='" + commentscontent + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", cpic=" + cpic +
                        ", descpoint=" + descpoint +
                        ", pic='" + pic + '\'' +
                        ", cid=" + cid +
                        ", deliverypoint=" + deliverypoint +
                        '}';
            }
        }
    }
}
