package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.adapter.viewHolder.CreateBannerHolder;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.ui.fragment.GoodsFragment;
import com.chinayiz.chinayzy.views.GlideCircleTransform;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/16 17:03
 * Class Goods_One
 */
public class Goods_Presenter extends BasePresenter<GoodsFragment> implements PullToRefreshLayout.OnRefreshListener {
    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    private PullToRefreshLayout mRefreshLayout;

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (EventMessage.NET_EVENT == message.getEventType()) {//请求成功
            disposeNetMsg(message);
        }else if (EventMessage.REQUEST_ERROR==message.getEventType()){//请求失败
            switch (message.getDataType()){
                case Commons.GOODS_DETAIL:{
                    Logger.i("请求失败");
                    if (mRefreshLayout != null) {
                        mRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                    }
                    break;
                }
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (EventMessage.INFORM_EVENT == message.getEventType()) {
            disposeInfoMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.GOODS_DETAIL: {
                GoodsDetailModel model = (GoodsDetailModel) message.getData();
                showGoodsInfo(model);
                if (mRefreshLayout != null) {
                    mRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
                break;
            }
            case Commons.GOODS_GROUP: {

                break;
            }
            case Commons.GOODS_PICDETAIL: {

                break;
            }
            case Commons.GOODS_RELATED: {

                break;
            }
        }
    }

    private void showGoodsInfo(GoodsDetailModel model) {
        //设置banner图
        List<String> urls = new ArrayList<>();
        for (String str : model.getData().getGpic().split(",")) {
            urls.add(str);
        }
        mView.goodsCode=model.getData().getItemcode();
        mView.storeID=String.valueOf(model.getData().getShopid());
        mView.mGoodsHolder.mVpagerBanner.setPages(new CreateBannerHolder(), urls);
        mView.mGoodsHolder.mTvGoodsTitle.setText(model.getData().getGname());
        mView.mGoodsHolder.mTvGoodsPrice.setText(model.getData().getPrice());
        mView.mGoodsHolder.mTvGoodsPostage.setText("运费："+model.getData().getCarriage()+"元");
        mView.mGoodsHolder.mTvGoodsSales.setText("月销量："+model.getData().getSalesvolume());
        mView.mGoodsHolder.mTvGoodsOrigin.setText(model.getData().getProductarea());
        String[]  mService=model.getData().getService().split(",");
        for (int i=0;i<mService.length;i++){
            mView.mGoodsHolder.mServiceList.get(i).mIvService.setVisibility(View.VISIBLE);
            mView.mGoodsHolder.mServiceList.get(i).mTvService.setVisibility(View.VISIBLE);
            mView.mGoodsHolder.mServiceList.get(i).mTvService.setText(mService[i]);
        }
        mView.mGoodsHolder.mTvCommentCount.setText("（"+String.valueOf(model.getData().getCommentnum())+"条评论）");
        int sum=0;
        if (model.getData().getCommentnum()!=0){//判断商品是否有评论
            sum=model.getData().getCommentlist().get(0).getDescpoint()+
                    model.getData().getCommentlist().get(0).getDeliverypoint()+
                    model.getData().getCommentlist().get(0).getServicepoint();
            Logger.i("评分总分：="+sum);
            mView.mGoodsHolder.mRbGoodsGrade.setRating(sum/3);//设置综合分数
            Glide.with(mView).load(model.getData().getCommentlist().get(0).getPic())
                    .transform(new GlideCircleTransform(mView.getActivity()))
                    .into(mView.mGoodsHolder.mIvUserIcon);
            String userName=model.getData().getCommentlist().get(0).getNickname();
            if ("0".equals(model.getData().getCommentlist().get(0).getIsanonymity())){
                mView.mGoodsHolder.mTvUserName.setText(userName);
            }else {
                char[] chars=userName.toCharArray();
                userName=chars[0]+"***"+chars[chars.length-1];
                mView.mGoodsHolder.mTvUserName.setText(userName);
            }
            mView.mGoodsHolder.mTvCommentContent.setText(model.getData().getCommentlist().get(0).getCommentscontent());

        }
        Glide.with(mView).load(model.getData().getSpic())
                .transform(new GlideCircleTransform(mView.getActivity()))
                .into(mView.mGoodsHolder.mIvStoreLogo);
        mView.mGoodsHolder.mTvStoreName.setText(model.getData().getSname());
        if ("0".equals(model.getData().getIsself())){//是否是自营店铺
            mView.mGoodsHolder.mIvStoreType.setVisibility(View.GONE);
        }
        mView.mGoodsHolder.mTvGoodsCount.setText(String.valueOf(model.getData().getGoodsnum()));
        mView.mGoodsHolder.mTvFansCount.setText(String.valueOf(model.getData().getCollectnum()));

        if ("1".equals(model.getData().getIscollect())){
            mView.mRbFavorite.setChecked(true);
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        if (pullToRefreshLayout!=null){
            mRefreshLayout = pullToRefreshLayout;
        }
        mRequestUtils.getGoodsDetail(mView.goodsID);
    }

    /**
     * 收藏商品
     */
    public void doCollect() {
        mRequestUtils.doCollectGoods(mView.goodsID);
    }

    /**
     * 取消收藏
     */
    public void doUnCollect() {
        mRequestUtils.doUnCollectGoods(mView.goodsID);
    }
    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

    }
}
