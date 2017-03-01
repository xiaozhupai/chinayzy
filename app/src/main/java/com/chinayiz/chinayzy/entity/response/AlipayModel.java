package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/3/1.
 */

public class AlipayModel extends BaseResponseModel {

    /**
     * linkString : “dsadsadsaaddsa”
     */

    private String linkString;

    public String getLinkString() {
        return linkString;
    }

    public void setLinkString(String linkString) {
        this.linkString = linkString;
    }
}
