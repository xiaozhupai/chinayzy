package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/8 10:14
 * Class StoreGoodsListModel
 */
public class StoreGoodsListModel extends BaseResponseModel {

    private List<StoreInfoModel.DataBean.GoodsBean> data;

    public List<StoreInfoModel.DataBean.GoodsBean> getData() {
        return data;
    }
    public void setData(List<StoreInfoModel.DataBean.GoodsBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StoreGoodsListModel{" +
                "data=" + data +
                '}';
    }
}
