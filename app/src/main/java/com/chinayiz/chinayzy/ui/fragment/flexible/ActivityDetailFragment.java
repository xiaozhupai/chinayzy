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
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.presenter.ActivityDetailPresenter;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.orhanobut.logger.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityDetailFragment extends BaseFragment<ActivityDetailPresenter> implements View.OnClickListener {


    public TextView tv_title;
    public TextView tv_invite_friend;
    public TextView tv_param1;
    public TextView tv_param2;
    public TextView tv_param3;
    public TextView tv_param4;
    public TextView tv_btn1;
    public TextView tv_btn2;
    public String crowdfid;
    public ImageView iv_pic;

    public ActivityDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("获奖详情");
    }

    @Override
    public void onInintData(Bundle bundle) {
        crowdfid= bundle.getString("crowdfid");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_detail, null);
        tv_title = (TextView)view. findViewById(R.id.tv_title);
        tv_param1 = (TextView) view.findViewById(R.id.tv_param1);
        tv_param2 = (TextView) view. findViewById(R.id.tv_param2);
        tv_param3 = (TextView)  view.findViewById(R.id.tv_param3);
        tv_param4 = (TextView)  view.findViewById(R.id.tv_param4);
        tv_btn1 = (TextView) view. findViewById(R.id.tv_btn1);
        tv_btn2 = (TextView)  view.findViewById(R.id.tv_btn2);
        iv_pic= (ImageView) view.findViewById(R.id.iv_pic);
        tv_btn2.setOnClickListener(this);
        tv_btn1.setOnClickListener(this);
        return view;

    }

    @Override
    public ActivityDetailPresenter initPresenter() {
        return new ActivityDetailPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }


    @Override
    public void onClick(View v) {
        if (mPresenter.bean!=null) {
            switch (v.getId()) {
                case R.id.tv_btn1:
                    Logger.i("兑换亿众币");
                    if (UserSeeion.isMember(getActivity(), "")) {  //如果是会员

                        mActivity.addFragment(new ExchangeYzbFragment(crowdfid));
                    } else {
                        final MessageDialog dialog = new MessageDialog(getActivity());
                        dialog.setTitle("提示");
                        dialog.message.setText("此功能股东会员专享,您还不是股东会员哦!");
                        dialog.setButton1("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.setButton2("去了解会员", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                Logger.i("去了解会员");
                                if (UserSeeion.getSys_auth(getActivity()).equals("1")){   //已经完善资料
                                    if (UserSeeion.isMember(getActivity())){
                                        Skip.toDeposit(getActivity());  //充值
                                    }
                                }else {
                                    Skip.toPerfestData(getActivity());
                                }
                            }
                        });
                        dialog.show();
                    }
                    break;
                case R.id.tv_btn2:
                    Logger.i("领取奖品");
                    mActivity.addFragment(new AddressResultFragment(crowdfid));

                    break;
            }
        }
    }
}
