package com.chinayiz.chinayzy.entity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/30 15:02
 * Class AppUpdataInfo
 */

public class AppUpdataInfo implements Parcelable {
    /**
     * createtime : 2017-01-18 16:06:42
     * isforce : 0
     * needupdate : 0
     * versiondesc : null
     * buildcode : 1
     * versioncode : 1.0
     * url : null
     */
    private String createtime;
    private String isforce;
    private String needupdate;
    private String versiondesc;
    private String buildcode;
    private String versioncode;
    private String url;

    public AppUpdataInfo(String createtime, String isforce, String needupdate, String versiondesc, String buildcode, String versioncode, String url) {
        this.createtime = createtime;
        this.isforce = isforce;
        this.needupdate = needupdate;
        this.versiondesc = versiondesc;
        this.buildcode = buildcode;
        this.versioncode = versioncode;
        this.url = url;
    }

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
                ", versiondesc=" + versiondesc +
                ", buildcode='" + buildcode + '\'' +
                ", versioncode='" + versioncode + '\'' +
                ", url=" + url +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createtime);
        dest.writeString(this.isforce);
        dest.writeString(this.needupdate);
        dest.writeString(this.versiondesc);
        dest.writeString(this.buildcode);
        dest.writeString(this.versioncode);
        dest.writeString(this.url);
    }

    public AppUpdataInfo() {
    }

    protected AppUpdataInfo(Parcel in) {
        this.createtime = in.readString();
        this.isforce = in.readString();
        this.needupdate = in.readString();
        this.versiondesc = in.readString();
        this.buildcode = in.readString();
        this.versioncode = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<AppUpdataInfo> CREATOR = new Parcelable.Creator<AppUpdataInfo>() {
        @Override
        public AppUpdataInfo createFromParcel(Parcel source) {
            return new AppUpdataInfo(source);
        }

        @Override
        public AppUpdataInfo[] newArray(int size) {
            return new AppUpdataInfo[size];
        }
    };
}
