package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/15 14:11
 * Class GoodsPicDetailModel 商品图文详情信息
 */
public class GoodsPicDetailModel extends BaseResponseModel{

    /**
     * data : {"detaildesc":"好吃","goodsid":2,"pic":"http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg","itemcode":"002001"}
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
         * detaildesc : 好吃
         * goodsid : 2
         * pic : http://img4.imgtn.bdimg.com/it/u=2595096625,1878289837&fm=23&gp=0.jpg
         * itemcode : 002001
         */

        private String detaildesc;
        private int goodsid;
        private String pic;
        private String itemcode;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getItemcode() {
            return itemcode;
        }

        public void setItemcode(String itemcode) {
            this.itemcode = itemcode;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "detaildesc='" + detaildesc + '\'' +
                    ", goodsid=" + goodsid +
                    ", pic='" + pic + '\'' +
                    ", itemcode='" + itemcode + '\'' +
                    '}';
        }
    }
}
