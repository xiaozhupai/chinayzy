package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/13 11:52
 * Class NewGoodsDetailModel 新商品详情信息model
 */

public class NewGoodsDetailModel extends BaseResponseModel {

    /**
     * data : {"servicepoint":null,"providerid":3,"goodsavgpoint":null,"commentlist":[{"isanonymity":null,"createtime":"2017-04-13 11:41:19","servicepoint":null,"commentscontent":null,"nickname":"jackywang ","cpic":null,"descpoint":4,"pic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1%2F2017%2F04%2F01%2F13%2F54%2F22.png","cid":1,"deliverypoint":null},{"isanonymity":null,"createtime":"2017-04-13 11:41:21","servicepoint":null,"commentscontent":null,"nickname":"啦啦啦啦啦啦啦","cpic":null,"descpoint":3,"pic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1%2F2017%2F04%2F11%2F04%2F07%2F27.png","cid":2,"deliverypoint":null}],"salesvolume":0,"gpic":"http://chinayiz.cn/m1/2017/0328/42b18e4d-6df3-4b12-8c81-7c982d1e5ad9.jpg,http://chinayiz.cn/m1/2017/0328/6d8bc733-f433-4819-a02b-7f692da3acd9.jpg,http://chinayiz.cn/m1/2017/0328/c6491d4e-8964-434e-af60-a339042d0272.jpg,http://chinayiz.cn/m1/2017/0328/99a02830-d027-443b-b0c3-dffd3360f397.jpg,http://chinayiz.cn/m1/2017/0328/7ad7c0a3-9b56-4339-89c3-0e289a1339fb.jpg","gname":"葛仙米","itemcode":"002001","sname":"峰鹤","commentnum":5,"shopid":6,"productarea":"湖北恩施","101":"request parameter userid is null！","collectnum":null,"icon":"http://chinayiz.cn/m1/2017/0331/51762678-cf48-4eb4-a05c-6069a4a70e7c.jpg","sgrade":1,"relateGoods":[{"icon":"http://chinayiz.cn/m1/2017/0331/51762678-cf48-4eb4-a05c-6069a4a70e7c.jpg","unit":"盒","price":"240.00-490.00","goodsid":46,"isself":"0","gname":"葛仙米","brand":"峰鹤","goodsdesc":"地标产品","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0328/81d3d09c-2d36-4e4a-a56b-17a5f37c5496.jpg","unit":"瓶","price":"49.00-669.00","goodsid":47,"isself":"0","gname":"野生蜂蜜","brand":"峰鹤","goodsdesc":"传统饲养，一年仅取一次，无污染，零添加","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0331/ee2a53db-dc71-45c0-9124-27ba32d36c1b.jpg","unit":"克","price":"11.90-11.90","goodsid":48,"isself":"0","gname":"宝塔菜 以洪湖鲜虫草参为原料","brand":"洪湖农家","goodsdesc":"以洪湖鲜虫草参为原料","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0328/5f3b92d5-7fe5-459d-853b-d92ef4fb9146.jpg","unit":"g","price":"20.20-20.20","goodsid":51,"isself":"0","gname":"藕荷簪 以洪湖鲜藕带的藕荷簪为原料","brand":"洪湖农家","goodsdesc":"以洪湖鲜藕带的藕荷簪为原料","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0331/1d190a1b-39e3-438e-8727-57cee05fdde5.jpg","unit":"g","price":"13.00-13.00","goodsid":52,"isself":"0","gname":"菱角 以洪湖鲜菱角为原料","brand":"洪湖农家","goodsdesc":"以洪湖鲜菱角为原料","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0328/0bbde4ee-f4b2-4acd-912a-2097e4f9d04f.jpg","unit":"g","price":"32.20-32.20","goodsid":54,"isself":"0","gname":"干莲子","brand":"洪湖农家","goodsdesc":"以莲子为原料","itemcode":"002001"}],"isfeature":"1","isself":"0","netcontent":"60g","goodsstandardid":107,"pid":"6ed5d5bb-ae25-4ccf-b3ae-5f2a2bb2b85c","spic":"http://chinayiz.cn/v1/2017/0330/5826f5ed-af1e-4846-979c-a20b29117183.png","standardname":"葛仙米水精40克/盒","unit":"盒","logisticspoint":null,"rfid":null,"price":"240.00-490.00","goodsid":46,"goodsnum":2,"service":"真实货源,质量鉴定,7天退换货,","brand":"峰鹤","goodsdesc":"地标产品","praise":"40%","standardvalue":"40克/盒"}
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
         * servicepoint : null
         * providerid : 3
         * goodsavgpoint : null
         * commentlist : [{"isanonymity":null,"createtime":"2017-04-13 11:41:19","servicepoint":null,"commentscontent":null,"nickname":"jackywang ","cpic":null,"descpoint":4,"pic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1%2F2017%2F04%2F01%2F13%2F54%2F22.png","cid":1,"deliverypoint":null},{"isanonymity":null,"createtime":"2017-04-13 11:41:21","servicepoint":null,"commentscontent":null,"nickname":"啦啦啦啦啦啦啦","cpic":null,"descpoint":3,"pic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1%2F2017%2F04%2F11%2F04%2F07%2F27.png","cid":2,"deliverypoint":null}]
         * salesvolume : 0
         * gpic : http://chinayiz.cn/m1/2017/0328/42b18e4d-6df3-4b12-8c81-7c982d1e5ad9.jpg,http://chinayiz.cn/m1/2017/0328/6d8bc733-f433-4819-a02b-7f692da3acd9.jpg,http://chinayiz.cn/m1/2017/0328/c6491d4e-8964-434e-af60-a339042d0272.jpg,http://chinayiz.cn/m1/2017/0328/99a02830-d027-443b-b0c3-dffd3360f397.jpg,http://chinayiz.cn/m1/2017/0328/7ad7c0a3-9b56-4339-89c3-0e289a1339fb.jpg
         * gname : 葛仙米
         * itemcode : 002001
         * sname : 峰鹤
         * commentnum : 5
         * shopid : 6
         * productarea : 湖北恩施
         * 101 : request parameter userid is null！
         * collectnum : null
         * icon : http://chinayiz.cn/m1/2017/0331/51762678-cf48-4eb4-a05c-6069a4a70e7c.jpg
         * sgrade : 1
         * relateGoods : [{"icon":"http://chinayiz.cn/m1/2017/0331/51762678-cf48-4eb4-a05c-6069a4a70e7c.jpg","unit":"盒","price":"240.00-490.00","goodsid":46,"isself":"0","gname":"葛仙米","brand":"峰鹤","goodsdesc":"地标产品","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0328/81d3d09c-2d36-4e4a-a56b-17a5f37c5496.jpg","unit":"瓶","price":"49.00-669.00","goodsid":47,"isself":"0","gname":"野生蜂蜜","brand":"峰鹤","goodsdesc":"传统饲养，一年仅取一次，无污染，零添加","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0331/ee2a53db-dc71-45c0-9124-27ba32d36c1b.jpg","unit":"克","price":"11.90-11.90","goodsid":48,"isself":"0","gname":"宝塔菜 以洪湖鲜虫草参为原料","brand":"洪湖农家","goodsdesc":"以洪湖鲜虫草参为原料","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0328/5f3b92d5-7fe5-459d-853b-d92ef4fb9146.jpg","unit":"g","price":"20.20-20.20","goodsid":51,"isself":"0","gname":"藕荷簪 以洪湖鲜藕带的藕荷簪为原料","brand":"洪湖农家","goodsdesc":"以洪湖鲜藕带的藕荷簪为原料","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0331/1d190a1b-39e3-438e-8727-57cee05fdde5.jpg","unit":"g","price":"13.00-13.00","goodsid":52,"isself":"0","gname":"菱角 以洪湖鲜菱角为原料","brand":"洪湖农家","goodsdesc":"以洪湖鲜菱角为原料","itemcode":"002001"},{"icon":"http://chinayiz.cn/m1/2017/0328/0bbde4ee-f4b2-4acd-912a-2097e4f9d04f.jpg","unit":"g","price":"32.20-32.20","goodsid":54,"isself":"0","gname":"干莲子","brand":"洪湖农家","goodsdesc":"以莲子为原料","itemcode":"002001"}]
         * isfeature : 1
         * isself : 0
         * address: 地址
         * carriage: 运费
         * netcontent : 60g
         * goodsstandardid : 107
         * pid : 6ed5d5bb-ae25-4ccf-b3ae-5f2a2bb2b85c
         * spic : http://chinayiz.cn/v1/2017/0330/5826f5ed-af1e-4846-979c-a20b29117183.png
         * standardname : 葛仙米水精40克/盒
         * unit : 盒
         * logisticspoint : null
         * rfid : null
         * price : 240.00-490.00
         * goodsid : 46
         * goodsnum : 2
         * service : 真实货源,质量鉴定,7天退换货,
         * brand : 峰鹤
         * goodsdesc : 地标产品
         * praise : 40%
         * standardvalue : 40克/盒
         */

