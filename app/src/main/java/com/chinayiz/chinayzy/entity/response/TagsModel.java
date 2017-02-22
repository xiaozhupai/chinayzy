package com.chinayiz.chinayzy.entity.response;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.  获取个性标签
 */

public class TagsModel extends BaseResponseModel {

    /**
     * data : {"taglist":[{"tagid":1,"tagtype":"1","isxz":"1","tagname":"欧美"},{"tagid":2,"tagtype":"1","isxz":"1","tagname":"日系"},{"tagid":3,"tagtype":"1","isxz":"1","tagname":"韩系"},{"tagid":4,"tagtype":"1","isxz":"0","tagname":"混搭"},{"tagid":5,"tagtype":"1","isxz":"0","tagname":"暗黑"}],"tagids":"1,2,3","tag":"嘿嘿,嘿嘿"}
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
         * taglist : [{"tagid":1,"tagtype":"1","isxz":"1","tagname":"欧美"},{"tagid":2,"tagtype":"1","isxz":"1","tagname":"日系"},{"tagid":3,"tagtype":"1","isxz":"1","tagname":"韩系"},{"tagid":4,"tagtype":"1","isxz":"0","tagname":"混搭"},{"tagid":5,"tagtype":"1","isxz":"0","tagname":"暗黑"}]
         * tagids : 1,2,3
         * tag : 嘿嘿,嘿嘿
         */

        private String tagids;
        private String tag;
        private List<TaglistBean> taglist;

        public String getTagids() {
            return tagids;
        }

        public void setTagids(String tagids) {
            this.tagids = tagids;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<TaglistBean> getTaglist() {
            return taglist;
        }

        public void setTaglist(List<TaglistBean> taglist) {
            this.taglist = taglist;
        }

        public static class TaglistBean {
            /**
             * tagid : 1
             * tagtype : 1
             * isxz : 1
             * tagname : 欧美
             */

            private int tagid;
            private String tagtype;
            private String isxz;
            private String tagname;

            public int getTagid() {
                return tagid;
            }

            public void setTagid(int tagid) {
                this.tagid = tagid;
            }

            public String getTagtype() {
                return tagtype;
            }

            public void setTagtype(String tagtype) {
                this.tagtype = tagtype;
            }

            public String getIsxz() {
                return isxz;
            }

            public void setIsxz(String isxz) {
                this.isxz = isxz;
            }

            public String getTagname() {
                return tagname;
            }

            public void setTagname(String tagname) {
                this.tagname = tagname;
            }
        }
    }
}
