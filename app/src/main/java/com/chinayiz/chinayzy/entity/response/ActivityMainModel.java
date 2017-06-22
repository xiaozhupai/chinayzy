package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ActivityMainModel extends BaseResponseModel {

    /**
     * data : {"gname":"EDGER 2017新款韩版女鞋高跟鞋女细跟单鞋   原价1250元 现价437元","crowdfid":1}
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
         * gname : EDGER 2017新款韩版女鞋高跟鞋女细跟单鞋   原价1250元 现价437元
         * crowdfid : 1
         * status :   0
         */

        private String gname;
        private String crowdfid;
        private String status;

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getCrowdfid() {
            return crowdfid;
        }

        public void setCrowdfid(String crowdfid) {
            this.crowdfid = crowdfid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
