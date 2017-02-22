package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/4 16:31
 * Class StoreInfoModel
 */
public class StoreInfoModel extends BaseResponseModel {
    /**
     * data : {"isself":"1","typecodelist":[{"typecode":"001","typename":"自营茶叶"},{"typecode":"002","typename":"有机蔬菜"},{"typecode":"003","typename":"五谷杂粮"},{"typecode":"004","typename":"精品粮油"},{"typecode":"005","typename":"新鲜水果"},{"typecode":null,"typename":null},{"typecode":"000","typename":"全部商品"}],"isattention":"0","pic":"www.ccc.com","bigicon":"1.jpg","sname":"皇家国际饭店西餐厅","grade":5,"servicepoint":9.7,"shopid":1,"logisticspoint":3,"goodsavgpoint":9,"goodsnum":8,"introduction":"高端的店铺","goodslist":[{"detaildesc":"好吃","goodsid":1,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978891802&di=0503356ebe895df517add71013824e36&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F94%2F51%2F89Y58PICVMa_1024.jpg","gname":"云雾绿茶"},{"detaildesc":"好吃","goodsid":2,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978924687&di=e206b0b3d7d8508acc5ff25df9e00b8d&imgtype=0&src=http%3A%2F%2Fimg.99.com.cn%2Fuploads%2F151217%2F406_151445_1.jpg","gname":"麻油"},{"detaildesc":"好吃","goodsid":3,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978942869&di=fdd60dc54e6cc27caffc8a82853048b3&imgtype=0&src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F04%2F15%2F146070121400860530.JPEG","gname":"猪颈肉"},{"detaildesc":"好吃","goodsid":4,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978953192&di=4cae50773a9b82d831e1533ed8298c57&imgtype=0&src=http%3A%2F%2Fimg.edaocha.net%2Fuserfiles%2Fimage%2F20160918%2F181621064992c614b10553.jpg","gname":"小白菜"},{"detaildesc":"好吃","goodsid":5,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978962380&di=a363cbad0d57c4219aa5289b2146d7b1&imgtype=0&src=http%3A%2F%2Fpic.pingguolv.com%2Fuploads%2Fallimg%2F140807%2F73-140PGH315.jpg","gname":"苹果"},{"detaildesc":"好吃","goodsid":7,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978973671&di=c8abcfbfdd1de40e525d117962e662d7&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3De4abe7b609f79052ef1f47363cf2d738%2F6707c9cd7b899e51cbbcaeb142a7d933c9950d66.jpg","gname":"云雾毛尖"},{"detaildesc":"好吃","goodsid":8,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978999653&di=fb37ba8ffe8a37eb426886129abea6c8&imgtype=0&src=http%3A%2F%2Fdifang.kaiwind.com%2Fxinjiang%2Fkpyd%2F201405%2F10%2FW020140510446216377257.jpg","gname":"云雾红茶"},{"detaildesc":"好吃","goodsid":9,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484979361036&di=84eeb6a9a35d65b06914e6bc3498fff3&imgtype=0&src=http%3A%2F%2Fimage.sonhoo.com%2Fuserpic%2Fzljt676767%2F1684868201095105412.jpg","gname":"娃哈哈"}]}
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
         * isself : 1
         * typecodelist : [{"typecode":"001","typename":"自营茶叶"},{"typecode":"002","typename":"有机蔬菜"},{"typecode":"003","typename":"五谷杂粮"},{"typecode":"004","typename":"精品粮油"},{"typecode":"005","typename":"新鲜水果"},{"typecode":null,"typename":null},{"typecode":"000","typename":"全部商品"}]
         * isattention : 0
         * pic : www.ccc.com
         * bigicon : 1.jpg
         * sname : 皇家国际饭店西餐厅
         * grade : 5
         * servicepoint : 9.7
         * shopid : 1
         * logisticspoint : 3
         * goodsavgpoint : 9
         * goodsnum : 8
         * introduction : 高端的店铺
         * goodslist : [{"detaildesc":"好吃","goodsid":1,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978891802&di=0503356ebe895df517add71013824e36&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F94%2F51%2F89Y58PICVMa_1024.jpg","gname":"云雾绿茶"},{"detaildesc":"好吃","goodsid":2,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978924687&di=e206b0b3d7d8508acc5ff25df9e00b8d&imgtype=0&src=http%3A%2F%2Fimg.99.com.cn%2Fuploads%2F151217%2F406_151445_1.jpg","gname":"麻油"},{"detaildesc":"好吃","goodsid":3,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978942869&di=fdd60dc54e6cc27caffc8a82853048b3&imgtype=0&src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F04%2F15%2F146070121400860530.JPEG","gname":"猪颈肉"},{"detaildesc":"好吃","goodsid":4,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978953192&di=4cae50773a9b82d831e1533ed8298c57&imgtype=0&src=http%3A%2F%2Fimg.edaocha.net%2Fuserfiles%2Fimage%2F20160918%2F181621064992c614b10553.jpg","gname":"小白菜"},{"detaildesc":"好吃","goodsid":5,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978962380&di=a363cbad0d57c4219aa5289b2146d7b1&imgtype=0&src=http%3A%2F%2Fpic.pingguolv.com%2Fuploads%2Fallimg%2F140807%2F73-140PGH315.jpg","gname":"苹果"},{"detaildesc":"好吃","goodsid":7,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978973671&di=c8abcfbfdd1de40e525d117962e662d7&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3De4abe7b609f79052ef1f47363cf2d738%2F6707c9cd7b899e51cbbcaeb142a7d933c9950d66.jpg","gname":"云雾毛尖"},{"detaildesc":"好吃","goodsid":8,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978999653&di=fb37ba8ffe8a37eb426886129abea6c8&imgtype=0&src=http%3A%2F%2Fdifang.kaiwind.com%2Fxinjiang%2Fkpyd%2F201405%2F10%2FW020140510446216377257.jpg","gname":"云雾红茶"},{"detaildesc":"好吃","goodsid":9,"price":"50-100","icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484979361036&di=84eeb6a9a35d65b06914e6bc3498fff3&imgtype=0&src=http%3A%2F%2Fimage.sonhoo.com%2Fuserpic%2Fzljt676767%2F1684868201095105412.jpg","gname":"娃哈哈"}]
         */
        private String isself;
        private String isattention;
        private String pic;
        private String bigicon;
        private String sname;
        private int grade;
        private double servicepoint;
        private int shopid;
        private int logisticspoint;
        private int goodsavgpoint;
        private int goodsnum;
        private String introduction;
        private List<TypecodeBean> typecodelist;
        private List<GoodsBean> goodslist;

