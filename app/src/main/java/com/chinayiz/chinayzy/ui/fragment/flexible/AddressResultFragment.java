package com.chinayiz.chinayzy.ui.fragment.flexible;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.AddressResultPresenter;
import com.chinayiz.chinayzy.ui.fragment.mine.AddressListFragment;
import com.orhanobut.logger.Logger;

import static com.chinayiz.chinayzy.R.id.iv_pic;
import static com.chinayiz.chinayzy.R.id.tv_param1;
import static com.chinayiz.chinayzy.R.id.tv_param2;
import static com.chinayiz.chinayzy.R.id.tv_param3;
import static com.chinayiz.chinayzy.R.id.tv_param4;
import static com.chinayiz.chinayzy.R.id.tv_title;

/**
 * 活动页面中的  确认收货地址
 * A simple {@link Fragment} subclass.
 */
public class AddressResultFragment extends BaseFragment<AddressResultPresenter> implements View.OnClickListener {


    public ImageView mIvLeft;
    public TextView mTvAddressName;
    public TextView mTvAddressPhone;
    public TextView mTvNoAddress;
    public TextView mTvAddressText;
    public LinearLayout mLvCenter;
    public ImageView mIvRight;
    public ImageView mIvPic;
    public TextView mTvTitle;
    public TextView mTvParam1;
    public TextView mTvParam2;
    public TextView mTvParam3;
    public TextView mTvParam4;
    public TextView mTvSubmit;

    public String crowdfid;

    public AddressResultFragment(String crowdfid) {
        this.crowdfid=crowdfid;
        // Required empty public constructor
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("确认收货地址");
        mPresenter.getData();
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_result, null);
        mIvLeft = (ImageView) view.findViewById(R.id.iv_left);
        mIvLeft.setOnClickListener(this);
        mTvAddressName = (TextView) view.findViewById(R.id.tv_address_name);
        mTvAddressName.setOnClickListener(this);
        mTvAddressPhone = (TextView) view.findViewById(R.id.tv_address_phone);
        mTvAddressPhone.setOnClickListener(this);
        mTvNoAddress = (TextView) view.findViewById(R.id.tv_no_address);
        mTvNoAddress.setOnClickListener(this);
        mTvAddressText = (TextView) view.findViewById(R.id.tv_address_text);
        mTvAddressText.setOnClickListener(this);
        mLvCenter = (LinearLayout) view.findViewById(R.id.lv_center);
        mLvCenter.setOnClickListener(this);
        mIvRight = (ImageView) view.findViewById(R.id.iv_right);
        mIvRight.setOnClickListener(this);
        mIvPic = (ImageView) view.findViewById(iv_pic);
        mIvPic.setOnClickListener(this);
        mTvTitle = (TextView) view.findViewById(tv_title);
        mTvTitle.setOnClickListener(this);
        mTvParam1 = (TextView) view.findViewById(tv_param1);
        mTvParam1.setOnClickListener(this);
        mTvParam2 = (TextView) view.findViewById(tv_param2);
        mTvParam2.setOnClickListener(this);
        mTvParam3 = (TextView) view.findViewById(tv_param3);
        mTvParam3.setOnClickListener(this);
        mTvParam4 = (TextView) view.findViewById(tv_param4);
        mTvParam4.setOnClickListener(this);
       mTvSubmit= (TextView) view.findViewById(R.id.tv_submit);
        mTvSubmit.setOnClickListener(this);
        return view;

    }


    @Override
    public void onResume() {

        super.onResume();
    }



    @Override
    public AddressResultPresenter initPresenter() {
        return new AddressResultPresenter();
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
            case R.id.lv_center:
                Logger.i("地址列表");
                mActivity.addFragment(new AddressListFragment());
                break;
            case R.id.tv_submit:
                mPresenter.submit();
                Logger.i("提交");
                break;
        }
    }
}
