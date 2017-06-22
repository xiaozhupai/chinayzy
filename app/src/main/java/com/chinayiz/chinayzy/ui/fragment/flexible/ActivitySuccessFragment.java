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
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.presenter.ActivitySuccessPresenter;
import com.orhanobut.logger.Logger;

/**
 * A simple {@link Fragment} subclass.
 */

public class ActivitySuccessFragment extends BaseFragment<ActivitySuccessPresenter> implements View.OnClickListener {
    private ImageView mIvResult;
    private TextView mTvResult;
    private TextView mTvToOthers;
    private TextView mTvToOthers2;
    public String  crowdfid;

    public ActivitySuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("支付结果");
    }

    @Override
    public void onInintData(Bundle bundle) {
        crowdfid=bundle.getString("crowdfid");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_success, null);
        mIvResult = (ImageView)view. findViewById(R.id.iv_result);
        mIvResult.setOnClickListener(this);
        mTvResult = (TextView)view.  findViewById(R.id.tv_result);
        mTvResult.setOnClickListener(this);
        mTvToOthers = (TextView)view.  findViewById(R.id.tv_to_others);
        mTvToOthers.setOnClickListener(this);
        mTvToOthers2 = (TextView)view.  findViewById(R.id.tv_to_others2);
        mTvToOthers2.setOnClickListener(this);
        return view;

    }

    @Override
    public ActivitySuccessPresenter initPresenter() {
        return  new ActivitySuccessPresenter();
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
            case R.id.tv_to_others:
                Logger.i("立即邀请");
                CommonRequestUtils.getRequestUtils().getSharecrowdfmessage(crowdfid);
                break;
            case R.id.tv_to_others2:
                Logger.i("返回首页");
                mActivity.finish();
                break;
        }
    }
}
