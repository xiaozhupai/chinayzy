package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/30 14:55
 * Class AppUpdataModel  应用更新Model
 */

public class AppUpdataModel extends BaseResponseModel {

    /**
     * data : {"createtime":"2017-03-30 15:34:44","isforce":"0","needupdate":1,"versiondesc":"【更新说明】 \n- 聊天时点击图片可直接编辑并发送，无需保存到本地；\n- 可将手机中的连拍照片生成动态图，发表到说说；\n- 短视频拍摄时间延长至10秒，精彩场景记录更完整；\n- 选择多条消息进行转发时，可逐条转发或合并转发。","buildcode":2,"versioncode":"1.1","url":"http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/chinayzy.apk"}
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
        return "AppUpdataModel{" +
                "data=" + data +
                '}';
    }

    public  class DataBean {
        /**
         * createtime : 2017-03-30 15:34:44
         * isforce : 0
         * needupdate : 1
         * versiondesc : 【更新说明】
         - 聊天时点击图片可直接编辑并发送，无需保存到本地；
         - 可将手机中的连拍照片生成动态图，发表到说说；
         - 短视频拍摄时间延长至10秒，精彩场景记录更完整；
         - 选择多条消息进行转发时，可逐条转发或合并转发。
         * buildcode : 2
         * versioncode : 1.1
         * url : http://yzy-app-img.oss-cn-shanghai.aliyuncs.com/chinayzy.apk
         */

        private String createtime;
        private String isforce;
        private String needupdate;
        private String versiondesc;
        private String buildcode;
        private String versioncode;
        private String url;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIsforce() {
            return isforce;
        }

        public void setIsforce(String isforce) {
            this.isforce = isforce;
        }

        public String getNeedupdate() {
            return needupdate;
        }

        public void setNeedupdate(String needupdate) {
            this.needupdate = needupdate;
        }

        public String getVersiondesc() {
            return versiondesc;
        }

        public void setVersiondesc(String versiondesc) {
            this.versiondesc = versiondesc;
        }

        public String getBuildcode() {
            return buildcode;
        }

        public void setBuildcode(String buildcode) {
            this.buildcode = buildcode;
        }

        public String getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(String versioncode) {
            this.versioncode = versioncode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "createtime='" + createtime + '\'' +
                    ", isforce='" + isforce + '\'' +
                    ", needupdate='" + needupdate + '\'' +
                    ", versiondesc='" + versiondesc + '\'' +
                    ", buildcode='" + buildcode + '\'' +
                    ", versioncode='" + versioncode + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
