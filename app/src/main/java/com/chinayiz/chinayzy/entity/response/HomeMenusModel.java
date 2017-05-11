package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/10 14:53
 * Class HomeMenusModel
 */

public class HomeMenusModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * syname : 生态农业
         * sytitle : 生态农业
         * sypic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0508/e884775b-e8de-4f6d-b9f5-ad814384846e.png
         * sylink : null
         * sytype : 1
         */

        private String syname;
        private String sytitle;
        private String sypic;
        private String sylink;
        private String sytype;

        public String getSyname() {
            return syname;
        }

        public void setSyname(String syname) {
            this.syname = syname;
        }

        public String getSytitle() {
            return sytitle;
        }

        public void setSytitle(String sytitle) {
            this.sytitle = sytitle;
        }

        public String getSypic() {
            return sypic;
        }

        public void setSypic(String sypic) {
            this.sypic = sypic;
        }

        public String getSylink() {
            return sylink;
        }

        public void setSylink(String sylink) {
            this.sylink = sylink;
        }

        public String getSytype() {
            return sytype;
        }

        public void setSytype(String sytype) {
            this.sytype = sytype;
        }
    }
}
