package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/6/19.
 */

public class ShareCrowdModel extends BaseResponseModel {

    /**
     * data : {"title":"中国亿众","nickname":"啦啦啦啦啦","content":"您的好友啦啦啦啦啦对您发出邀请，邀请加入中国亿众","userid":15,"image":"http://chinayiz.cn/v1/2017/0407/415ab22a-6aa0-4739-9ada-c3d314bf6f0d.png","codepic":"http://chinayiz.cn/v1/2017/0410/c6a4a616-d222-485f-a450-560534f1b219.png","pid":"CNYZ-YUANKUNKUN-12345","webpageUrl":"http://192.168.200.8:8081/yzyProduct/v1/api/h5/fxtuijianma?userid=15"}
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
         * title : 中国亿众
         * nickname : 啦啦啦啦啦
         * content : 您的好友啦啦啦啦啦对您发出邀请，邀请加入中国亿众
         * userid : 15
         * image : http://chinayiz.cn/v1/2017/0407/415ab22a-6aa0-4739-9ada-c3d314bf6f0d.png
         * codepic : http://chinayiz.cn/v1/2017/0410/c6a4a616-d222-485f-a450-560534f1b219.png
         * pid : CNYZ-YUANKUNKUN-12345
         * webpageUrl : http://192.168.200.8:8081/yzyProduct/v1/api/h5/fxtuijianma?userid=15
         */

        private String title;
        private String nickname;
        private String content;
        private String userid;
        private String image;
        private String codepic;
        private String pid;
        private String webpageUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCodepic() {
            return codepic;
        }

        public void setCodepic(String codepic) {
            this.codepic = codepic;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getWebpageUrl() {
            return webpageUrl;
        }

        public void setWebpageUrl(String webpageUrl) {
            this.webpageUrl = webpageUrl;
        }
    }
}
