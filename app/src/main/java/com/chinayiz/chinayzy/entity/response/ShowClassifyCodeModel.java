package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * 红包专场分类Listview的model
 * Created by Administrator on 2017/8/1.
 */

public class ShowClassifyCodeModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ShowClassifyCodeModel{" +
                "data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * itemname : 热卖茶叶
         * itemcode : 001000
         * typepic : http://xxx.jpg
         */

        private String itemname;
        private String itemcode;
        private String typepic;

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public String getItemcode() {
            return itemcode;
        }

        public void setItemcode(String itemcode) {
            this.itemcode = itemcode;
        }

        public String getTypepic() {
            return typepic;
        }

        public void setTypepic(String typepic) {
            this.typepic = typepic;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "itemname='" + itemname + '\'' +
                    ", itemcode='" + itemcode + '\'' +
                    ", typepic='" + typepic + '\'' +
                    '}';
        }
    }
}
