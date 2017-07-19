package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/7/14 14:47
 * Class HomeMainActivitysModel
 */

public class HomeMainActivitysModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * isshow : 1
         * link :
         * pic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0713/a6895281-6c9f-47f3-bcd2-36fa71504f35.png
         * type : 1
         * asid : 1
         */

        private String isshow;
        private String link;
        private String pic;
        private String type;
        private String asid;

        public String getIsshow() {
            return isshow;
        }

        public void setIsshow(String isshow) {
            this.isshow = isshow;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAsid() {
            return asid;
        }

        public void setAsid(String asid) {
            this.asid = asid;
        }
    }
}
