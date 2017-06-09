package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.TrueNamePresenter;
import com.chinayiz.chinayzy.ui.fragment.register.MemberRuleFragment;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.orhanobut.logger.Logger;

/**
 * A simple {@link Fragment} subclass. 完善个人资料
 */

@SuppressLint("ValidFragment")
public class TrueNameFragment extends BaseFragment<TrueNamePresenter> implements View.OnClickListener {
    public EditText mEtRegisterTruename;
    public EditText mEtRegisterCard;
    public CheckImageView mCivCheck;
    public TextView mTvMember;
    public TextView mTvSubmit;

    @Override
    protected void onVisible() {

    }


    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("完善个人资料");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_name, null);
        mEtRegisterTruename = (EditText) view.findViewById(R.id.et_register_truename);
        mEtRegisterTruename.setOnClickListener(this);
        mEtRegisterCard = (EditText) view.findViewById(R.id.et_register_card);
        mEtRegisterCard.setOnClickListener(this);
        mCivCheck = (CheckImageView) view.findViewById(R.id.civ_check);
        mCivCheck.setOnClickListener(this);
        mTvMember = (TextView) view.findViewById(R.id.tv_member);
        mTvMember.setOnClickListener(this);
        mTvSubmit = (TextView) view.findViewById(R.id.tv_submit);
        mTvSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public TrueNamePresenter initPresenter() {
        return new TrueNamePresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
              mPresenter.submit();
                Logger.i("申请会员");
                break;
            case R.id.civ_check:
                 if (mCivCheck.isCheck){
                     mCivCheck.setCheck(false);
                 }else {
                     mCivCheck.setCheck(true);
                 }
                break;
            case R.id.tv_member:
                mActivity.addFragment(new MemberRuleFragment());
                break;
        }
    }


}
