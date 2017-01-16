package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.NongYe_HomeRecyl_Adapter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.NongYe_homePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/7 15:19
 * Class NongYe_homeFragment 农业首页
 */
public class NongYe_homeFragment extends BaseFragment<NongYe_homePresenter> {
    private RecyclerView mNongyeHomeRecyclerLayout;
    private List mDateList=new ArrayList();
    private NongYe_HomeRecyl_Adapter mRecylAdapter;

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void lazyLoad() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=0;i<9;i++){
//                    mDateList.add(i);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    mRecylAdapter.notifyDataSetChanged();
//                }
//            }
//        }).start();

    }
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nongye_home_banner, container);
        initWidget(view);
        return view;
    }
    private void initWidget(View view) {
//        mNongyeHomeRecyclerLayout = (RecyclerView) view.findViewById(R.id.nongye_home_recyclerLayout);
//        mNongyeHomeRecyclerLayout.setLayoutManager(new LinearLayoutManager(mContext));
////        mRecylAdapter=new NongYe_HomeRecyl_Adapter(mDateList);
////        mNongyeHomeRecyclerLayout.setAdapter(mRecylAdapter);
    }
    @Override
    public NongYe_homePresenter initPresenter() {
        return mPresenter;
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

}
