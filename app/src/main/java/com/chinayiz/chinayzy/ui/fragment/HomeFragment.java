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
import com.chinayiz.chinayzy.presenter.NongYehomePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chinayiz.chinayzy.base.BaseActivity.mGoodsFragment;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 15:19
 * Class NongYe_homeFragment 农业首页
 */
public class HomeFragment extends BaseFragment<NongYehomePresenter> {
    public RecyclerView mNongyeHomeRecyclerLayout;
    public NongYeHomeRecylAdapter mRecylAdapter;
    public Map<Integer,Object> mDateList=new HashMap<>();
    public List<String> isLoad=new ArrayList<>();
    public ClassifyFragment mClassifyFragment = null;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nongye_fragment_home, container,false);
        initWidget(view);
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
        startFragment(mGoodsFragment,goodsId);
    }
    public void openClassify(String code) {
        if (mClassifyFragment==null) {
            mClassifyFragment=new ClassifyFragment();
        }
        mClassifyFragment.setTypeCode(code);
        startFragment(mClassifyFragment,"ClassifyFragment");
    }

    @Override
    public NongYehomePresenter initPresenter() {
        return new NongYehomePresenter();
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
