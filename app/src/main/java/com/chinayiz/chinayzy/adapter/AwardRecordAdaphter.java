package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AwardRecodModel;
import com.chinayiz.chinayzy.entity.response.ShareCrowdModel;
import com.chinayiz.chinayzy.entity.response.StringModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.chinayiz.chinayzy.ui.fragment.ListFragment;
import com.chinayiz.chinayzy.ui.fragment.flexible.AddressResultFragment;
import com.chinayiz.chinayzy.ui.fragment.flexible.ExchangeYzbFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.widget.ArrayAlertDialog;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.chinayiz.chinayzy.widget.ShareDialog2;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 获奖记录
 * Created by Administrator on 2017/6/13.
 */

public class AwardRecordAdaphter extends BaseInectAdaphter implements EventBusCallback {
    public String index;
    public static final String NO_START = "1";
    public static final String STARTING = "2";
    public static final String ANNOUNCED = "3";
    public static final String INVALID = "4";
    public static final String GET_AWARD = "5";
    public static final String FINISH="6";
    private  ArrayAlertDialog dialog;
    public static final String AwardRecordAdaphter_SHARE="AwardRecordAdaphter_SHARE";



    public AwardRecordAdaphter(Context context, String index) {
        this.context = context;
        this.index = index;
    }

