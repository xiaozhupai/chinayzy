package com.chinayiz.chinayzy.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.orhanobut.logger.Logger;

/**  首页弹出框
 * Created by Administrator on 2017/6/16.
 */

public class MainActivityDialog  extends DialogUtils.XDialog implements View.OnClickListener {
    public TextView tv_goods_title;
    private String crowdfid;
    private  String goodsTitle;
    private Context context;


    public MainActivityDialog(Context context,String goodsTitle,String crowdfid) {
        super(context);
        this.goodsTitle=goodsTitle;
        this.crowdfid=crowdfid;
        this.context=context;
        initView();
    }

    private void initView() {
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.CENTER);
        setContentView(R.layout.main_activity_dialog_layout);
        tv_goods_title= (TextView) findViewById(R.id.tv_goods_title);
        findViewById(R.id.iv_submit).setOnClickListener(this);
        findViewById(R.id.iv_right_cancel).setOnClickListener(this);
        if (goodsTitle.length()>15){
            goodsTitle=goodsTitle.substring(0,15)+"...";
        }
        tv_goods_title.setText(goodsTitle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_submit:
                Skip.toActivityDetail(context,crowdfid);
                break;
            case R.id.iv_right_cancel:

                break;
        }
        dismiss();
        Logger.i("crowdfid"+crowdfid);
    }
}
