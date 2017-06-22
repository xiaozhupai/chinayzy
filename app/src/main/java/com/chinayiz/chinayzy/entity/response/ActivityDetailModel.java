package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**获奖详情
 * Created by Administrator on 2017/6/16.
 */

public class ActivityDetailModel extends BaseResponseModel {

    /**
     * data : {"gname":"EDGER 2017新款韩版女鞋高跟鞋女细跟单鞋   原价1250元 现价437元","crowdfid":1,"allcount":1,"invitecount":1,"cost":1,"winnertime":1,"icon":1,"phone":1}
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
         * allcount : 1
         * invitecount : 1
         * cost : 1
         * winnertime : 1
         * icon : 1
         * phone : 1
         */

        private String gname;
        private String crowdfid;
        private int allcount;
        private int invitecount;
        private String cost;
        private String winnertime;
        private String icon;
        private String phone;

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

        public int getAllcount() {
            return allcount;
        }

        public void setAllcount(int allcount) {
            this.allcount = allcount;
        }

        public int getInvitecount() {
            return invitecount;
        }

        public void setInvitecount(int invitecount) {
            this.invitecount = invitecount;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getWinnertime() {
            return winnertime;
        }

        public void setWinnertime(String winnertime) {
            this.winnertime = winnertime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
