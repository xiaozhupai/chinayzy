package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/3/7.
 */

public class KeeporZanModel extends BaseResponseModel {


    /**
     * data : {"iscollect":"1","ispraise":"\u201c1\u201d"}
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
         * iscollect : 1
         * ispraise : “1”
         */

        private String iscollect;
        private String ispraise;

        public String getIscollect() {
            return iscollect;
        }

        public void setIscollect(String iscollect) {
            this.iscollect = iscollect;
        }

        public String getIspraise() {
            return ispraise;
        }

        public void setIspraise(String ispraise) {
            this.ispraise = ispraise;
        }
    }
}
