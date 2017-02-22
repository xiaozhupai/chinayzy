package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/18 10:00
 * Class CommentListModel 评论列表
 */
public class CommentListModel extends BaseResponseModel {

    /**
     * data : {"commentlist":[{"isanonymity":"1","createtime":"2017-02-15 19:09:23","servicepoint":1,"commentscontent":"买来下火锅，好久没尝到这么鲜美的食物了","nickname":"bbb","cpic":"2.jpg,1.jpg","descpoint":3,"pic":"http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100","cid":3,"deliverypoint":1}],"count":1}
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
         * commentlist : [{"isanonymity":"1","createtime":"2017-02-15 19:09:23","servicepoint":1,"commentscontent":"买来下火锅，好久没尝到这么鲜美的食物了","nickname":"bbb","cpic":"2.jpg,1.jpg","descpoint":3,"pic":"http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100","cid":3,"deliverypoint":1}]
         * count : 1
         */

        private int count;
        private List<CommentlistBean> commentlist;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<CommentlistBean> getCommentlist() {
            return commentlist;
        }

        public void setCommentlist(List<CommentlistBean> commentlist) {
            this.commentlist = commentlist;
        }

        public static class CommentlistBean {
            /**
             * isanonymity : 1
             * createtime : 2017-02-15 19:09:23
             * servicepoint : 1
             * commentscontent : 买来下火锅，好久没尝到这么鲜美的食物了
             * nickname : bbb
             * cpic : 2.jpg,1.jpg
             * descpoint : 3
             * pic : http://qzapp.qlogo.cn/qzapp/1105981066/A3CA2E1D16ADDB5EA1AA7C4649E1E426/100
             * cid : 3
             * deliverypoint : 1
             */

            private String isanonymity;
            private String createtime;
            private int servicepoint;
            private String commentscontent;
            private String nickname;
            private String cpic;
            private int descpoint;
            private String pic;
            private int cid;
            private int deliverypoint;

            public String getIsanonymity() {
                return isanonymity;
            }

            public void setIsanonymity(String isanonymity) {
                this.isanonymity = isanonymity;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getServicepoint() {
                return servicepoint;
            }

            public void setServicepoint(int servicepoint) {
                this.servicepoint = servicepoint;
            }

            public String getCommentscontent() {
                return commentscontent;
            }

            public void setCommentscontent(String commentscontent) {
                this.commentscontent = commentscontent;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getCpic() {
                return cpic;
            }

            public void setCpic(String cpic) {
                this.cpic = cpic;
            }

            public int getDescpoint() {
                return descpoint;
            }

            public void setDescpoint(int descpoint) {
                this.descpoint = descpoint;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getDeliverypoint() {
                return deliverypoint;
            }

            public void setDeliverypoint(int deliverypoint) {
                this.deliverypoint = deliverypoint;
            }

            @Override
            public String toString() {
                return "CommentlistBean{" +
                        "cid=" + cid +
                        ", isanonymity='" + isanonymity + '\'' +
                        ", createtime='" + createtime + '\'' +
                        ", servicepoint=" + servicepoint +
                        ", commentscontent='" + commentscontent + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", cpic='" + cpic + '\'' +
                        ", descpoint=" + descpoint +
                        ", pic='" + pic + '\'' +
                        ", deliverypoint=" + deliverypoint +
                        '}';
            }
        }
    }
}
