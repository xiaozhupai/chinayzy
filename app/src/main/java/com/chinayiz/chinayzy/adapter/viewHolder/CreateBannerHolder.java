package com.chinayiz.chinayzy.adapter.viewHolder;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/20 15:31
 * Class CreateBannerHolder banner图适配器创建接口
 */
public class CreateBannerHolder implements CBViewHolderCreator<BannerHolder> {
    @Override
    public BannerHolder createHolder() {
        return new BannerHolder();
    }
}
