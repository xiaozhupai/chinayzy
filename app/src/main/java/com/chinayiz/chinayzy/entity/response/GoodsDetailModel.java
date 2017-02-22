package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/15 10:23
 * Class GoodsDetailModel 商品详情相关信息
 */
public class GoodsDetailModel extends BaseResponseModel {

    /**
     * data : {"servicepoint":9.7,"providerid":1,"goodsavgpoint":9,"commentlist":[{"isanonymity":"1","createtime":"2017-02-15 19:09:23","servicepoint":1,"commentscontent":"买来下火锅，好久没尝到这么鲜美的食物了","nickname":"bbb","cpic":"2.jpg,1.jpg","descpoint":3,"pic":"http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100","cid":3,"deliverypoint":1}],"gpic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978891802&di=0503356ebe895df517add71013824e36&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F94%2F51%2F89Y58PICVMa_1024.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978942869&di=fdd60dc54e6cc27caffc8a82853048b3&imgtype=0&src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F04%2F15%2F146070121400860530.JPEG,http://img.redocn.com/201407/17/1332599_14055589835alX.jpg","salesvolume":500,"gname":"云雾绿茶","itemcode":"001001","sname":"皇家国际西餐厅","commentnum":1,"shopid":1,"repertorytotal":100,"productarea":"湖北","collectnum":10,"icon":"http://img.redocn.com/201407/17/1332599_14055589835alX.jpg","sgrade":5,"carriage":10,"isfeature":"1","isself":"1","goodsstandardid":1,"pid":"222222","spic":"http://p2.so.qhmsg.com/bdr/326__/t01f1d8fdf87651e72c.jpg","standardname":"2只装","iscollect":0,"unit":"g","logisticspoint":3,"rfid":"","price":"100.25-150.25","goodsid":1,"goodsnum":8,"service":"真实货源,质量鉴定,","brand":"英山云雾","goodsdesc":"好吃"}
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
         * servicepoint : 9.7
         * providerid : 1
         * goodsavgpoint : 9
         * commentlist : [{"isanonymity":"1","createtime":"2017-02-15 19:09:23","servicepoint":1,"commentscontent":"买来下火锅，好久没尝到这么鲜美的食物了","nickname":"bbb","cpic":"2.jpg,1.jpg","descpoint":3,"pic":"http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100","cid":3,"deliverypoint":1}]
         * gpic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978891802&di=0503356ebe895df517add71013824e36&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F94%2F51%2F89Y58PICVMa_1024.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978942869&di=fdd60dc54e6cc27caffc8a82853048b3&imgtype=0&src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F04%2F15%2F146070121400860530.JPEG,http://img.redocn.com/201407/17/1332599_14055589835alX.jpg
         * salesvolume : 500
         * gname : 云雾绿茶
         * itemcode : 001001
         * sname : 皇家国际西餐厅
         * commentnum : 1
         * shopid : 1
         * repertorytotal : 100
         * productarea : 湖北
         * collectnum : 10
         * icon : http://img.redocn.com/201407/17/1332599_14055589835alX.jpg
         * sgrade : 5
         * carriage : 10
         * isfeature : 1
         * isself : 1
         * goodsstandardid : 1
         * pid : 222222
         * spic : http://p2.so.qhmsg.com/bdr/326__/t01f1d8fdf87651e72c.jpg
         * standardname : 2只装
         * iscollect : 0
         * unit : g
         * logisticspoint : 3
         * rfid :
         * price : 100.25-150.25
         * goodsid : 1
         * goodsnum : 8
         * service : 真实货源,质量鉴定,
         * brand : 英山云雾
         * goodsdesc : 好吃
         */

        private double servicepoint;
        private int providerid;
        private int goodsavgpoint;
        private String gpic;
        private int salesvolume;
        private String gname;
        private String itemcode;
        private String sname;
        private int commentnum;
        private int shopid;
        private int repertorytotal;
        private String productarea;
        private int collectnum;
        private String icon;
        private int sgrade;
        private int carriage;
        private String isfeature;
        private String isself;
        private int goodsstandardid;
        private String pid;
        private String spic;
        private String standardname;
        private int iscollect;
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

        public String getGpic() {
            return gpic;
        }

        public void setGpic(String gpic) {
            this.gpic = gpic;
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

        public String getItemcode() {
            return itemcode;
        }

        public void setItemcode(String itemcode) {
            this.itemcode = itemcode;
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

        public int getCarriage() {
            return carriage;
        }

        public void setCarriage(int carriage) {
            this.carriage = carriage;
        }

        public String getIsfeature() {
            return isfeature;
        }

        public void setIsfeature(String isfeature) {
            this.isfeature = isfeature;
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

        public int getIscollect() {
            return iscollect;
        }

        public void setIscollect(int iscollect) {
            this.iscollect = iscollect;
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
                    "brand='" + brand + '\'' +
                    ", servicepoint=" + servicepoint +
                    ", providerid=" + providerid +
                    ", goodsavgpoint=" + goodsavgpoint +
                    ", gpic='" + gpic + '\'' +
                    ", salesvolume=" + salesvolume +
                    ", gname='" + gname + '\'' +
                    ", itemcode='" + itemcode + '\'' +
                    ", sname='" + sname + '\'' +
                    ", commentnum=" + commentnum +
                    ", shopid=" + shopid +
                    ", repertorytotal=" + repertorytotal +
                    ", productarea='" + productarea + '\'' +
                    ", collectnum=" + collectnum +
                    ", icon='" + icon + '\'' +
                    ", sgrade=" + sgrade +
                    ", carriage=" + carriage +
                    ", isfeature='" + isfeature + '\'' +
                    ", isself='" + isself + '\'' +
                    ", goodsstandardid=" + goodsstandardid +
                    ", pid='" + pid + '\'' +
                    ", spic='" + spic + '\'' +
                    ", standardname='" + standardname + '\'' +
                    ", iscollect=" + iscollect +
                    ", unit='" + unit + '\'' +
                    ", logisticspoint=" + logisticspoint +
                    ", rfid='" + rfid + '\'' +
                    ", price='" + price + '\'' +
                    ", goodsid=" + goodsid +
                    ", goodsnum=" + goodsnum +
                    ", service='" + service + '\'' +
                    ", goodsdesc='" + goodsdesc + '\'' +
                    ", commentlist=" + commentlist +
                    '}';
        }

        public static class CommentlistBean {
            /**
             * isanonymity : 1
             * createtime : 2017-02-15 19:09:23
             * servicepoint : 1
             * commentscontent : 买来下火锅，好久没尝到这么鲜美的食物了
             * nickname : bbb
             * cpic : 2.jpg,1.jpg
             * descpoint : 3
             * pic : http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100
             * cid : 3
             * deliverypoint : 1
             */

            private String isanonymity;
            private String createtime;
            private int servicepoint;
            private String commentscontent;
            private String nickname;
            private String cpic;
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

            public int getServicepoint() {
                return servicepoint;
            }

            public void setServicepoint(int servicepoint) {
                this.servicepoint = servicepoint;
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

            public String getCpic() {
                return cpic;
            }

            public void setCpic(String cpic) {
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
                        "cid=" + cid +
                        ", isanonymity='" + isanonymity + '\'' +
                        ", createtime='" + createtime + '\'' +
                        ", servicepoint=" + servicepoint +
                        ", commentscontent='" + commentscontent + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", cpic='" + cpic + '\'' +
                        ", descpoint=" + descpoint +
                        ", pic='" + pic + '\'' +
                        ", deliverypoint=" + deliverypoint +
                        '}';
            }
        }
    }
}
