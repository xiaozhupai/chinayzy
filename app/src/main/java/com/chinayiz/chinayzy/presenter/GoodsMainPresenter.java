package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NewGoodsDetailModel;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.entity.response.ShoppingCarCountModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.common.GoodsDetailFragment;
import com.chinayiz.chinayzy.ui.common.GoodsMainFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/13 13:48
 * Class GoodsMainPresenter
 */

public class GoodsMainPresenter extends BasePresenter<GoodsMainFragment> {
    public CommonRequestUtils mRequestUtils;
    public NewGoodsDetailModel model = null;
    private RelatedGoodsModel mRelatedGoodsModel;
    public int count;

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.GOODS_DETAIL: {//商品详情简要信息
                model = (NewGoodsDetailModel) message.getData();
                NewGoodsDetailModel.DataBean data=model.getData();
                mView.storeID = data.getShopid();
                mView.mDetailFragment.setData(model);
                mView.mCommentsFragment.setCoun(Integer.parseInt(data.getCommentnum()));
                mView.mPicDetailFragment.setGoodsid(mView.goodsID,data.getItemcode());
                mView.mPregess.setVisibility(View.GONE);
                mView.setFavorite(model.getData().getIscollect());
                break;
            }
            case Commons.COMMENT_LIST: {//评论列表
                mView.mCommentsFragment.setCommentData(message);
                break;
            }
            case Commons.GOODS_RELATED: {//相关商品
                mRelatedGoodsModel = (RelatedGoodsModel) message.getData();
                mView.mDetailFragment.setRelatGoodsList(mRelatedGoodsModel);
                mView.mPicDetailFragment.setRelatGoodsList(mRelatedGoodsModel);
                break;
            }
            case GoodsDetailFragment.CLICK_COMMENTS:{//切换到评论列表
                mView.onCheckedChanged(null, R.id.rb_tabComments);
                break;
            }
            case Commons.SHOPPINGCARTCOUNT: //购物车小红点数量
                ShoppingCarCountModel model5= (ShoppingCarCountModel) message.getData();
                if (mView.goodsType==mView.COMMON){
                    count= model5.getData();
                    mView.getCount(count);
                }
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()) {
            case GoodsDetailGridAdpter.CLICK_GOODS: {
                String goodsID = message.getData().toString();
                Skip.toNewGoodsDetail(mView.getActivity(), goodsID, GoodsMainFragment.COMMON);
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
