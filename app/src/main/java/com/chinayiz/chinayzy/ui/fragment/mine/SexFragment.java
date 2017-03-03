package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SexPresenter;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.orhanobut.logger.Logger;

/**  性别
 * A simple {@link Fragment} subclass.
 */
public class SexFragment extends BaseFragment<SexPresenter> implements View.OnClickListener {
    public String param;
    public TextView tv_sex_man;
    public ImageView iv_sex_man;
    public RelativeLayout rl_sex_man;
    public TextView tv_sex_woman;
    public ImageView iv_sex_woman;
    public RelativeLayout rl_sex_woman;
    public MineActivity mineActivity;


    public SexFragment(String param) {
        this.param = param;
    }

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
        mineActivity= (MineActivity) activity;
        mineActivity.mTvActionBarTitle.setText("性别");
        mineActivity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        mineActivity.mCbActionBarEdit.setText("完成");
        mineActivity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.i("完成");
                mPresenter.submit();
            }
        });

    }


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_sex, null);
        tv_sex_man = (TextView) view.findViewById(R.id.tv_sex_man);
        tv_sex_man.setOnClickListener(this);
        iv_sex_man = (ImageView) view.findViewById(R.id.iv_sex_man);
        iv_sex_man.setOnClickListener(this);
        rl_sex_man = (RelativeLayout) view.findViewById(R.id.rl_sex_man);
        rl_sex_man.setOnClickListener(this);
        tv_sex_woman = (TextView) view.findViewById(R.id.tv_sex_woman);
        tv_sex_woman.setOnClickListener(this);
        iv_sex_woman = (ImageView) view.findViewById(R.id.iv_sex_woman);
        iv_sex_woman.setOnClickListener(this);
        rl_sex_woman = (RelativeLayout)view.findViewById(R.id.rl_sex_woman);
        rl_sex_woman.setOnClickListener(this);
        if (TextUtils.isEmpty(param)){
            iv_sex_man.setVisibility(View.GONE);
            iv_sex_woman.setVisibility(View.GONE);
        }else {
            if (param.equals("男")){
                iv_sex_man.setVisibility(View.VISIBLE);
                iv_sex_woman.setVisibility(View.GONE);
            }else {
                iv_sex_man.setVisibility(View.GONE);
                iv_sex_woman.setVisibility(View.VISIBLE);
            }
        }


        return view;

    }

    @Override
    public SexPresenter initPresenter() {
        return new SexPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_sex_man:
                iv_sex_man.setVisibility(View.VISIBLE);
                iv_sex_woman.setVisibility(View.GONE);
                break;
            case R.id.rl_sex_woman:
                iv_sex_man.setVisibility(View.GONE);
                iv_sex_woman.setVisibility(View.VISIBLE);
                break;
        }
    }
}
