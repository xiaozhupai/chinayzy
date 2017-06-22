package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ActivityDetailModel;
import com.chinayiz.chinayzy.entity.response.DefaultAddressModel;
import com.chinayiz.chinayzy.entity.response.StringModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.flexible.AddressResultFragment;
import com.chinayiz.chinayzy.widget.MessageDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/6/17.
 */

public class AddressResultPresenter extends BasePresenter<AddressResultFragment> {
    private String addresssid;
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
            case Commons.GETDEFAULTADDRESS:
                DefaultAddressModel model= (DefaultAddressModel) message.getData();
                //运费收货地址
                if (model.getData()!=null){

                    DefaultAddressModel.DataBean  addressBean=model.getData();
                    addresssid=addressBean.getAddressid();
                    mView.mTvAddressName.setText(addressBean.getConsignee());
                    mView.mTvAddressPhone.setText(addressBean.getPhone());
                    mView.mTvAddressText.setText(addressBean.getArea()+addressBean.getAddress());
                    mView.mTvNoAddress.setVisibility(View.INVISIBLE);
                    mView.mTvAddressName.setVisibility(View.VISIBLE);
                    mView.mTvAddressPhone.setVisibility(View.VISIBLE);
                    mView.mTvAddressText.setVisibility(View.VISIBLE);
                }else {
                    mView.mTvAddressName.setVisibility(View.INVISIBLE);
                    mView.mTvAddressPhone.setVisibility(View.INVISIBLE);
                    mView.mTvAddressText.setVisibility(View.INVISIBLE);
                    mView.mTvNoAddress.setVisibility(View.VISIBLE);
                }
                break;
            case Commons.CONFIRMADDRESS:
                StringModel model2= (StringModel) message.getData();
                if (model2.getCode().equals("100")){   //提交成功
                    final MessageDialog dialog=new MessageDialog(mView.getActivity());
                    dialog.message.setText("提交地址之后,商品将在1-7个工作日内发货,您可以在【我的亿众】-【获奖记录】 查看.");
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
                BaseActivity.showToast(mView.getActivity(),model2.getMsg());
                break;
            case  Commons.WINNERDETAIL:
                ActivityDetailModel bean= (ActivityDetailModel) message.getData();
                initData(bean.getData());
                break;
        }

    }

    private void initData( ActivityDetailModel.DataBean bean) {
        this.bean=bean;
        Glide.with(mView.getActivity()).load(bean.getIcon()).into(mView.mIvPic);
        mView.mTvTitle.setText(bean.getGname());
        mView.mTvParam1.setText("价值:"+bean.getCost());
        int yzb= (int) (Double.valueOf(bean.getCost())*0.8);
        String exchange="兑换:<font color='#ff3951'>"+yzb+"</font>";
        mView.mTvParam2.setText(Html.fromHtml(exchange));
        mView.mTvParam3.setText("获奖号码:"+bean.getPhone().substring(0,4)+"..."+bean.getPhone().substring(bean.getPhone().length()-3,bean.getPhone().length()));
        mView.mTvParam4.setText("揭晓时间:"+bean.getWinnertime());
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    protected void onCreate() {

    }

    public void getData() {
        CommonRequestUtils.getRequestUtils().getdefaultaddress();
        CommonRequestUtils.getRequestUtils().getActivityDetail(mView.crowdfid);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }


    public void submit() {
        if (addresssid==null){
            BaseActivity.showToast(mView.getActivity(),"请输入收货地址");
            return;
        }
        final MessageDialog dialog=new MessageDialog(mView.getActivity());
        dialog.message.setText("请认真核对您的地址后点击确认");
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
                CommonRequestUtils.getRequestUtils().getConfirmAddress(mView.crowdfid,addresssid);
            }
        });
     dialog.show();
    }
}
