package com.chinayiz.chinayzy.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.SearchPopuAdaphter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.BrandModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**   搜索结果的弹窗
 * Created by Administrator on 2017/4/24.
 */

public class SearchPopuwindow extends PopupWindow implements View.OnClickListener,EventBusCallback {
    private Context context;
    private GridView gv_search;
    public SearchPopuAdaphter adaphter;
    private TextView tv_reset,tv_submit;
    private LinearLayout ll_bottom;
    private String searchkey;
    private List<BrandModel.DataBean> lists_brands;
    public static final String CALL_BACK="SearchPopuwindow";
    public boolean isMail;
    public SearchPopuwindow(Context context,String searchkey,boolean isMail) {
        super(context);
        this.context=context;
        this.searchkey=searchkey;
        this.isMail=isMail;
        initView();
        getData();
        EventBus.getDefault().register(this);
    }

    public SearchPopuwindow(Context context,String searchkey,List<BrandModel.DataBean> list,boolean isMail){
        super(context);
        this.context=context;
        this.searchkey=searchkey;
        this.isMail=isMail;
        initView();
        adaphter.setData(list);
    }

    private void getData() {
        String goodstype;
        if (isMail){
            goodstype="2";
            Net.getNet().getbrands("",goodstype);
        }else {
            goodstype="1";
            Net.getNet().getbrands(searchkey,goodstype);
        }
    }

    private void initView() {

        List list=new ArrayList();
        adaphter=new SearchPopuAdaphter(context,list);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View v=inflater.inflate(R.layout.search_popuwindow_layout,null);
        setContentView(v);
        gv_search= (GridView) v.findViewById(R.id.gv_search);
        gv_search.setAdapter(adaphter);
        tv_reset= (TextView) v.findViewById(R.id.tv_reset);
        tv_submit= (TextView) v.findViewById(R.id.tv_submit);
        ll_bottom= (LinearLayout) v.findViewById(R.id.ll_bottom);
        tv_reset.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        ll_bottom.setOnClickListener(this);
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                EventBus.getDefault().unregister(this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.tv_reset:
                Logger.i("重置");
                List <BrandModel.DataBean> lists=adaphter.lists;
                if (adaphter.lists!=null){
                    for (BrandModel.DataBean bean: lists) {
                        bean.setChecked(false);
                    }
                    adaphter.setData(lists);
                }
                break;
            case R.id.tv_submit:
                Logger.i("确定");
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CALL_BACK,adaphter.lists));
                dismiss();
                break;
            case R.id.ll_bottom:
                dismiss();
                break;
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getDataType()== Commons.GETBRANDS){
         BrandModel model= (BrandModel) message.getData();
            lists_brands=model.getData();
            adaphter.setData(lists_brands);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }
}
