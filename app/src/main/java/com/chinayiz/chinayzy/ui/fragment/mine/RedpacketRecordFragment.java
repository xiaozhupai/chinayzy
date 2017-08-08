package com.chinayiz.chinayzy.ui.fragment.mine;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.RedpacketRecordAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BaseView;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.presenter.RedpacketRecordPrecenter;
import com.orhanobut.logger.Logger;

/**
 * 红包消费记录
 * A simple {@link Fragment} subclass.
 */
public class RedpacketRecordFragment extends BaseFragment<RedpacketRecordPrecenter> implements View.OnClickListener, BaseView {

    public ListView lv_record;
    public TextView red_packet_money;
    public RedpacketRecordAdapter mlvAdapter;

    public RedpacketRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        super.onInitActionBar(activity);
        activity.mTvActionBarTitle.setText("消费记录");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_redpacket, container, false);
        lv_record = (ListView) view.findViewById(R.id.lv_record);
        red_packet_money = (TextView) view.findViewById(R.id.red_packet_money);
        mlvAdapter = new RedpacketRecordAdapter(getActivity(), null);
        lv_record.setAdapter(mlvAdapter);

        return view;
    }

    @Override
    public RedpacketRecordPrecenter initPresenter() {
        return new RedpacketRecordPrecenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}