        public String getIsself() {
            return isself;
        }

        public void setIsself(String isself) {
            this.isself = isself;
        }

        public String getIsattention() {
            return isattention;
        }

        public void setIsattention(String isattention) {
            this.isattention = isattention;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getBigicon() {
            return bigicon;
        }

        public void setBigicon(String bigicon) {
            this.bigicon = bigicon;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public double getServicepoint() {
            return servicepoint;
        }

        public void setServicepoint(double servicepoint) {
            this.servicepoint = servicepoint;
        }

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public int getLogisticspoint() {
            return logisticspoint;
        }

        public void setLogisticspoint(int logisticspoint) {
            this.logisticspoint = logisticspoint;
        }

        public int getGoodsavgpoint() {
            return goodsavgpoint;
        }

        public void setGoodsavgpoint(int goodsavgpoint) {
            this.goodsavgpoint = goodsavgpoint;
        }

        public int getGoodsnum() {
            return goodsnum;
        }

        public void setGoodsnum(int goodsnum) {
            this.goodsnum = goodsnum;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public List<TypecodeBean> getTypecodelist() {
            return typecodelist;
        }

        public void setTypecodelist(List<TypecodeBean> typecodelist) {
            this.typecodelist = typecodelist;
        }

        public List<GoodsBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodsBean> goodslist) {
            this.goodslist = goodslist;
        }

        public  class TypecodeBean {
            /**
             * typecode : 001
             * typename : 自营茶叶
             */

            private String typecode;
            private String tname;

            public String getTypecode() {
                return typecode;
            }

            public void setTypecode(String typecode) {
                this.typecode = typecode;
            }

            public String getTypename() {
                return tname;
            }

            public void setTypename(String typename) {
                this.tname = typename;
            }
        }

        public static class GoodsBean {
            /**
             * detaildesc : 好吃
             * goodsid : 1
             * price : 50-100
             * icon : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484978891802&di=0503356ebe895df517add71013824e36&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F94%2F51%2F89Y58PICVMa_1024.jpg
             * gname : 云雾绿茶
             */

            private String detaildesc;
            private int goodsid;
            private String price;
            private String icon;
            private String gname;

            public String getDetaildesc() {
                return detaildesc;
            }

            public void setDetaildesc(String detaildesc) {
                this.detaildesc = detaildesc;
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

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            @Override
            public String toString() {
                return "GoodsBean{" +
                        "detaildesc='" + detaildesc + '\'' +
                        ", goodsid=" + goodsid +
                        ", price='" + price + '\'' +
                        ", icon='" + icon + '\'' +
                        ", gname='" + gname + '\'' +
                        '}';
            }
        }
    }
}
