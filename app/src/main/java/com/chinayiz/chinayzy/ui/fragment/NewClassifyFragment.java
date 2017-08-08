package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ClassGridAdapter;
import com.chinayiz.chinayzy.adapter.TypeListAdpter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ClassifyTypesModel;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.presenter.NewClassifyPresenter;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/25 15:40
 * Class NewClassifyFragment
 */

public class NewClassifyFragment extends BaseFragment<NewClassifyPresenter> implements TypeListAdpter.OnItemSeletdListener {
    public RecyclerView mRecyclerView;
    public ListView mListView;
    public TypeListAdpter mListAdpter;
    public String mTypeCode;
    public ClassGridAdapter mGridAdapter;
    public String picUrl;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_classify, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("分类");
    }

    @Override
    public void onInintData(Bundle bundle) {
        mTypeCode = bundle.getString("itemCode");
    }

    private void initViews(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_typeList);
        mListAdpter = new TypeListAdpter(getActivity());
        mListView.setAdapter(mListAdpter);
        mListAdpter.setItemSeletdListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_typeGrid);
        mGridAdapter = new ClassGridAdapter(this,ClassGridAdapter.ITEM_GOODS);
        //设置网格布局管理器
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //添加headview核心代码
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //如果位置是0，那么这个条目将占用SpanCount()这么多的列数，在此也就是2
                //而如果不是0，则说明不是Header，就占用1列即可
                return mGridAdapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGridAdapter);
    }

    @Override
    public void onSeletItem(int position, ClassifyTypesModel.DataBean data) {
        ClassifyTypesModel.DataBean dataBean = data;
        picUrl = dataBean.getPic();
        mListAdpter.changeSelected(position);
        Logger.i("选择的条目="+dataBean.getTypecode());
        Net.getNet().getGoosSet("1","30","1",dataBean.getTypecode());
//        mPresenter.getClassDatas(dataBean.getTypecode());
    }


    @Override
    public void onResume() {
        super.onResume();
        // code不等于空白页，并且code不为空
        if (!("-1".equals(mTypeCode)) && mTypeCode != null) {
            mPresenter.getClassDatas(mTypeCode);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public NewClassifyPresenter initPresenter() {
        return new NewClassifyPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}
