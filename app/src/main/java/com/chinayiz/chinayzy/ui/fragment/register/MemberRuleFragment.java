package com.chinayiz.chinayzy.ui.fragment.register;


import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.Presenter;

/**
 * 会员须知
 */
@SuppressLint("ValidFragment")
public class MemberRuleFragment extends BaseFragment<Presenter> implements View.OnClickListener {

    private TextView tv_content;
    private TextView tv_member_check;
    private TextView tv_member_submit;
    private boolean isCheck;

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("会员权益");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_rule, null);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_member_check = (TextView) view.findViewById(R.id.tv_member_check);
        tv_member_submit = (TextView) view.findViewById(R.id.tv_member_submit);
        tv_member_submit.setOnClickListener(this);
        tv_member_check.setOnClickListener(this);
        tv_member_check.setText(Html.fromHtml("同意<font color='#ff3952'>《会员权益》</font>"));
        String content = "1、申请人须仔细阅读本须知，您点击同意按钮后即表示完全接受本协议项下的全部条款，" +
                "一旦注册即视为用户确认自己享有相关民事权利能力和行为能力并能够独立承担法律责任。\n\n 2、" +
                "申请会员资格须一次性向商城“我的钱包”充值1350元人民币消费益众品牌的商品和标有自营标签的商品，" +
                "获得会员资格后即可享受会员专属权利。\n\n 3、好友用您的邀请码成功注册，您将会获得平台分发的100元红利；好友邀请第三人成功注册，" +
                "您还可以获得平台分发的50元红利。\n\n 4、目前支持商城的所有实物类商品（包括自营、部分第三方）、部分虚拟类产品（话费和流量充值、" +
                "机票和电子书等）、预售商品。可支持商品的具体限额以实际支付情况为准，如超限，系统会有相应超限提示，请您更换其它方式完成支付。\n\n 5、" +
                "会员邀请商家在商城成功入驻的，将可以获得该商家在入驻期间，平台收益的10%的红利。\n\n 6、会员在商城平台消费的，将可以获得单笔消费商城平台收益的40%" +
                "~70%作为消费红利。\n\n 7、本协议由中国亿众负责解释。";
        tv_content.setText(content);
        return view;
    }

    @Override
    public Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_member_submit:
                if (isCheck) {
                    mActivity.addFragment(new MemberProtocolFragment());
                }
                break;
            case R.id.tv_member_check:
                if (isCheck) {
                    isCheck = false;
                    tv_member_submit.setBackgroundResource(R.drawable.shape_corner_gray);
                    Drawable drawable = getResources().getDrawable(R.mipmap.radio_normal);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_member_check.setCompoundDrawables(drawable, null, null, null);
                } else {
                    Drawable drawable = getResources().getDrawable(R.mipmap.radio_selected);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_member_check.setCompoundDrawables(drawable, null, null, null);
                    isCheck = true;
                    tv_member_submit.setBackgroundResource(R.drawable.shape_corner_red);
                }
                break;
        }
    }
}
