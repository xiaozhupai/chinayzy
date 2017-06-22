package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ActivityDetailModel;
import com.chinayiz.chinayzy.entity.response.StringModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.flexible.ExchangeYzbFragment;
import com.chinayiz.chinayzy.widget.MessageDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/6/17.
 */

public class ExchangeYzbPresenter extends BasePresenter<ExchangeYzbFragment> {
    private ActivityDetailModel.DataBean bean;
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

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
         switch (message.getDataType()){
             case Commons.CHANGEYZB:
                StringModel model= (StringModel) message.getData();
                 BaseActivity.showToast(mView.getActivity(),model.getMsg());
                 if (model.getCode().equals("100")){//兑换成功
                     final MessageDialog dialog=new MessageDialog(mView.getActivity());
                     dialog.vTitle.setText("兑换成功");
                     dialog.message.setText("请前往“我的亿众”-“我的账户”进行查收");
                     dialog.vButton1.setVisibility(View.GONE);
                     dialog.setButton2("确定", new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dialog.dismiss();
                             mView.mActivity.finish();
                         }
                     });
                     dialog.show();
                 }
                 break;
             case  Commons.WINNERDETAIL:
                ActivityDetailModel bean= (ActivityDetailModel) message.getData();
                 initData(bean.getData());
                 break;
         }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }
    private void initData( ActivityDetailModel.DataBean bean) {
        this.bean=bean;
        Glide.with(mView.getActivity()).load(bean.getIcon()).into(mView.mIvPic);
        mView.mTvTitle.setText(bean.getGname());
        mView.mTvParam1.setText("价值:"+bean.getCost());
        int yzb= (int) (Double.valueOf(bean.getCost())*0.8);
        String exchange="兑换:<font color='#ff3951'>"+yzb+"亿众币</font>";
        mView.mTvParam2.setText(Html.fromHtml(exchange));
        String phone="获奖号码:"+"<font color='#ff3951'> "+bean.getPhone()+"</font>";
        mView.mTvParam3.setText(Html.fromHtml(phone));
        mView.mTvParam4.setText("揭晓时间:"+bean.getWinnertime());
    }
    @Override
    protected void onCreate() {
         CommonRequestUtils.getRequestUtils().getActivityDetail(mView.crowdfid);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    public void submit() {
        final MessageDialog dialog=new MessageDialog(mView.getActivity());
        dialog.setTitle("提示");
        dialog.message.setText("您是否确定兑换亿众币?兑换后的商品将无法退换.");
        dialog.setButton1("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setButton2("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (bean!=null){
                    CommonRequestUtils.getRequestUtils().getChangeyzb(mView.crowdfid,bean.getCost());
                }
            }
        });
        dialog.show();

    }
}