    @Override
    public void onGetData(int pageindex) {
        CommonRequestUtils.getRequestUtils().getCrowdfrecord(index);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final BaseActivity activity= (BaseActivity) context;
        if (view == null) {
            view = View.inflate(context, R.layout.award_item_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final AwardRecodModel.DataBean bean = (AwardRecodModel.DataBean) lists.get(i);
        Glide.with(context).load(bean.getIcon()).into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(bean.getGname());
        String invite="我已成功邀请好友<font color='#ff3951'>"+bean.getInvitecount()+"</font>人";
        viewHolder.tv_param1.setText(Html.fromHtml(invite));
        viewHolder.tv_param2.setText("总需人数:"+bean.getAllcount());
        if (bean.getCtype().equals(NO_START)){
            viewHolder.pb_loading.setVisibility(View.VISIBLE);
            double progress=Double.valueOf(bean.getPlan());
            viewHolder.pb_loading.setProgress((int)progress);
        }else {
            viewHolder.pb_loading.setVisibility(View.GONE);
        }
        if (bean.getCtype().equals(NO_START)){
            viewHolder.tv_param3.setText("进度"+bean.getPlan()+"%");
        }else if (bean.getCtype().equals(ANNOUNCED)){
            viewHolder.tv_param3.setText("活动状态:规定时间内,参与人数未达到");
        }else {
            if (!TextUtils.isEmpty(bean.getPhone())){
                String get_award_phone="获奖号码:<font color='#ff3951'>"+bean.getPhone()+"   "+bean.getInvitecount()+"人</font><font color='#ff3951'>";
                viewHolder.tv_param3.setText(Html.fromHtml(get_award_phone));
            }
        }
        if (bean.getCtype().equals(INVALID) || bean.getCtype().equals(GET_AWARD) || bean.getCtype().equals(FINISH)){
            viewHolder.ll_layout.setBackgroundResource(R.mipmap.bg_get_award);
        }else {
            viewHolder.ll_layout.setBackgroundResource(R.color.white);
        }
        switch (bean.getCtype()) {
            case NO_START:   //进行中未完结

                viewHolder.tv_param4.setVisibility(View.GONE);
                viewHolder.tv_btn1.setVisibility(View.GONE);
                viewHolder.tv_btn2.setVisibility(View.VISIBLE);
                viewHolder.tv_btn2.setText("立即邀请");
                viewHolder.tv_btn2.setTextColor(Color.WHITE);
                viewHolder.tv_btn2.setBackgroundResource(R.drawable.shape_corner_red);
                viewHolder.iv_finish.setVisibility(View.GONE);
                viewHolder.tv_btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.i("立即邀请");
                        CommonRequestUtils.getRequestUtils().getSharecrowdfmessage(bean.getCrowdfid(),AwardRecordAdaphter_SHARE);
                    }
                });
                break;
            case STARTING:   //已揭晓 未获奖 已完结

                viewHolder.tv_param4.setVisibility(View.VISIBLE);
                viewHolder.tv_param4.setText("揭晓时间:"+bean.getCreatetime());
             viewHolder.iv_finish.setVisibility(View.VISIBLE);
                viewHolder.iv_finish.setImageResource(R.mipmap.icon_finish);
                viewHolder.tv_btn1.setVisibility(View.GONE);
                viewHolder.tv_btn2.setVisibility(View.GONE);

                break;
            case ANNOUNCED:   //已揭晓 未获奖 已失效

                viewHolder.tv_param4.setVisibility(View.GONE);
                viewHolder.iv_finish.setVisibility(View.VISIBLE);
                viewHolder.iv_finish.setImageResource(R.mipmap.icon_invalid);
                viewHolder.tv_btn1.setVisibility(View.GONE);
                viewHolder.tv_btn2.setVisibility(View.GONE);
                break;
            case INVALID:    //已揭晓 已获奖 未领取或未兑换

                viewHolder.tv_param4.setVisibility(View.VISIBLE);
                viewHolder.tv_param4.setText("揭晓时间:"+bean.getCreatetime());
                viewHolder.iv_finish.setVisibility(View.GONE);
                viewHolder.tv_btn1.setVisibility(View.VISIBLE);
                viewHolder.tv_btn2.setVisibility(View.VISIBLE);
                viewHolder.tv_btn1.setText("兑换亿众币");
                viewHolder.tv_btn2.setText("立即领取");
                viewHolder.tv_btn1.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.tv_btn2.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.tv_btn1.setBackgroundResource(R.drawable.shape_corner_red_solid);
                viewHolder.tv_btn2.setBackgroundResource(R.drawable.shape_corner_red_solid);
                viewHolder.tv_btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (UserSeeion.isMember(context, "")) {  //如果是会员

                            activity.addFragment(new ExchangeYzbFragment(bean.getCrowdfid()));
                        } else {
                            final MessageDialog dialog = new MessageDialog(context);
                            dialog.setTitle("提示");
                            dialog.message.setText("此功能股东会员专享,您还不是股东会员哦!");
                            dialog.setButton1("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.setButton2("去了解会员", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Logger.i("去了解会员");
                                    if (UserSeeion.getSys_auth(context).equals("1")){   //已经完善资料
                                        if (UserSeeion.isMember(context)){
                                            Skip.toDeposit(context);  //充值
                                        }
                                    }else {
                                        Skip.toPerfestData(context);
                                    }
                                }
                            });
                            dialog.show();
                        }

                    }
                });

                viewHolder.tv_btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.addFragment(new AddressResultFragment(bean.getCrowdfid()));
                    }
                });
                break;
            case GET_AWARD:    //已揭晓 已获奖 已领

                viewHolder.tv_param4.setVisibility(View.VISIBLE);
                viewHolder.tv_param4.setText("揭晓时间:"+bean.getCreatetime());
                viewHolder.iv_finish.setVisibility(View.GONE);
                viewHolder.tv_btn1.setVisibility(View.VISIBLE);
                viewHolder.tv_btn2.setVisibility(View.VISIBLE);
                viewHolder.tv_btn1.setText("确认收货");
                viewHolder.tv_btn2.setText("查看物流");
                viewHolder.tv_btn1.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.tv_btn2.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.tv_btn1.setBackgroundResource(R.drawable.shape_corner_red_solid);
                viewHolder.tv_btn2.setBackgroundResource(R.drawable.shape_corner_red_solid);
                viewHolder.tv_btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.i("确认收货");
                        final MessageDialog dialog=new MessageDialog(context);
                        dialog.message.setText("确认收货吗?");
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
                                CommonRequestUtils.getRequestUtils().getConfirmshouhuo(bean.getCrowdfid());
                            }
                        });
                        dialog.show();
                    }
                });
                viewHolder.tv_btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.i("查看物流");
                        Skip.toWebPage(context,Commons.API+Commons.CROWDFWLXX+"?userid="+ APP.sUserid+"&crowdfid="+bean.getCrowdfid()+"&devicetype=android","物流信息");
                    }
                });
                break;
            case FINISH:   //已揭晓 已获奖 已完结
                viewHolder.tv_param4.setVisibility(View.VISIBLE);
                viewHolder.tv_param4.setText("揭晓时间:"+bean.getCreatetime());
                viewHolder.iv_finish.setVisibility(View.VISIBLE);
                viewHolder.iv_finish.setImageResource(R.mipmap.icon_finish);
                viewHolder.tv_btn1.setVisibility(View.GONE);
                viewHolder.tv_btn2.setVisibility(View.GONE);
                break;
        }
        return view;
    }

    @Override
    public void onItemClick(int position) {
         AwardRecodModel.DataBean bean= (AwardRecodModel.DataBean) lists.get(position);
        Skip.toWebPage(context,bean.getWebpageUrl()+"&userid="+APP.sUserid,"活动详情");
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }else if (message.getEventType()== EventMessage.ERROR_EVENT){
//            if (pullrefresh!=null){
//                pullResult();
//            }
            if (mSmartRefresh!=null){
                pullResult();
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        if (message.getDataType().equals(index)){ //获奖记录
            AwardRecodModel model = (AwardRecodModel) message.getData();
            lists=model.getData();
            setData(lists);

//            pullrefresh.loadmoreView.setVisibility(View.GONE);
//            pullrefresh.setLoadMoreVisiable(false);
//            pullrefresh.refreshFinish(PullToRefreshLayout.SUCCEED);
            
            mSmartRefresh.finishRefresh();
            if (lists.size()==0){
               ListFragment listFragment= (ListFragment) fragment;
                listFragment.ll_none.setVisibility(View.VISIBLE);
                listFragment.iv_none.setImageResource(R.mipmap.img_none_award);
                listFragment.tv_none.setText("您还没有参加过此活动~");
            }
            return;
        }

        switch (message.getDataType()) {
            case Commons.CONFIRMSHOUHUO:   //确认收货
              StringModel model1= (StringModel) message.getData();
                if (model1.getCode().equals("100")){
                    onRefresh();
                }
                break;
            case AwardRecordAdaphter_SHARE:
                Logger.i(AwardRecordAdaphter_SHARE);
                ShareCrowdModel model2= (ShareCrowdModel) message.getData();
                final ShareCrowdModel.DataBean bean= model2.getData();
                ShareDialog2 dialog2=new ShareDialog2(context,bean.getImage(),bean.getWebpageUrl(),bean.getTitle(),bean.getContent());
                dialog2.show();

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }


    public static class ViewHolder {
        public View rootView;
        public ImageView iv_icon;
        public TextView tv_title;
        public TextView tv_param1;
        public TextView tv_param2;
        public TextView tv_param3;
        public TextView tv_param4;
        public ProgressBar pb_loading;
        public TextView tv_btn1;
        public TextView tv_btn2;
        public ImageView iv_finish;
        public LinearLayout ll_layout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_icon = (ImageView) rootView.findViewById(R.id.iv_icon);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_param1 = (TextView) rootView.findViewById(R.id.tv_param1);
            this.tv_param2 = (TextView) rootView.findViewById(R.id.tv_param2);
            this.tv_param3 = (TextView) rootView.findViewById(R.id.tv_param3);
            this.tv_param4 = (TextView) rootView.findViewById(R.id.tv_param4);
            this.pb_loading = (ProgressBar) rootView.findViewById(R.id.pb_loading);
            this.tv_btn1 = (TextView) rootView.findViewById(R.id.tv_btn1);
            this.tv_btn2 = (TextView) rootView.findViewById(R.id.tv_btn2);
            this.iv_finish = (ImageView) rootView.findViewById(R.id.iv_finish);
            this.ll_layout=(LinearLayout) rootView.findViewById(R.id.ll_layout);
        }

    }
}
