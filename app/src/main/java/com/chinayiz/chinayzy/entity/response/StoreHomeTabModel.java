package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/6/5 15:51
 * Class StoreHomeTabModel
 */

public class StoreHomeTabModel extends BaseResponseModel {

    /**
     * data : {"toplist":[{"itemname":"首页","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0420/a3740b9d-9aa1-46a9-b0c7-a6165efd1b4b.jpg","detaillink":"1","itemcode":"017001"},{"itemname":"美妆","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/bfb146f2-6a6e-435b-9d03-105c2b8e88a7.png","detaillink":"2","itemcode":"016001"},{"itemname":"母婴","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/88e7c411-5099-4f20-823f-b7afbe35ad4e.png","detaillink":"3","itemcode":"016002"},{"itemname":"全球购","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/d4dc22c8-6989-4be1-84a7-163231792203.png","detaillink":"4","itemcode":"016003"},{"itemname":"食品","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/bc1cc226-49d2-4ca3-bf56-674aeb63fdac.png","detaillink":"5","itemcode":"016004"},{"itemname":"服饰","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/b2e3bf5b-e72c-4b31-8e05-6e0b0483b95b.png","detaillink":"6","itemcode":"016005"},{"itemname":"数码","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/91909cfa-218f-49ec-bdf5-c0e96e917a61.png","detaillink":"7","itemcode":"016006"},{"itemname":"美容","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/defe211f-4191-41a5-9429-e935af2740ff.png","detaillink":"8","itemcode":"016007"}],"tubiaolist":[{"itemname":"美妆","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/bfb146f2-6a6e-435b-9d03-105c2b8e88a7.png","detaillink":"2","itemcode":"016001"},{"itemname":"母婴","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/88e7c411-5099-4f20-823f-b7afbe35ad4e.png","detaillink":"3","itemcode":"016002"},{"itemname":"全球购","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/d4dc22c8-6989-4be1-84a7-163231792203.png","detaillink":"4","itemcode":"016003"},{"itemname":"食品","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/bc1cc226-49d2-4ca3-bf56-674aeb63fdac.png","detaillink":"5","itemcode":"016004"},{"itemname":"服饰","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/b2e3bf5b-e72c-4b31-8e05-6e0b0483b95b.png","detaillink":"6","itemcode":"016005"},{"itemname":"数码","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/91909cfa-218f-49ec-bdf5-c0e96e917a61.png","detaillink":"7","itemcode":"016006"},{"itemname":"美容","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/defe211f-4191-41a5-9429-e935af2740ff.png","detaillink":"8","itemcode":"016007"},{"itemname":"更多分类","typepic":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/6c76a6ba-3af6-46ff-9cef-450f331b4c09.png","detaillink":"9","itemcode":"018001"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ToplistBean> toplist;
        private List<TubiaolistBean> tubiaolist;

        public List<ToplistBean> getToplist() {
            return toplist;
        }

        public void setToplist(List<ToplistBean> toplist) {
            this.toplist = toplist;
        }

        public List<TubiaolistBean> getTubiaolist() {
            return tubiaolist;
        }

        public void setTubiaolist(List<TubiaolistBean> tubiaolist) {
            this.tubiaolist = tubiaolist;
        }

        public static class ToplistBean {
            /**
             * itemname : 首页
             * typepic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/m1/2017/0420/a3740b9d-9aa1-46a9-b0c7-a6165efd1b4b.jpg
             * detaillink : 1
             * itemcode : 017001
             */

            private String itemname;
            private String typepic;
            private String detaillink;
            private String itemcode;

            public String getItemname() {
                return itemname;
            }

            public void setItemname(String itemname) {
                this.itemname = itemname;
            }

            public String getTypepic() {
                return typepic;
            }

            public void setTypepic(String typepic) {
                this.typepic = typepic;
            }

            public String getDetaillink() {
                return detaillink;
            }

            public void setDetaillink(String detaillink) {
                this.detaillink = detaillink;
            }

            public String getItemcode() {
                return itemcode;
            }

            public void setItemcode(String itemcode) {
                this.itemcode = itemcode;
            }
        }

        public static class TubiaolistBean {
            /**
             * itemname : 美妆
             * typepic : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/v1/2017/0601/bfb146f2-6a6e-435b-9d03-105c2b8e88a7.png
             * detaillink : 2
             * itemcode : 016001
             */

            private String itemname;
            private String typepic;
            private String detaillink;
            private String itemcode;

            public String getItemname() {
                return itemname;
            }

            public void setItemname(String itemname) {
                this.itemname = itemname;
            }

            public String getTypepic() {
                return typepic;
            }

            public void setTypepic(String typepic) {
                this.typepic = typepic;
            }

            public String getDetaillink() {
                return detaillink;
            }

            public void setDetaillink(String detaillink) {
                this.detaillink = detaillink;
            }

            public String getItemcode() {
                return itemcode;
            }

            public void setItemcode(String itemcode) {
                this.itemcode = itemcode;
            }
        }
    }
}
