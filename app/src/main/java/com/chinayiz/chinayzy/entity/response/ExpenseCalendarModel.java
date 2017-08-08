package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * 红包专场消费记录
 * Created by Administrator on 2017/8/3.
 */

public class ExpenseCalendarModel extends BaseResponseModel {

    /**
     * data : {"list":[{"createtime":"2017-08-02 09:20:43","id":2,"point":20,"gname":null},{"createtime":"2017-08-01 16:13:44","id":1,"point":30,"gname":"富硒贡芽"}],"vippoint":0}
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
         * list : [{"createtime":"2017-08-02 09:20:43","id":2,"point":20,"gname":null},{"createtime":"2017-08-01 16:13:44","id":1,"point":30,"gname":"富硒贡芽"}]
         * vippoint : 0
         */

        private String vippoint;
        private List<ListBean> list;

        public String getVippoint() {
            return vippoint;
        }

        public void setVippoint(String vippoint) {
            this.vippoint = vippoint;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * createtime : 2017-08-02 09:20:43
             * id : 2
             * point : 20
             * gname : null
             */

            private String createtime;
            private String id;
            private String point;
            private String gname;
            private String messageRecode;

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getMessageRecode() {
                return messageRecode;
            }

            public void setMessageRecode(String messageRecode) {
                this.messageRecode = messageRecode;
            }
        }
    }
}
