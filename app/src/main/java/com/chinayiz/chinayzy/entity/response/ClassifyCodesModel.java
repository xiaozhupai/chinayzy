package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/27 18:44
 * Class ClassifyCodesModel 生态农业二级分类菜单code
 */
public class ClassifyCodesModel extends BaseResponseModel{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
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
                    "itemcode='" + itemcode + '\'' +
                    ", itemname='" + itemname + '\'' +
                    ", typepic='" + typepic + '\'' +
                    '}';
        }
    }
}