        private String servicepoint;
        private String providerid;
        private String goodsavgpoint;
        private String salesvolume;
        private String address;
        private String carriage;
        private String iscollect;
        private String gpic;
        private String gname;
        private String itemcode;
        private String sname;
        private String commentnum;
        private String shopid;
        private String productarea;
        @SerializedName("101")
        private String _$101;
        private String collectnum;
        private String icon;
        private String sgrade;
        private String isfeature;
        private String isself;
        private String netcontent;
        private String goodsstandardid;
        private String pid;
        private String spic;
        private String standardname;
        private String unit;
        private String logisticspoint;
        private String rfid;
        private String price;
        private String goodsid;
        private String goodsnum;
        private String service;
        private String brand;
        private String goodsdesc;
        private String praise;
        private String standardvalue;
        private List<CommentlistBean> commentlist;
        private List<RelateGoodsBean> relateGoods;

        public String getIscollect() {
            return iscollect;
        }

        public void setIscollect(String iscollect) {
            this.iscollect = iscollect;
        }
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCarriage() {
            return carriage;
        }

        public void setCarriage(String carriage) {
            this.carriage = carriage;
        }
        public String getServicepoint() {
            return servicepoint;
        }

        public void setServicepoint(String servicepoint) {
            this.servicepoint = servicepoint;
        }

