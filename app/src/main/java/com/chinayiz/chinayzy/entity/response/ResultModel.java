package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */

public class ResultModel extends BaseResponseModel {

    /**
     * data : {"totalmoney":1458,"deductionpoint":0,"isxjjuan":"1","carriages":[{"shopid":4,"carriage":8,"shopTatalPrice":1450,"goodsnum":1}],"addressRecord":{"phone":"13871009000","area":"湖北省武汉市洪山区","addressid":11,"address":"东湖新技术开发区光谷大道3号未来之光1-502","consignee":"王建超","province":"湖北省"},"coupon":{"couponid":1,"couponname":"现金卷","couponprice":1350,"couponremark":"现金卷说明"},"goodmessage":[{"goodmessagelist":[{"icon":"http: //yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0330/73011f81-fb84-4fb1-b60e-4949d9d444fd.jpg","shopid":4,"num":1,"price":1450,"gname":"富硒贡芽","isself":"1","netcontent":"200g","goodsstandardid":159,"pic":"http: //yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0401/9793b104-08ca-41f1-97e2-07e6453a45ac.png","carid":32,"sname":"亿众生态店","standardname":"大气礼盒"}]}]}
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
         * totalmoney : 1458
         * deductionpoint : 0
         * isxjjuan : 1
         * carriages : [{"shopid":4,"carriage":8,"shopTatalPrice":1450,"goodsnum":1}]
         * addressRecord : {"phone":"13871009000","area":"湖北省武汉市洪山区","addressid":11,"address":"东湖新技术开发区光谷大道3号未来之光1-502","consignee":"王建超","province":"湖北省"}
         * coupon : {"couponid":1,"couponname":"现金卷","couponprice":1350,"couponremark":"现金卷说明"}
         * goodmessage : [{"goodmessagelist":[{"icon":"http: //yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0330/73011f81-fb84-4fb1-b60e-4949d9d444fd.jpg","shopid":4,"num":1,"price":1450,"gname":"富硒贡芽","isself":"1","netcontent":"200g","goodsstandardid":159,"pic":"http: //yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0401/9793b104-08ca-41f1-97e2-07e6453a45ac.png","carid":32,"sname":"亿众生态店","standardname":"大气礼盒"}]}]
         */

        private String totalmoney;
        private double deductionpoint;
        private String isxjjuan;
        private AddressRecordBean addressRecord;
        private CouponBean coupon;
        private List<CarriagesBean> carriages;
        private List<GoodmessageBean> goodmessage;


        public String getTotalmoney() {
            return totalmoney;
        }

        public void setTotalmoney(String totalmoney) {
            this.totalmoney = totalmoney;
        }

        public double getDeductionpoint() {
            return deductionpoint;
        }

        public void setDeductionpoint(double deductionpoint) {
            this.deductionpoint = deductionpoint;
        }

        public String getIsxjjuan() {
            return isxjjuan;
        }

        public void setIsxjjuan(String isxjjuan) {
            this.isxjjuan = isxjjuan;
        }

        public AddressRecordBean getAddressRecord() {
            return addressRecord;
        }

        public void setAddressRecord(AddressRecordBean addressRecord) {
            this.addressRecord = addressRecord;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
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
             * phone : 13871009000
             * area : 湖北省武汉市洪山区
             * addressid : 11
             * address : 东湖新技术开发区光谷大道3号未来之光1-502
             * consignee : 王建超
             * province : 湖北省
             */

            private String phone;
            private String area;
            private String addressid;
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

            public String getAddressid() {
                return addressid;
            }

            public void setAddressid(String addressid) {
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

        public static class CouponBean {
            /**
             * couponid : 1
             * couponname : 现金卷
             * couponprice : 1350
             * couponremark : 现金卷说明
             */

            private String couponid;
            private String couponname;
            private String couponprice;
            private String couponremark;
            private int count;
            private String couponlogids;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getCouponlogids() {
                return couponlogids;
            }

            public void setCouponlogids(String couponlogids) {
                this.couponlogids = couponlogids;
            }

            public String getCouponid() {
                return couponid;
            }

            public void setCouponid(String couponid) {
                this.couponid = couponid;
            }

            public String getCouponname() {
                return couponname;
            }

            public void setCouponname(String couponname) {
                this.couponname = couponname;
            }

            public String getCouponprice() {
                return couponprice;
            }

            public void setCouponprice(String couponprice) {
                this.couponprice = couponprice;
            }

            public String getCouponremark() {
                return couponremark;
            }

            public void setCouponremark(String couponremark) {
                this.couponremark = couponremark;
            }
        }

        public static class CarriagesBean {
            /**
             * shopid : 4
             * carriage : 8
             * shopTatalPrice : 1450
             * goodsnum : 1
             */

            private int shopid;
            private int carriage;
            private double shopTatalPrice;
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

            public double getShopTatalPrice() {
                return shopTatalPrice;
            }

            public void setShopTatalPrice(double shopTatalPrice) {
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
                 * icon : http: //yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0330/73011f81-fb84-4fb1-b60e-4949d9d444fd.jpg
                 * shopid : 4
                 * num : 1
                 * price : 1450
                 * gname : 富硒贡芽
                 * isself : 1
                 * netcontent : 200g
                 * goodsstandardid : 159
                 * pic : http: //yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0401/9793b104-08ca-41f1-97e2-07e6453a45ac.png
                 * carid : 32
                 * sname : 亿众生态店
                 * standardname : 大气礼盒
                 */

                private String icon;
                private int shopid;
                private int num;
                private double price;
                private String gname;
                private String isself;
                private String netcontent;
                private int goodsstandardid;
                private String pic;
                private int carid;
                private String sname;
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

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
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
