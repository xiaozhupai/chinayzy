package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.AddressAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.presenter.AddressListPresenter;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AddressListFragment extends BaseFragment<AddressListPresenter> implements View.OnClickListener {

    public ListView lv_addreddlist;
    public TextView tv_submit;
    public AddressAdaphter adaphter;
    public int index;
    public static final String ADDRESS_BACK="ADDRESS_BACK";
    public CommonActivity activity;


    @Override
    public void onInintData(Bundle bundle) {

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
    public void onInitActionBar(final BaseActivity activity) {
        activity.mTvActionBarTitle.setText("收货地址");
        activity.mCbActionBarEdit.setVisibility(View.GONE);
        activity.mIvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,ADDRESS_BACK,""));
                activity.onBackPressed();
            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list, null);
        lv_addreddlist = (ListView) view.findViewById(R.id.lv_addreddlist);
        tv_submit = (TextView) view.findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(this);
        adaphter=new AddressAdaphter(getActivity(),null);
        lv_addreddlist.setAdapter(adaphter);
        return view;

    }

    @Override
    public AddressListPresenter initPresenter() {
        return new AddressListPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:  //跳转到新增收货地址
                mPresenter.toAdd();
                break;
        }
    }
}
