package com.chinayiz.chinayzy.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chinayiz.chinayzy.MainActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.ActionBarControlModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.chinayiz.chinayzy.ui.activity.NongYeMainActivity.NYMAIN_ACTIONBAR;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 10:57
 * Class MainPresenter
 */
public class NongYeMainPresenter extends BasePresenter<NongYeMainActivity> {
    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case NYMAIN_ACTIONBAR:{
                ActionBarControlModel model= (ActionBarControlModel) message.getData();
                if (model.showAndHide!=-1){
                   mView.setAcctionBar(model.showAndHide);
                }
                if (model.showAndHide!=NongYeMainActivity.HIDE_ALL||model.showAndHide!=NongYeMainActivity.HIDE_ACTIONBAR){
                    if (model.title!=null&&model.title!=""){
                        setActionBar(model.back,model.more,model.edit,model.car,model.title);
                    }else {
                        setActionBar(model.back,model.more,model.edit,model.car);
                    }
                }
                break;
            }
        }
    }

    /**
     * 控制ActionBar按钮的显示或隐藏 1：显示 0：隐藏
     * @param back 返回按钮
     * @param more 更多按钮
     * @param edit 编辑按钮
     * @param car 购物车按钮
     */
    private void setActionBar(int back,int more,int edit,int car){
        if (back==1){mView.mIvBackButton.setVisibility(View.VISIBLE);
        }else {mView.mIvBackButton.setVisibility(View.GONE);}
        if (more==1){mView.mIvActionBarMore.setVisibility(View.VISIBLE);
        }else {mView.mIvActionBarMore.setVisibility(View.GONE);}
        if (edit==1){mView.mCbActionBarEdit.setVisibility(View.VISIBLE);
        }else { mView.mCbActionBarEdit.setVisibility(View.GONE);}
        if (car==1){mView.mIvActionBarCart.setVisibility(View.VISIBLE);
        }else { mView.mIvActionBarCart.setVisibility(View.GONE);}
    }

    /**
     *  控制ActionBar按钮的显示或隐藏 1：显示 0：隐藏
     * @param back 返回按钮
     * @param more 更多按钮
     * @param edit 编辑按钮
     * @param car 购物车按钮
     * @param title 页面标题
     */
    private void setActionBar(int back,int more,int edit,int car,String title){
        setActionBar(back,more,edit,car);
        mView.mTvActionBarTitle.setText(title);
    }

    /**
     * 路由模式处理开始消息，以此控制actionbar
     * @param franame fragment 类名
     */
    private void routerStart(String franame) {

    }


    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){

        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType()==EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }
    }

    @Override
    public void onCreate() {
        mView.getActivity();
    }

    @Override
    public void onDestroy() {
        mView.getFragmentManager().removeOnBackStackChangedListener(mView);
        mView.mHomeFragment = null;
        mView.mFindFragment = null;
        mView.mActivityFragment = null;
        mView.mCartFragment = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 启动商城首页
     */
    public void doStart_MainPager() {
        Intent intent = new Intent(mView, MainActivity.class);
        mView.startActivity(intent);
    }

}
