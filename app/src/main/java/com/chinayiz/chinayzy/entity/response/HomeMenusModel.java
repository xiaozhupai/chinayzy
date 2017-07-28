package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/10 14:53
 * Class HomeMenusModel
 */

public class HomeMenusModel extends BaseResponseModel {

    /**
     * data : {"tubiaolist":[{"syname":"生态农业","sytitle":"生态农业","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0511/f293323a-9f6b-4f75-97bd-6d5e6b23fa05.png","sylink":null,"sytype":"1"},{"syname":"亿众商城","sytitle":"亿众商城","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0511/281a6e93-668a-4378-b736-067951471d7b.png","sylink":null,"sytype":"1"},{"syname":"关于我们","sytitle":"关于我们","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0511/e0240aaa-0756-4d7c-8773-c6ee019a9a88.png","sylink":null,"sytype":"1"},{"syname":"我的二维码","sytitle":"我的二维码","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0511/9afeb61d-ce53-4e65-8e75-62079be9f2d9.png","sylink":null,"sytype":"1"}],"lanweilist":[{"syname":"世界硒都","sytitle":"世界硒都","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0720/05a9a5e3-5c4e-4d79-a1d6-e3949c7e1d2c.png","sylink":null,"sytype":"2"},{"syname":"有机农业","sytitle":"有机农业","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0720/ed83b23d-908e-4b91-b8e8-df589a174294.png","sylink":null,"sytype":"2"},{"syname":"吃货必备","sytitle":"吃货必备","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0720/206afaa7-8a24-4182-a7a7-e1fd35fc5f8f.png","sylink":null,"sytype":"2"},{"syname":"爱时尚","sytitle":"爱时尚","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0720/ce516802-3ec7-452b-b828-d982246e4fb5.png","sylink":null,"sytype":"2"},{"syname":"为你推荐","sytitle":"为你推荐","sypic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0720/b06d79c9-1466-45f8-9558-982e97b2471e.png","sylink":null,"sytype":"2"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TubiaolistBean> tubiaolist;
        private List<LanweilistBean> lanweilist;

        public List<TubiaolistBean> getTubiaolist() {
            return tubiaolist;
        }

        public void setTubiaolist(List<TubiaolistBean> tubiaolist) {
            this.tubiaolist = tubiaolist;
        }

        public List<LanweilistBean> getLanweilist() {
            return lanweilist;
        }

        public void setLanweilist(List<LanweilistBean> lanweilist) {
            this.lanweilist = lanweilist;
        }

        public static class TubiaolistBean {
            /**
             * syname : 生态农业
             * sytitle : 生态农业
             * sypic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0511/f293323a-9f6b-4f75-97bd-6d5e6b23fa05.png
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

        public static class LanweilistBean {
            /**
             * syname : 世界硒都
             * sytitle : 世界硒都
             * sypic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0720/05a9a5e3-5c4e-4d79-a1d6-e3949c7e1d2c.png
             * sylink : null
             * sytype : 2
             */

            private String syname;
            private String sytitle;
            private String sypic;
            private String sylink;
            private String sytype;
            private int sysqnum;

            public int getSysqnum() {
                return sysqnum;
            }

            public void setSysqnum(int sysqnum) {
                this.sysqnum = sysqnum;
            }

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
}
