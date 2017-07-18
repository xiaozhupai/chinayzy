package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

/**
 * Created by Administrator on 2017/7/17.
 */

public class ShoppingCarCountModel extends BaseResponseModel {

    /**
     * data : 10
     */

    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ShoppingCarCountModel{" +
                "data=" + data +
                '}';
    }
}
