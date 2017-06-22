package com.chinayiz.chinayzy.ui.fragment.mine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ScreListAdapter;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ImGoldModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.common.CommonWebFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/8 10:06
 * Class ImGoldFragment  我的积分
 */
@SuppressLint("ValidFragment")
public class ImGoldFragment extends AbsFragment implements View.OnClickListener {
    private TextView mTvSumGolds;
    private TextView mTvIncomes;
    private TextView mTvOutMoneys;
    private ListView mLvAccrualLog;
    private TextView mTvDealLog;
    private TextView mTvGetGold;
    private View mNotData;
    private String goldNum;
    private ScreListAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_im_gold, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void initView(View view) {
        mNotData=view.findViewById(R.id.view_notEarnings);
        mProgress = view.findViewById(R.id.ll_progress);
        iv_back_button = (ImageView) view.findViewById(R.id.iv_back_button);
        iv_back_button.setOnClickListener(this);
        view.findViewById(R.id.tv_outMoney).setOnClickListener(this);
        tv_actionbar_title = (TextView) view.findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setTextColor(Color.BLACK);
        tv_actionbar_title.setText("我的账户");
        view.findViewById(R.id.iv_more_button).setVisibility(View.GONE);
        mTvSumGolds = (TextView) view.findViewById(R.id.tv_sumGolds);
        mTvIncomes = (TextView) view.findViewById(R.id.tv_incomes);
        mTvOutMoneys = (TextView) view.findViewById(R.id.tv_outMoneys);
        mTvOutMoneys.setOnClickListener(this);
        mLvAccrualLog = (ListView) view.findViewById(R.id.lv_accrualLog);
        mTvDealLog = (TextView) view.findViewById(R.id.tv_dealLog);
        mTvDealLog.setOnClickListener(this);
        mTvGetGold = (TextView) view.findViewById(R.id.tv_getGold);
        mTvGetGold.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRequestUtils.getImGodl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button:
                getActivity().finish();
                break;
            case R.id.tv_outMoneys:
            case R.id.tv_outMoney:
                addFragment(new TakeFragment(goldNum),"TakeFragment");
                break;
            case R.id.tv_dealLog:
                addFragment(new DealListFragment(),"DealListFragment");
                break;
            case R.id.tv_getGold:
                addFragment(new CommonWebFragment("亿众币规则",Commons.API+Commons.GOLD_RULE),"CommonWebFragment");
                break;
        }
    }
    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.IM_GOLD: {
                ImGoldModel model= (ImGoldModel) message.getData();
                mProgress.setVisibility(View.GONE);
                if (model.getData().getEarningslist()!=null&&model.getData().getEarningslist().size()>0){
                    mNotData.setVisibility(View.GONE);
                }
                setGold(model);
                break;
            }
        }
    }
    private void setGold(ImGoldModel model) {
        Logger.i("处理科学计数法");

        mTvSumGolds.setText(model.getData().getTotalpoints()+"");
        mTvOutMoneys.setText(model.getData().getCancarrypoints()+"");
        mAdapter=new ScreListAdapter(getActivity(),model.getData().getEarningslist());
        mLvAccrualLog.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
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
}
