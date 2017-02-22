package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/9 14:58
 * Class TypeListModel 类型菜单
 */
public class TypeListModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public  class DataBean {
        /**
         * itemname : 热卖茶叶
         * itemcode : 001000
         */

        private String itemname;
        private String itemcode;

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
    }
}
