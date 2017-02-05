package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 */

public class FindTypeModel extends BaseResponseModel {
        /**
         * time : 2017-01-17 10:13:46
         * data : [{"typename":"综合","type":"1"},{"typename":"养生","type":"2"}]
         * code : 100
         * msg : 发现首页文章类型返回成功
         */

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * typename : 综合
             * type : 1
             */

            private String typename;
            private String type;

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

}
