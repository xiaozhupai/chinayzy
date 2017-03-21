package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MyStepModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private List<FootmarklistBean> footmarklist;

        public List<FootmarklistBean> getFootmarklist() {
            return footmarklist;
        }

        public void setFootmarklist(List<FootmarklistBean> footmarklist) {
            this.footmarklist = footmarklist;
        }

        /**
         *  获取Item内容
         *
         * @param pPosition
         * @return
         */
        public MyStepModel.DataBean.FootmarklistBean getItem(int pPosition) {
            // Category排在第一位
            if (pPosition == 0) {
                return footmarklist.get(0);
            } else {
                return footmarklist.get(pPosition - 1);
            }
        }


        public static class FootmarklistBean {
            /**
             * price : 50-100
             * goodsid : 4
             * gname : 小白菜
             * goodsdesc : 好吃
             * lt : 01月19日
             * pic : http://chinayiz.cn/m1/2017/0218/15cea45b-c386-46de-9260-a33870bc0769.jpg,http://chinayiz.cn/m1/2017/0218/24bda0fb-75d8-432c-8059-13ad3a015463.jpg,http://chinayiz.cn/m1/2017/0218/55e9dd8e-ade6-4024-8a92-05f412d8bbd0.jpg
             */

            private String price;
            private int goodsid;
            private String gname;
            private String goodsdesc;
            private String lt;
            private String pic;

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

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getGoodsdesc() {
                return goodsdesc;
            }

            public void setGoodsdesc(String goodsdesc) {
                this.goodsdesc = goodsdesc;
            }

            public String getLt() {
                return lt;
            }

            public void setLt(String lt) {
                this.lt = lt;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
