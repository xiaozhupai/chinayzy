package com.chinayiz.chinayzy.ui.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SettingPresenter;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.orhanobut.logger.Logger;

/**  设置
 * A simple {@link Fragment} subclass.
 */

public class SettingFragment extends BaseFragment<SettingPresenter> implements View.OnClickListener {
    public RelativeLayout rl_to_person;
    public TextView tv_cache;
    public ImageView iv_cache_imae;
    public RelativeLayout rl_to_cache;
    public CheckBox cb_sendmessage;
    public RelativeLayout rl_two_code;
    public TextView tv_version;
    public ImageView iv_version_imae;
    public RelativeLayout rl_version;
    public RelativeLayout rl_about_us;
    public TextView tv_logout;
    public TextView tv_cache_data;





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

        activity.mTvActionBarTitle.setText("设置");
        activity.mCbActionBarEdit.setVisibility(View.GONE);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        rl_to_person = (RelativeLayout) view.findViewById(R.id.rl_to_person);
        rl_to_person.setOnClickListener(this);
        tv_cache_data= (TextView) view.findViewById(R.id.tv_cache_data);
        tv_cache = (TextView) view.findViewById(R.id.tv_cache);
        tv_cache.setOnClickListener(this);
        iv_cache_imae = (ImageView) view.findViewById(R.id.iv_cache_imae);
        iv_cache_imae.setOnClickListener(this);
        rl_to_cache = (RelativeLayout) view.findViewById(R.id.rl_to_cache);
        rl_to_cache.setOnClickListener(this);
        cb_sendmessage = (CheckBox) view.findViewById(R.id.cb_sendmessage);
        cb_sendmessage.setOnClickListener(this);
        rl_two_code = (RelativeLayout)view.findViewById(R.id.rl_two_code);
        rl_two_code.setOnClickListener(this);
        tv_version = (TextView) view.findViewById(R.id.tv_version);
        tv_version.setOnClickListener(this);
        iv_version_imae = (ImageView) view.findViewById(R.id.iv_version_imae);
        iv_version_imae.setOnClickListener(this);
        rl_version = (RelativeLayout) view.findViewById(R.id.rl_version);
        rl_version.setOnClickListener(this);
        rl_about_us = (RelativeLayout)view.findViewById(R.id.rl_about_us);
        rl_about_us.setOnClickListener(this);
        tv_logout = (TextView) view.findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(this);
        return view;
    }

    @Override
    public SettingPresenter initPresenter() {
        return new SettingPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_to_person:   //个人中心
                Logger.i("个人中心");
                mPresenter.toPerson();
                break;
            case R.id.rl_to_cache:   //清除缓存
                Logger.i("清除缓存");
             mPresenter.clearCache();
                break;
            case R.id.rl_two_code:   //分享二维码
                Logger.i("分享二维码");
                break;
            case R.id.rl_version:   //版本更新
                Logger.i("版本更新");
                break;
            case R.id.rl_about_us:  //关于我们
                Logger.i("关于我们");
                mPresenter.toAboutUs();
                break;
            case R.id.tv_logout:   //退出登录
                Logger.i("退出登录");
                  mPresenter.logout();
                break;
        }
    }
}
