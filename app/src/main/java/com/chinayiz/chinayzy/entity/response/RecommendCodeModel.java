package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/10 16:14
 * Class RecommendCodeModel
 */

public class RecommendCodeModel extends BaseResponseModel {

    /**
     * data : {"message":"【中国亿众】啦啦啦啦啦推荐您加入中国亿众，推荐码为CNYZ-YUANKUNKUN-12345","content":"您的好友啦啦啦啦啦对您发出邀请，邀请加入中国亿众","title":"中国亿众","nickname":"啦啦啦啦啦","userid":15,"image":"http://chinayiz.cn/v1/2017/0407/415ab22a-6aa0-4739-9ada-c3d314bf6f0d.png","codepic":"http://chinayiz.cn/v1/2017/0410/c6a4a616-d222-485f-a450-560534f1b219.png","pid":"CNYZ-YUANKUNKUN-12345","webpageUrl":"http://192.168.1.8:8081/yzyProduct/v1/api/h5/fxtuijianma?userid=15"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RecommendCodeModel{" +
                "data=" + data +
                '}';
    }

    public  class DataBean {
        /**
         * message : 【中国亿众】啦啦啦啦啦推荐您加入中国亿众，推荐码为CNYZ-YUANKUNKUN-12345
         * content : 您的好友啦啦啦啦啦对您发出邀请，邀请加入中国亿众
         * title : 中国亿众
         * nickname : 啦啦啦啦啦
         * userid : 15
         * image : http://chinayiz.cn/v1/2017/0407/415ab22a-6aa0-4739-9ada-c3d314bf6f0d.png
         * codepic : http://chinayiz.cn/v1/2017/0410/c6a4a616-d222-485f-a450-560534f1b219.png
         * pid : CNYZ-YUANKUNKUN-12345
         * webpageUrl : http://192.168.1.8:8081/yzyProduct/v1/api/h5/fxtuijianma?userid=15
         */

        private String message;
        private String content;
        private String title;
        private String nickname;
        private String userid;
        private String image;
        private String codepic;
        private String pid;
        private String webpageUrl;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

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

        @Override
        public String toString() {
            return "DataBean{" +
                    "message='" + message + '\'' +
                    ", content='" + content + '\'' +
                    ", title='" + title + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", userid='" + userid + '\'' +
                    ", image='" + image + '\'' +
                    ", codepic='" + codepic + '\'' +
                    ", pid='" + pid + '\'' +
                    ", webpageUrl='" + webpageUrl + '\'' +
                    '}';
        }
    }
}
