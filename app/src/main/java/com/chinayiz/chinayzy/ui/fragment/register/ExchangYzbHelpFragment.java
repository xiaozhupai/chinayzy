package com.chinayiz.chinayzy.ui.fragment.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ExchangYzbHelpPresenter;
import com.chinayiz.chinayzy.presenter.MemberProtocolPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangYzbHelpFragment extends BaseFragment<ExchangYzbHelpPresenter> implements View.OnClickListener {
    public TextView tv_content;
    private TextView tv_member_check;
    private TextView tv_member_submit;
    private boolean isCheck;

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("用户协议");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchang_yzb_help, null);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
//        tv_member_check = (TextView) view.findViewById(R.id.tv_member_check);
        tv_member_submit = (TextView) view.findViewById(R.id.tv_member_submit);
        tv_member_submit.setOnClickListener(this);
//        tv_member_check.setOnClickListener(this);
//        tv_member_check.setText(Html.fromHtml("同意 <font color='#ff3952'>《用户协议》</font>"));
//        String content = "【关于注册会员】\n" +
//                "注册会员自愿成为中国亿众的一员，按照中国亿众会员进入标准成功注册后，其享受中国亿众的一切服务。对于本服务的约定，注册会员将自动遵守中国亿众的一切法规，如利用中国亿众生态系统谋取其他私利，对平台造成损失，该会员视为自动与平台解除合约，对于丢失的亿众虚拟币中国亿众将不承担一切后果。\n" +
//                "为了便于区分，中国亿众简称甲方，注册会员简称乙方。\n" +
//                "【关于义务】\n" +
//                "1.乙方自愿下载和使用甲方服务软件。\n" +
//                "2.乙方自愿申请注册甲方的会员，并自愿成为甲方的参与者，成功注册后享受中国亿众生态系统平台的一切收益。\n" +
//                "3.乙方在注册期限内有权办理手机支付和注销手续。\n" +
//                "4.乙方对甲方的服务如有疑问、建议或意见时，可通过客户系统进行咨询和投诉。\n" +
//                "【关于权益】\n" +
//                "1.乙方自行负责下载中国亿众平台软件和个人上网支付的与服务有关的电话费用和网络费用。\n" +
//                "2.乙方应按照机密的原则设置和保管自设密码，避免使用姓名，生日，电话号码等于本人明显相关的信息作为密码；不得将本人自设密码提供给除法律规定外的任何人；采取其他合理措施，防止本人密码被窃取。由于密码泄露造成的后果由乙方自行承担。\n" +
//                "3.乙方必须妥善保管自己的手机、用户名、密码、用户擅自转让或授权他人使用自己的手机、用户名和密码产生的不良后果，甲方不承担任何的责任。\n" +
//                "4.乙方有义务在注册时提供自己真实的资料，同时，用户也有义务在相关资料实际变更时及时更新有关注册资料，用户保证不以他人资料进行软件注册或认证。\n" +
//                "5.乙方办理手机支付业务，应遵守甲方在平台上的公布的交易规则。\n" +
//                "6.乙方承担自己在使用中国亿众平台时实施所有行为遵守国家法律、法规以及各种社会公共利益或公共道德。对于任何法律后果的发生，乙方将以自己名义独立承担所有相关的法律责任。";
//        tv_content.setText(content);
        return view;
    }

    @Override
    public ExchangYzbHelpPresenter initPresenter() {
        return new ExchangYzbHelpPresenter();
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
            case R.id.tv_member_submit:
                mPresenter.submit();
                break;
        }
    }
}
