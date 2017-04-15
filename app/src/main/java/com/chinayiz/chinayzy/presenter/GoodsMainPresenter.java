package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NewGoodsDetailModel;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.common.GoodsDetailFragment;
import com.chinayiz.chinayzy.ui.common.GoodsMainFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashSet;
import java.util.Set;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/13 13:48
 * Class GoodsMainPresenter
 */

public class GoodsMainPresenter extends BasePresenter<GoodsMainFragment> {
    public CommonRequestUtils mRequestUtils;
    public NewGoodsDetailModel model = null;
    public Set<String> isFirst=new HashSet<>();
    private RelatedGoodsModel mRelatedGoodsModel;

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.GOODS_DETAIL: {//商品详情简要信息
                if (isFirst.contains( Commons.GOODS_DETAIL)){
                    break;
                }
                isFirst.add(Commons.GOODS_DETAIL);
                model = (NewGoodsDetailModel) message.getData();
                NewGoodsDetailModel.DataBean data=model.getData();
                mView.storeID = data.getShopid();
                mView.mDetailFragment.setData(model);
                mView.mCommentsFragment.setCoun(Integer.parseInt(data.getCommentnum()));
                mView.mPicDetailFragment.setGoodsid(mView.goodsID,data.getItemcode());
                Logger.e("启动次数"+mView.startSum);
                break;
            }
            case Commons.COMMENT_LIST: {//评论列表
                if (isFirst.contains( Commons.COMMENT_LIST)){
                    break;
                }
                isFirst.add(Commons.COMMENT_LIST);
                mView.mCommentsFragment.setCommentData(message);
                break;
            }
            case Commons.GOODS_RELATED: {//相关商品
                if (isFirst.contains( Commons.GOODS_RELATED)){
                    break;
                }
                isFirst.add(Commons.GOODS_RELATED);
                mRelatedGoodsModel = (RelatedGoodsModel) message.getData();
                mView.mDetailFragment.setRelatGoodsList(mRelatedGoodsModel);
                mView.mPicDetailFragment.setRelatGoodsList(mRelatedGoodsModel);
                break;
            }
            case GoodsDetailFragment.CLICK_COMMENTS:{//切换到评论列表
                mView.onCheckedChanged(null, R.id.rb_tabComments);
                break;
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()) {
            case GoodsDetailGridAdpter.CLICK_GOODS: {
                String goodsID = message.getData().toString();
                Skip.toNewGoodsDetail(mView.getActivity(), goodsID);
                break;
            }

        }
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(mView.getActivity(), "未知错误，请重试");
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }
}
