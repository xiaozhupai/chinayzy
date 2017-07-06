package com.chinayiz.chinayzy.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.CouponAdaphter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.CouponModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**    优惠券弹出框
 * Created by Administrator on 2017/6/30.
 */

public class CouponDialog extends DialogUtils.XDialog implements EventBusCallback, AdapterView.OnItemClickListener {
    public ListView lv_coupon;
    public ImageView iv_close;
    public String couponlogids;
    public Context mContext;
    public CouponAdaphter adaphter;
    public static final String CouponDialog="CouponDialog";
    private List<CouponModel.DataBean> lists;

    public CouponDialog(Context context, int theme) {
        super(context, theme);
    }

    public CouponDialog(Context context,String couponlogids) {
        super(context);
        this.mContext=context;
        this.couponlogids=couponlogids;

         EventBus.getDefault().register(this);
        adaphter=new CouponAdaphter(context,null);
        initView();
        initData();
    }

    private void initData() {
        CommonRequestUtils.getRequestUtils().getCanUseCoupon(couponlogids);
    }

    private void initView() {
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.dialog_coupon);
        lv_coupon= (ListView) findViewById(R.id.lv_coupon);
        iv_close= (ImageView) findViewById(R.id.iv_close);
        lv_coupon.setOnItemClickListener(this);
        lv_coupon.setAdapter(adaphter);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
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

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
               switch (message.getDataType()){
                   case Commons.CANUSECOUPON:   //优惠券列表
                  CouponModel model= (CouponModel) message.getData();
                       lists=model.getData();
                           adaphter.setData(model.getData());
                       break;
               }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       CouponModel.DataBean dataBean=lists.get(position);
        EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,"CouponDialog",dataBean));
        dismiss();
    }
}
