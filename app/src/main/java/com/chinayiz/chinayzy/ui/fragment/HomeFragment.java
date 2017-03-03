package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.ActionBarControlModel;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.presenter.homePresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 15:19
 * Class NongYe_homeFragment 农业首页
 */
public class HomeFragment extends BaseFragment<homePresenter> {
    public RecyclerView mNongyeHomeRecyclerLayout;
    public NongYeHomeRecylAdapter mRecylAdapter;
    public Map<Integer,Object> mDateList=new HashMap<>();
    public List<String> isLoad=new ArrayList<>();
    public ClassifyFragment mClassifyFragment = null;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nongye_fragment_home, container,false);
        initWidget(view);
        LeakCanary.refWatcher(getActivity());
        return view;
    }
    private void initWidget(View view) {
        mNongyeHomeRecyclerLayout = (RecyclerView) view.findViewById(R.id.nongye_home_recyclerLayout);
        mNongyeHomeRecyclerLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDateList.put(0,9);//初始化RecyclerView 的item条数
        mDateList.put(1,isLoad);
        mRecylAdapter=new NongYeHomeRecylAdapter(mDateList,this);
        mNongyeHomeRecyclerLayout.setAdapter(mRecylAdapter);
    }

    /**
     * 打开商品详情
     * @param goodsId 商品ID
     */
    public void openGoodesDetail(String goodsId) {
       mActivity.mGoodsFragment.setGoodsID(goodsId);
        startFragment(mActivity.mGoodsFragment,"GoodsFragment");
    }

    /**
     * 打开二级商品分类
     * @param code
     */
    public void openClassify(String code) {
        if (mClassifyFragment==null) {
            mClassifyFragment=new ClassifyFragment();
        }
        mClassifyFragment.setTypeCode(code);
        startFragment(mClassifyFragment,"ClassifyFragment");
    }

    @Override
    public void onResume() {
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                NongYeMainActivity.NYMAIN_ACTIONBAR,new ActionBarControlModel(NongYeMainActivity.SHOW_ALL,"首页",1,0,0,1)));
        super.onResume();
    }

    @Override
    public homePresenter initPresenter() {
        return new homePresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    protected void onInvisible() {
    }
    protected void onVisible() {

    }
}
