package com.chinayiz.chinayzy.ui.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.DepositPresenter;
import com.chinayiz.chinayzy.views.CheckImageView;

/**
 * 充值
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class DepositFragment extends BaseFragment<DepositPresenter> implements View.OnClickListener {
    public CheckImageView iv_ali_pay;
    public CheckImageView iv_wechat_pay;
    private TextView tv_deposit_submit;

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("充值");
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("切换\n账号");
        activity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip.toLogin(getActivity());
                mActivity.onBackPressed();
            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_deposit,null);
        iv_ali_pay = (CheckImageView) view.findViewById(R.id.iv_ali_pay);
        iv_ali_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //支付宝支付

                if (iv_wechat_pay.isCheck){
                    iv_wechat_pay.setCheck(false);
                    iv_ali_pay.setCheck(true);
                }
            }
        });

        iv_wechat_pay = (CheckImageView) view.findViewById(R.id.iv_wechat_pay);
        iv_wechat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_ali_pay.isCheck){
                    iv_ali_pay.setCheck(false);
                    iv_wechat_pay.setCheck(true);
                }
            }
        });
        tv_deposit_submit = (TextView)view.findViewById(R.id.tv_deposit_submit);
        tv_deposit_submit.setOnClickListener(this);
        iv_ali_pay.setCheck(true);
        iv_wechat_pay.setCheck(false);
        return view;
    }

    @Override
    public DepositPresenter initPresenter() {
        return new DepositPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_deposit_submit:
                 mPresenter.submit();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter.status==1){
            mPresenter.success();
        }
    }

}
