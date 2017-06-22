package com.chinayiz.chinayzy.ui.fragment.flexible;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ActivityDetailModel;
import com.chinayiz.chinayzy.presenter.ExchangeYzbPresenter;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.orhanobut.logger.Logger;

/**
 * 兑换亿众币
 * A simple {@link Fragment} subclass.
 */
public class ExchangeYzbFragment extends BaseFragment<ExchangeYzbPresenter> implements View.OnClickListener {
    public ImageView mIvPic;
    public TextView mTvTitle;
    public TextView mTvParam1;
    public TextView mTvParam2;
    public ImageView mIvYzbInfo;
    public TextView mTvParam3;
    public TextView mTvParam4;
    public TextView mTvSubmit;
    public ActivityDetailModel.DataBean bean;
    public  String crowdfid;

    public ExchangeYzbFragment(String crowdfid) {

        this.crowdfid=crowdfid;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("兑换亿众币");
        activity.mCbActionBarEdit.setText("帮助");
        activity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MessageDialog dialog=new MessageDialog(getActivity());
                dialog.setTitle("提示");
                dialog.message.setText("商品兑换(1元=0.8亿众币),亿众币可以直接提现,兑换的商品将无法退换");
                dialog.vButton1.setVisibility(View.GONE);
                dialog.setButton2("我知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange_yzb, null);
        mIvPic = (ImageView) view.findViewById(R.id.iv_pic);
        mIvPic.setOnClickListener(this);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(this);
        mTvParam1 = (TextView) view.findViewById(R.id.tv_param1);
        mTvParam1.setOnClickListener(this);
        mTvParam2 = (TextView) view.findViewById(R.id.tv_param2);
        mTvParam2.setOnClickListener(this);
//        mIvYzbInfo = (ImageView) view.findViewById(R.id.iv_yzb_info);
//        mIvYzbInfo.setOnClickListener(this);
        mTvParam3 = (TextView) view.findViewById(R.id.tv_param3);
        mTvParam3.setOnClickListener(this);
        mTvParam4 = (TextView) view.findViewById(R.id.tv_param4);
        mTvParam4.setOnClickListener(this);
        mTvSubmit = (TextView) view.findViewById(R.id.tv_submit);
        mTvSubmit.setOnClickListener(this);

        return view;
    }



    @Override
    public ExchangeYzbPresenter initPresenter() {
        return new ExchangeYzbPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_submit:
                Logger.i("立即兑换");
                mPresenter.submit();
                break;

        }

    }
}
