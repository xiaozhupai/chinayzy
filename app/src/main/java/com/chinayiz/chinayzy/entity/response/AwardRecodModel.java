package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */

public class AwardRecodModel extends BaseResponseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-06-16 10:45:59
         * icon : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0427/c69d0c26-baf8-40e4-bddf-6cfe82c001c9.jpg
         * winvitecount : 2
         * phone : 187****7579
         * joincount : 2
         * goodsid : 183
         * allcount : 20
         * gname : EDGER 2017新款韩版女鞋高跟鞋女细跟单鞋   原价1250元 现价437元
         * ctype : 2
         * crowdfid : 1
         * type : 3
         * invitecount : 40
         */

        private String createtime;
        private String icon;
        private int winvitecount;
        private String phone;
        private int joincount;
        private String goodsid;
        private int allcount;
        private String gname;
        private String ctype;
        private String crowdfid;
        private String type;
        private int invitecount;
        private String plan;
        private String webpageUrl;

        public String getWebpageUrl() {
            return webpageUrl;
        }

        public void setWebpageUrl(String webpageUrl) {
            this.webpageUrl = webpageUrl;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getWinvitecount() {
            return winvitecount;
        }

        public void setWinvitecount(int winvitecount) {
            this.winvitecount = winvitecount;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getJoincount() {
            return joincount;
        }

        public void setJoincount(int joincount) {
            this.joincount = joincount;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public int getAllcount() {
            return allcount;
        }

        public void setAllcount(int allcount) {
            this.allcount = allcount;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getCtype() {
            return ctype;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public String getCrowdfid() {
            return crowdfid;
        }

        public void setCrowdfid(String crowdfid) {
            this.crowdfid = crowdfid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getInvitecount() {
            return invitecount;
        }

        public void setInvitecount(int invitecount) {
            this.invitecount = invitecount;
        }
    }
}
