package com.chinayiz.chinayzy.ui.fragment.mine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ResponseModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.common.CommonWebFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.chinayiz.chinayzy.net.Commons.EDIE_USERINFO;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/10 13:48
 * Class TakeFragment  积分提现/充值
 */
@SuppressLint("ValidFragment")
public class TakeFragment extends AbsFragment implements View.OnClickListener {
    private String goldNum;
    private TextView tv_goldRule;
    private TextView tv_canTakeNum;
    private Button bt_inPut;
    private Button bt_outPut;

    public TakeFragment(String goldNum) {
        this.goldNum = goldNum;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void initView(View view) {
        iv_back_button = (ImageView) view.findViewById(R.id.iv_back_button);
        iv_back_button.setOnClickListener(this);
        tv_actionbar_title = (TextView) view.findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setText("亿众币提现");
        tv_actionbar_title.setTextColor(Color.BLACK);
        view.findViewById(R.id.iv_more_button).setVisibility(View.GONE);

        tv_canTakeNum = (TextView) view.findViewById(R.id.tv_canTakeNum);
        tv_canTakeNum.setText(goldNum);
        tv_goldRule = (TextView) view.findViewById(R.id.tv_goldRule);
        bt_inPut = (Button) view.findViewById(R.id.bt_inPut);
        bt_outPut = (Button) view.findViewById(R.id.bt_outPut);
        tv_goldRule.setOnClickListener(this);
        bt_inPut.setOnClickListener(this);
        bt_outPut.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.USERINFO_ISOK:{
                ResponseModel model= (ResponseModel) message.getData();
                if ("1".equals(model.getData())) {
                    Logger.i("已经完善");
                    addFragment(new ResuestTakeFragment(),"ResuestTakeFragment");
                }else {
                    Logger.i("待完善");
                    new MaterialDialog.Builder(getActivity())
                            .limitIconToDefaultSize()
                            .title("提示")
                            .titleColor(Color.rgb(209,97,88))
                            .negativeText("下次再说")
                            .positiveText("去完善")
                            .content("系统检测您的详细资料尚未完善，请您先完善详细资料后再进行下一步操作。")
                            .contentColor(Color.rgb(43,43,43))
                            .backgroundColor(Color.rgb(230,230,230))
                            .negativeColor(Color.rgb(65,51,60))
                            .positiveColor(Color.rgb(57,181,74))
                            .onAny(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if (DialogAction.POSITIVE.toString().equals(which.name())){  //更新/下载
                                        String url=Commons.API+EDIE_USERINFO+"?userid="+ APP.sUserid+"&type=android";
                                        Skip.toWebPage(getActivity(),url,"完善资料");
                                    }else if (DialogAction.NEGATIVE.toString().equals(which.name())){ //取消更新/安装
                                       dialog.dismiss();
                                    }
                                }
                            })
                            .cancelable(false)
                            .build()
                            .show();
                }
                break;
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(getActivity(), "未知错误，请重试");
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_goldRule: {
                addFragment(new CommonWebFragment("提现规则", Commons.API + Commons.GET_GOLD_RULE), "CommonWebFragment");
                break;
            }
            case R.id.bt_inPut: {
                addFragment(new PrestoreFragment(), "PrestoreFragment");
                break;
            }
            case R.id.bt_outPut: {
                mRequestUtils.getUserInfoOk();
                break;
            }
            case R.id.iv_back_button: {
                getActivity().onBackPressed();
                break;
            }
        }
    }
}
