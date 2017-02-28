package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/28 9:19
 * Class ClassifyTypeModel  生态农业二级分类菜单类型
 */
public class ClassifyTypesModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * typecode : 001
         * sequencenum : 1
         * tname : 茶饮食品
         * pic : http://chinayiz.cn/m1/2017/0218/24bda0fb-75d8-432c-8059-13ad3a015463.jpg
         */

        private String typecode;
        private int sequencenum;
        private String tname;
        private String pic;

        public String getTypecode() {
            return typecode;
        }

        public void setTypecode(String typecode) {
            this.typecode = typecode;
        }

        public int getSequencenum() {
            return sequencenum;
        }

        public void setSequencenum(int sequencenum) {
            this.sequencenum = sequencenum;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "pic='" + pic + '\'' +
                    ", typecode='" + typecode + '\'' +
                    ", sequencenum=" + sequencenum +
                    ", tname='" + tname + '\'' +
                    '}';
        }
    }
}
