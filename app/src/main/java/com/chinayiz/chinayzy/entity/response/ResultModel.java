package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */

public class ResultModel extends BaseResponseModel {

    /**
     * data : {"totalmoney":1178.5,"deductionpoint":0,"carriages":[{"shopid":1,"carriage":10,"shopTatalPrice":1000.5,"goodsnum":2},{"shopid":3,"carriage":10,"shopTatalPrice":158,"goodsnum":1}],"addressRecord":{"phone":"18736045781","area":"湖北省 武汉市 洪山区","addressid":11,"address":"光谷大道未来之光一栋50009\n","consignee":"袁坤坤"},"goodmessage":[{"goodmessagelist":[{"icon":"http://chinayiz.cn/m1/2017/0222/e1e63f48-8025-467e-aa42-e8ead41cd685.jpg","shopid":1,"num":2,"price":500.25,"gname":"云雾红茶","isself":"1","goodsstandardid":9,"pic":"http://chinayiz.cn/m1/2017/0220/db2e9814-6d52-46bc-b18a-42c60bdb64b1.jpg","carid":129,"sname":"皇家国际西餐厅","standardname":"10只装"}]},{"goodmessagelist":[{"icon":"http://chinayiz.cn/m1/2017/0222/e1e63f48-8025-467e-aa42-e8ead41cd685.jpg","shopid":3,"num":1,"price":158,"gname":"润虎 茶叶 红茶 武夷山金骏眉 大师茶陶瓷罐礼盒装","isself":"1","goodsstandardid":18,"pic":"http://chinayiz.cn/v1/2017/0216/64aae6de-928e-46fe-8f7a-d9571fadab95.png","carid":128,"sname":"亿众自营店","standardname":"福至年年好金骏眉礼盒"}]}]}
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
         * totalmoney : 1178.5
         * deductionpoint : 0.0
         * carriages : [{"shopid":1,"carriage":10,"shopTatalPrice":1000.5,"goodsnum":2},{"shopid":3,"carriage":10,"shopTatalPrice":158,"goodsnum":1}]
         * addressRecord : {"phone":"18736045781","area":"湖北省 武汉市 洪山区","addressid":11,"address":"光谷大道未来之光一栋50009\n","consignee":"袁坤坤"}
         * goodmessage : [{"goodmessagelist":[{"icon":"http://chinayiz.cn/m1/2017/0222/e1e63f48-8025-467e-aa42-e8ead41cd685.jpg","shopid":1,"num":2,"price":500.25,"gname":"云雾红茶","isself":"1","goodsstandardid":9,"pic":"http://chinayiz.cn/m1/2017/0220/db2e9814-6d52-46bc-b18a-42c60bdb64b1.jpg","carid":129,"sname":"皇家国际西餐厅","standardname":"10只装"}]},{"goodmessagelist":[{"icon":"http://chinayiz.cn/m1/2017/0222/e1e63f48-8025-467e-aa42-e8ead41cd685.jpg","shopid":3,"num":1,"price":158,"gname":"润虎 茶叶 红茶 武夷山金骏眉 大师茶陶瓷罐礼盒装","isself":"1","goodsstandardid":18,"pic":"http://chinayiz.cn/v1/2017/0216/64aae6de-928e-46fe-8f7a-d9571fadab95.png","carid":128,"sname":"亿众自营店","standardname":"福至年年好金骏眉礼盒"}]}]
         */

        private double totalmoney;
        private double deductionpoint;
        private AddressRecordBean addressRecord;
        private List<CarriagesBean> carriages;
        private List<GoodmessageBean> goodmessage;

        public double getTotalmoney() {
            return totalmoney;
        }

        public void setTotalmoney(double totalmoney) {
            this.totalmoney = totalmoney;
        }

        public double getDeductionpoint() {
            return deductionpoint;
        }

        public void setDeductionpoint(double deductionpoint) {
            this.deductionpoint = deductionpoint;
        }

        public AddressRecordBean getAddressRecord() {
            return addressRecord;
        }

        public void setAddressRecord(AddressRecordBean addressRecord) {
            this.addressRecord = addressRecord;
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
             * phone : 18736045781
             * area : 湖北省 武汉市 洪山区
             * addressid : 11
             * address : 光谷大道未来之光一栋50009

             * consignee : 袁坤坤
             */

            private String phone;
            private String area;
            private int addressid;
            private String address;
            private String consignee;

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
        }

        public static class CarriagesBean {
            /**
             * shopid : 1
             * carriage : 10.0
             * shopTatalPrice : 1000.5
             * goodsnum : 2
             */

            private int shopid;
            private double carriage;
            private double shopTatalPrice;
            private int goodsnum;

            public int getShopid() {
                return shopid;
            }

            public void setShopid(int shopid) {
                this.shopid = shopid;
            }

            public double getCarriage() {
                return carriage;
            }

            public void setCarriage(double carriage) {
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

            /**
             *  获取Item内容
             *
             * @param pPosition
             * @return
             */
            public GoodmessageBean.GoodmessagelistBean getItem(int pPosition) {
                // Category排在第一位
                if (pPosition == 0) {
                    return goodmessagelist.get(0);
                } else {
                    return goodmessagelist.get(pPosition - 1);
                }
            }

            public static class GoodmessagelistBean {
                /**
                 * icon : http://chinayiz.cn/m1/2017/0222/e1e63f48-8025-467e-aa42-e8ead41cd685.jpg
                 * shopid : 1
                 * num : 2
                 * price : 500.25
                 * gname : 云雾红茶
                 * isself : 1
                 * goodsstandardid : 9
                 * pic : http://chinayiz.cn/m1/2017/0220/db2e9814-6d52-46bc-b18a-42c60bdb64b1.jpg
                 * carid : 129
                 * sname : 皇家国际西餐厅
                 * standardname : 10只装
                 */

                private String icon;
                private int shopid;
                private int num;
                private double price;
                private String gname;
                private String isself;
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
