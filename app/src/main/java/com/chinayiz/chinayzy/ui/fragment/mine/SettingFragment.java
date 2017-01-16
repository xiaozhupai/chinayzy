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
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SettingPresenter;

/**  设置
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment<SettingPresenter> implements View.OnClickListener {


    private RelativeLayout rl_to_person;
    private TextView tv_cache;
    private ImageView iv_cache_imae;
    private RelativeLayout rl_to_cache;
    private CheckBox cb_sendmessage;
    private RelativeLayout rl_two_code;
    private TextView tv_version;
    private ImageView iv_version_imae;
    private RelativeLayout rl_version;
    private RelativeLayout rl_about_us;
    private TextView tv_logout;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);

        rl_to_person = (RelativeLayout) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.rl_to_person);
        rl_to_person.setOnClickListener(this);
        tv_cache = (TextView) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.tv_cache);
        tv_cache.setOnClickListener(this);
        iv_cache_imae = (ImageView) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.iv_cache_imae);
        iv_cache_imae.setOnClickListener(this);
        rl_to_cache = (RelativeLayout) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.rl_to_cache);
        rl_to_cache.setOnClickListener(this);
        cb_sendmessage = (CheckBox) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.cb_sendmessage);
        cb_sendmessage.setOnClickListener(this);
        rl_two_code = (RelativeLayout) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.rl_two_code);
        rl_two_code.setOnClickListener(this);
        tv_version = (TextView) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.tv_version);
        tv_version.setOnClickListener(this);
        iv_version_imae = (ImageView) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.iv_version_imae);
        iv_version_imae.setOnClickListener(this);
        rl_version = (RelativeLayout) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.rl_version);
        rl_version.setOnClickListener(this);
        rl_about_us = (RelativeLayout) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.rl_about_us);
        rl_about_us.setOnClickListener(this);
        tv_logout = (TextView) inflater.inflate(R.layout.fragment_setting, container, false).findViewById(R.id.tv_logout);
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

                break;
            case R.id.rl_to_cache:   //清楚缓存

                break;
            case R.id.rl_two_code:   //分享二维码

                break;
            case R.id.rl_version:   //版本更新

                break;
            case R.id.rl_about_us:  //关于我们

                break;
            case R.id.tv_logout:   //退出登录

                break;
        }
    }
}
