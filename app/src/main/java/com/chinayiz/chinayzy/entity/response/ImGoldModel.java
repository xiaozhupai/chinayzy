package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/8 14:21
 * Class ImGoldModel   我的积分
 */
public class ImGoldModel extends BaseResponseModel {

    /**
     * data : {"totalpoints":0.01,"earningslist":[{"point":"+0.01","operatetime":"2017-03-02 13:49:19","name":"充值"},{"point":"+30.00","operatetime":"2017-03-02 10:03:17","name":"推荐奖励"},{"point":"+30.00","operatetime":"2017-03-02 10:03:16","name":"推荐奖励"},{"point":"+100.00","operatetime":null,"name":"充值"},{"point":"+50.00","operatetime":null,"name":"充值"},{"point":"+100.00","operatetime":null,"name":"充值"},{"point":"+220.00","operatetime":null,"name":"充值"},{"point":"+30.00","operatetime":"2017-03-02 10:03:11","name":"活动-ddddd奖励"},{"point":"+30.00","operatetime":"2017-03-02 10:03:10","name":"活动-ddddd奖励"},{"point":"+30.00","operatetime":"2017-03-02 10:03:08","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:51","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:50","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:49","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:47","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:45","name":"活动-dfgdf奖励"},{"point":"+20.00","operatetime":"2017-03-02 09:53:43","name":"活动-ddddd奖励"},{"point":"+2000.00","operatetime":"2017-03-02 09:10:55","name":"活动-dfgdf奖励"}],"cancarrypoints":2260}
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
         * totalpoints : 0.01
         * earningslist : [{"point":"+0.01","operatetime":"2017-03-02 13:49:19","name":"充值"},{"point":"+30.00","operatetime":"2017-03-02 10:03:17","name":"推荐奖励"},{"point":"+30.00","operatetime":"2017-03-02 10:03:16","name":"推荐奖励"},{"point":"+100.00","operatetime":null,"name":"充值"},{"point":"+50.00","operatetime":null,"name":"充值"},{"point":"+100.00","operatetime":null,"name":"充值"},{"point":"+220.00","operatetime":null,"name":"充值"},{"point":"+30.00","operatetime":"2017-03-02 10:03:11","name":"活动-ddddd奖励"},{"point":"+30.00","operatetime":"2017-03-02 10:03:10","name":"活动-ddddd奖励"},{"point":"+30.00","operatetime":"2017-03-02 10:03:08","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:51","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:50","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:49","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:47","name":"活动-dfgdf奖励"},{"point":"+30.00","operatetime":"2017-03-02 09:53:45","name":"活动-dfgdf奖励"},{"point":"+20.00","operatetime":"2017-03-02 09:53:43","name":"活动-ddddd奖励"},{"point":"+2000.00","operatetime":"2017-03-02 09:10:55","name":"活动-dfgdf奖励"}]
         * cancarrypoints : 2260
         */

        private double totalpoints;
        private int cancarrypoints;
        private List<EarningslistBean> earningslist;

        public double getTotalpoints() {
            return totalpoints;
        }

        public void setTotalpoints(double totalpoints) {
            this.totalpoints = totalpoints;
        }

        public int getCancarrypoints() {
            return cancarrypoints;
        }

        public void setCancarrypoints(int cancarrypoints) {
            this.cancarrypoints = cancarrypoints;
        }

        public List<EarningslistBean> getEarningslist() {
            return earningslist;
        }

        public void setEarningslist(List<EarningslistBean> earningslist) {
            this.earningslist = earningslist;
        }

        public static class EarningslistBean {
            /**
             * point : +0.01
             * operatetime : 2017-03-02 13:49:19
             * name : 充值
             */
            private String point;
            private String operatetime;
            private String name;

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getOperatetime() {
                return operatetime;
            }

            public void setOperatetime(String operatetime) {
                this.operatetime = operatetime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
