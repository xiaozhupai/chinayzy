package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.NewMainActivity;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.EvalueResultPresenter;
import com.orhanobut.logger.Logger;

/**
 *   交易，评价 结果
 * A simple {@link } subclass.
 */
@SuppressLint("ValidFragment")
public class EvalueResultFragment extends BaseFragment<EvalueResultPresenter> implements View.OnClickListener {


    private ImageView iv_result;
    private TextView tv_result;
    private TextView tv_to_others;
    private String title;
    private int type;
    /**
     * 商品评价成功
     */
    public static final int EVALUE=0;
    /**
     * 确认收货，交易成功
     */
    public static final int TRADE=1;

    /**
     * 支付订单成功
     */
    public static final int PAY=2;

    /**
     *
     */

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onInintData(Bundle bundle) {
        this.type=bundle.getInt("type");
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        mActivity=activity;
        mActivity.mIvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_evalue_result,null);
        iv_result = (ImageView)view.findViewById(R.id.iv_result);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        tv_to_others = (TextView)view.findViewById(R.id.tv_to_others);
        tv_to_others.setOnClickListener(this);
        switch (type){
            case EVALUE:
                mActivity.mTvActionBarTitle.setText("评价成功");
                tv_result.setText("感谢您宝贵的评价!");
                iv_result.setImageResource(R.mipmap.img_evalue_result);
                break;
            case TRADE:
                mActivity.mTvActionBarTitle.setText("交易成功");
                tv_result.setText("交易成功!卖家将收到您的付款~");
                iv_result.setImageResource(R.mipmap.img_trade_success);
                break;
            case PAY:
                mActivity.mTvActionBarTitle.setText("付款成功");
                tv_result.setText("付款成功，等待卖家发货");
                iv_result.setImageResource(R.mipmap.img_trade_success);
                break;
        }
        return view;
    }

    @Override
    public EvalueResultPresenter initPresenter() {
        return new EvalueResultPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.i("评价停止");
        getActivity().onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_others:
                Intent intent=new Intent(getActivity(), NewMainActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
