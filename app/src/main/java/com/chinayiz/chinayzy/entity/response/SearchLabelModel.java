package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class SearchLabelModel extends BaseResponseModel {

    /**
     * data : {"hotlist":["茶叶","车厘子","大米","有机花菜","乌龙茶","东北大米","萝卜"],"historylist":["水果","茶"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> hotlist;
        private List<String> historylist;

        public List<String> getHotlist() {
            return hotlist;
        }

        public void setHotlist(List<String> hotlist) {
            this.hotlist = hotlist;
        }

        public List<String> getHistorylist() {
            return historylist;
        }

        public void setHistorylist(List<String> historylist) {
            this.historylist = historylist;
        }
    }
}