        public String getProviderid() {
            return providerid;
        }

        public void setProviderid(String providerid) {
            this.providerid = providerid;
        }

        public String getGoodsavgpoint() {
            return goodsavgpoint;
        }

        public void setGoodsavgpoint(String goodsavgpoint) {
            this.goodsavgpoint = goodsavgpoint;
        }

        public String getSalesvolume() {
            return salesvolume;
        }

        public void setSalesvolume(String salesvolume) {
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

        public String getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(String commentnum) {
            this.commentnum = commentnum;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getProductarea() {
            return productarea;
        }

        public void setProductarea(String productarea) {
            this.productarea = productarea;
        }

        public String get_$101() {
            return _$101;
        }

        public void set_$101(String _$101) {
            this._$101 = _$101;
        }

        public String getCollectnum() {
            return collectnum;
        }

        public void setCollectnum(String collectnum) {
            this.collectnum = collectnum;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSgrade() {
            return sgrade;
        }

        public void setSgrade(String sgrade) {
            this.sgrade = sgrade;
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

        public String getNetcontent() {
            return netcontent;
        }

        public void setNetcontent(String netcontent) {
            this.netcontent = netcontent;
        }

        public String getGoodsstandardid() {
            return goodsstandardid;
        }

        public void setGoodsstandardid(String goodsstandardid) {
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

        public String getLogisticspoint() {
            return logisticspoint;
        }

        public void setLogisticspoint(String logisticspoint) {
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

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getGoodsnum() {
            return goodsnum;
        }

        public void setGoodsnum(String goodsnum) {
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

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getStandardvalue() {
            return standardvalue;
        }

        public void setStandardvalue(String standardvalue) {
            this.standardvalue = standardvalue;
        }

        public List<CommentlistBean> getCommentlist() {
            return commentlist;
        }

        public void setCommentlist(List<CommentlistBean> commentlist) {
            this.commentlist = commentlist;
        }

        public List<RelateGoodsBean> getRelateGoods() {
            return relateGoods;
        }

        public void setRelateGoods(List<RelateGoodsBean> relateGoods) {
            this.relateGoods = relateGoods;
        }

        public static class CommentlistBean {
            /**
             * isanonymity : null
             * createtime : 2017-04-13 11:41:19
             * servicepoint : null
             * commentscontent : null
             * nickname : jackywang
             * cpic : null
             * descpoint : 4
             * pic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1%2F2017%2F04%2F01%2F13%2F54%2F22.png
             * cid : 1
             * deliverypoint : null
             */

            private String isanonymity;
            private String createtime;
            private String servicepoint;
            private String commentscontent;
            private String nickname;
            private String cpic;
            private String descpoint;
            private String pic;
            private String cid;
            private String deliverypoint;

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

            public String getServicepoint() {
                return servicepoint;
            }

            public void setServicepoint(String servicepoint) {
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

            public String getDescpoint() {
                return descpoint;
            }

            public void setDescpoint(String descpoint) {
                this.descpoint = descpoint;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getDeliverypoint() {
                return deliverypoint;
            }

            public void setDeliverypoint(String deliverypoint) {
                this.deliverypoint = deliverypoint;
            }
        }

        public static class RelateGoodsBean {
            /**
             * icon : http://chinayiz.cn/m1/2017/0331/51762678-cf48-4eb4-a05c-6069a4a70e7c.jpg
             * unit : 盒
             * price : 240.00-490.00
             * goodsid : 46
             * isself : 0
             * gname : 葛仙米
             * brand : 峰鹤
             * goodsdesc : 地标产品
             * itemcode : 002001
             */

            private String icon;
            private String unit;
            private String price;
            private String goodsid;
            private String isself;
            private String gname;
            private String brand;
            private String goodsdesc;
            private String itemcode;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
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

            public String getGoodsdesc() {
                return goodsdesc;
            }

            public void setGoodsdesc(String goodsdesc) {
                this.goodsdesc = goodsdesc;
            }

            public String getItemcode() {
                return itemcode;
            }

            public void setItemcode(String itemcode) {
                this.itemcode = itemcode;
            }
        }
    }
}
