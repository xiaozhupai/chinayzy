package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/7/18 16:40
 * Class GoodsShareInfoModel
 *  商品分享数据模型
 */

public class GoodsShareInfoModel  extends BaseResponseModel{


    /**
     * data : {"content":"传统饲养，一年仅取一次，无污染，零添加","icon":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0417/bd1d598e-eba1-450c-926e-e9cc5ad30418.jpg","title":"野生蜂蜜","goodsid":47,"gname":"野生蜂蜜","image":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0411/d013b6ef-a677-41b0-a827-1ab0bae0b3a5.png","goodsdesc":"传统饲养，一年仅取一次，无污染，零添加","webpageUrl":"http://192.168.200.47:80/yzyWX/goods/details?type=1"}
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
         * content : 传统饲养，一年仅取一次，无污染，零添加
         * icon : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0417/bd1d598e-eba1-450c-926e-e9cc5ad30418.jpg
         * title : 野生蜂蜜
         * goodsid : 47
         * gname : 野生蜂蜜
         * image : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0411/d013b6ef-a677-41b0-a827-1ab0bae0b3a5.png
         * goodsdesc : 传统饲养，一年仅取一次，无污染，零添加
         * webpageUrl : http://192.168.200.47:80/yzyWX/goods/details?type=1
         */

        private String content;
        private String icon;
        private String title;
        private String goodsid;
        private String gname;
        private String image;
        private String goodsdesc;
        private String webpageUrl;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGoodsdesc() {
            return goodsdesc;
        }

        public void setGoodsdesc(String goodsdesc) {
            this.goodsdesc = goodsdesc;
        }

        public String getWebpageUrl() {
            return webpageUrl;
        }

        public void setWebpageUrl(String webpageUrl) {
            this.webpageUrl = webpageUrl;
        }
    }
}
