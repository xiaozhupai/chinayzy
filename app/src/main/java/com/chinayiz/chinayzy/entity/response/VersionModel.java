package com.chinayiz.chinayzy.entity.response;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 14:03
 * Class Version
 */
public class VersionModel {
    public static final int NEW_VERSION=1;//有新版本
    public static final int NO_VERSION=-1;//没有新版本
    public static final int COERCE_VERSION=0;//强制更新版本
    private  int isNewVersion;//判断是否有新版本
    private int newVersionNumber;//新版本号
    private String updateMessge;//更新说明
    private String updateTitle;//更新窗口标题
    private String downloadUri;//新版本下载地址

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public String getUpdateMessge() {
        return updateMessge;
    }

    public void setUpdateMessge(String updateMessge) {
        this.updateMessge = updateMessge;
    }

    public int getnewVersionNumber() {
        return newVersionNumber;
    }

    public void setnewVersionNumber(int newVersionNumber) {
        this.newVersionNumber = newVersionNumber;
    }

    public int getIsNewVersion() {
        return isNewVersion;
    }

    public void setIsNewVersion(int isNewVersion) {
        this.isNewVersion = isNewVersion;
    }


    @Override
    public String toString() {
        return "Version{" +
                "isNewVersion=" + isNewVersion +
                ", newVersionNumber=" + newVersionNumber +
                ", updateMessge='" + updateMessge + '\'' +
                ", updateTitle='" + updateTitle + '\'' +
                ", downloadUri='" + downloadUri + '\'' +
                '}';
    }


}
