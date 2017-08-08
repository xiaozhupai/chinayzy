package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/5.
 */

public class ResultRedModel extends BaseResponseModel {
    /**
     * data : {"totalmoney":10,"carriages":[{"shopid":16,"carriage":10,"shopTatalPrice":1350,"goodsnum":1}],"addressRecord":{"phone":"18736047578","area":"湖北省 武汉市 洪山区","addressid":43,"address":"曙光新村 曙光星城 B区八栋206","consignee":"袁坤坤","province":"湖北省"},"vippoint":0.55,"goodmessage":[{"goodmessagelist":[{"icon":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0428/dc4e62a4-90e7-411b-b7cd-751365e95301.jpg","shopid":16,"num":1,"price":1350,"gname":"益众之玉衡 优质红茶/恩施富硒红茶/富硒红茶  240g/盒","isself":"1","netcontent":"240g","goodsstandardid":843,"pic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0607/1c90ba7a-1667-4599-8a41-326124ca1224.jpg","carid":544,"sname":"亿众生态馆","cost":1688,"standardname":"茶/富硒红茶"}]}]}
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
         * totalmoney : 10
         * carriages : [{"shopid":16,"carriage":10,"shopTatalPrice":1350,"goodsnum":1}]
         * addressRecord : {"phone":"18736047578","area":"湖北省 武汉市 洪山区","addressid":43,"address":"曙光新村 曙光星城 B区八栋206","consignee":"袁坤坤","province":"湖北省"}
         * vippoint : 0.55
         * goodmessage : [{"goodmessagelist":[{"icon":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0428/dc4e62a4-90e7-411b-b7cd-751365e95301.jpg","shopid":16,"num":1,"price":1350,"gname":"益众之玉衡 优质红茶/恩施富硒红茶/富硒红茶  240g/盒","isself":"1","netcontent":"240g","goodsstandardid":843,"pic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0607/1c90ba7a-1667-4599-8a41-326124ca1224.jpg","carid":544,"sname":"亿众生态馆","cost":1688,"standardname":"茶/富硒红茶"}]}]
         */

        private int totalmoney;
        private AddressRecordBean addressRecord;
        private double vippoint;
        private List<CarriagesBean> carriages;
        private List<GoodmessageBean> goodmessage;

        public int getTotalmoney() {
            return totalmoney;
        }

        public void setTotalmoney(int totalmoney) {
            this.totalmoney = totalmoney;
        }

        public AddressRecordBean getAddressRecord() {
            return addressRecord;
        }

        public void setAddressRecord(AddressRecordBean addressRecord) {
            this.addressRecord = addressRecord;
        }

        public double getVippoint() {
            return vippoint;
        }

        public void setVippoint(double vippoint) {
            this.vippoint = vippoint;
        }

        public List<CarriagesBean> getCarriages() {
            return carriages;
        }

        public void setCarriages(List<CarriagesBean> carriages) {
            this.carriages = carriages;
        }

        public List<GoodmessageBean> getGoodmessage() {
            return goodmessage;
        }

        public void setGoodmessage(List<GoodmessageBean> goodmessage) {
            this.goodmessage = goodmessage;
        }

        public static class AddressRecordBean {
            /**
             * phone : 18736047578
             * area : 湖北省 武汉市 洪山区
             * addressid : 43
             * address : 曙光新村 曙光星城 B区八栋206
             * consignee : 袁坤坤
             * province : 湖北省
             */

            private String phone;
            private String area;
            private int addressid;
            private String address;
            private String consignee;
            private String province;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getAddressid() {
                return addressid;
            }

            public void setAddressid(int addressid) {
                this.addressid = addressid;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }
        }

        public static class CarriagesBean {
            /**
             * shopid : 16
             * carriage : 10
             * shopTatalPrice : 1350
             * goodsnum : 1
             */

            private int shopid;
            private int carriage;
            private int shopTatalPrice;
            private int goodsnum;

            public int getShopid() {
                return shopid;
            }

            public void setShopid(int shopid) {
                this.shopid = shopid;
            }

            public int getCarriage() {
                return carriage;
            }

            public void setCarriage(int carriage) {
                this.carriage = carriage;
            }

            public int getShopTatalPrice() {
                return shopTatalPrice;
            }

            public void setShopTatalPrice(int shopTatalPrice) {
                this.shopTatalPrice = shopTatalPrice;
            }

            public int getGoodsnum() {
                return goodsnum;
            }

            public void setGoodsnum(int goodsnum) {
                this.goodsnum = goodsnum;
            }
        }

        public static class GoodmessageBean {
            private List<GoodmessagelistBean> goodmessagelist;

            public List<GoodmessagelistBean> getGoodmessagelist() {
                return goodmessagelist;
            }

            public void setGoodmessagelist(List<GoodmessagelistBean> goodmessagelist) {
                this.goodmessagelist = goodmessagelist;
            }

            public static class GoodmessagelistBean {
                /**
                 * icon : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0428/dc4e62a4-90e7-411b-b7cd-751365e95301.jpg
                 * shopid : 16
                 * num : 1
                 * price : 1350
                 * gname : 益众之玉衡 优质红茶/恩施富硒红茶/富硒红茶  240g/盒
                 * isself : 1
                 * netcontent : 240g
                 * goodsstandardid : 843
                 * pic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0607/1c90ba7a-1667-4599-8a41-326124ca1224.jpg
                 * carid : 544
                 * sname : 亿众生态馆
                 * cost : 1688
                 * standardname : 茶/富硒红茶
                 */

                private String icon;
                private int shopid;
                private int num;
                private int price;
                private String gname;
                private String isself;
                private String netcontent;
                private int goodsstandardid;
                private String pic;
                private int carid;
                private String sname;
                private int cost;
                private String standardname;

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public int getShopid() {
                    return shopid;
                }

                public void setShopid(int shopid) {
                    this.shopid = shopid;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getGname() {
                    return gname;
                }

                public void setGname(String gname) {
                    this.gname = gname;
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

                public int getGoodsstandardid() {
                    return goodsstandardid;
                }

                public void setGoodsstandardid(int goodsstandardid) {
                    this.goodsstandardid = goodsstandardid;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getCarid() {
                    return carid;
                }

                public void setCarid(int carid) {
                    this.carid = carid;
                }

                public String getSname() {
                    return sname;
                }

                public void setSname(String sname) {
                    this.sname = sname;
                }

                public int getCost() {
                    return cost;
                }

                public void setCost(int cost) {
                    this.cost = cost;
                }

                public String getStandardname() {
                    return standardname;
                }

                public void setStandardname(String standardname) {
                    this.standardname = standardname;
                }
            }
        }
    }
}
