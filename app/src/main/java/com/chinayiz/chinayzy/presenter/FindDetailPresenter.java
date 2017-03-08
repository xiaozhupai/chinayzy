package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.graphics.ImageFormat;
import android.os.Bundle;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.KeeporZanModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.activity.LoginActivity;
import com.chinayiz.chinayzy.ui.fragment.find.FindDetailFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static android.content.ContentValues.TAG;

/**发现详情
 * Created by Administrator on 2017/1/3.
 */

public class FindDetailPresenter extends BasePresenter<FindDetailFragment> {
    public UserNet net=UserNet.getNet();
    @Override
    public void onCreate() {
         Net.getNet().getCollectOrPraise(mView.bean.getBid()+"");
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
           if (message.getEventType()==EventMessage.INFORM_EVENT){
               disposeInfoMsg(message);
           }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
              switch (message.getDataType()){
                  case Commons.DIZAN:  //点赞
                     BaseResponseModel model= (BaseResponseModel) message.getData();
                      if (model.getCode().equals("100")){
                          if (mView.isLove){
                              mView.  iv_love.setImageResource(R.mipmap.icon_love_normal);
                              mView.isLove=false;
                          }else {
                              mView.   iv_love.setImageResource(R.mipmap.icon_love_selected);
                              mView.  isLove=true;
                          }
                      }
                      break;
                  case Commons.CANCELCOLLECT:   //收藏
                      BaseResponseModel model2= (BaseResponseModel) message.getData();
                      if (model2.getCode().equals("100")){
                          if (mView.isKeep){
                              mView.iv_keep.setImageResource(R.mipmap.icon_keep);
                              mView.isKeep=false;
                          }else {
                              mView.iv_keep.setImageResource(R.mipmap.icon_keep_selected);
                              mView.isKeep=true;
                          }
                      }
                      break;

                  case Commons.ISCOLLECTORPRAISE:
                      KeeporZanModel keeporZanModel= (KeeporZanModel) message.getData();
                      setData(keeporZanModel.getData());
                      break;
              }
    }

    private void setData(KeeporZanModel.DataBean bean){
        if (bean.getIscollect()!=null){
            if (bean.getIscollect().equals("0")){
                mView.iv_keep.setImageResource(R.mipmap.icon_keep);
                mView.isKeep=false;
            }else {
                mView.iv_keep.setImageResource(R.mipmap.icon_keep_selected);
                mView.isKeep=true;
            }
        }
        if (bean.getIscollect()!=null){
            if (bean.getIspraise().equals("0")){
                mView.iv_love.setImageResource(R.mipmap.icon_love_normal);
                mView.isLove=false;
            }else {
                mView.iv_love.setImageResource(R.mipmap.icon_love_selected);
                mView.isLove=true;
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
              switch (message.getDataType()){
                  case FindDetailFragment.FIND_DETAIL:
                        Logger.i("FIND_DETAIL");
                      Net.getNet().getCollectOrPraise(mView.bean.getBid()+"");
                      break;
              }
    }

    public void toLove(boolean isLove){
        if (UserSeeion.isLogin(mView.getActivity())){
            String love;
            if (isLove){
                love="1";
                Logger.i("点赞");
            }else {
                love="0";
                Logger.i("取消点赞");
            }
            net.getDiZan(mView.bid,love);
        }else {
            toLogin();
        }

    }

    public void toKeep(boolean isKeep){
        if (UserSeeion.isLogin(mView.getActivity())){
            String keep;
            if (isKeep){
                keep="1";
                Logger.i("收藏");
            }else {
                keep="0";
                Logger.i("取消收藏");
            }
            net.getCollection(mView.bid,keep);
        }else {
            toLogin();
        }

    }

    public void toLogin(){
        Intent intent=new Intent(mView.getActivity(), LoginActivity.class);
        intent.putExtra("FindDetailFragment","");
        mView.startActivity(intent);
    }
}
