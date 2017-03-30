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
import com.chinayiz.chinayzy.entity.model.ActionBarControlModel;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ClassifyTypesModel;
import com.chinayiz.chinayzy.presenter.ClassifyPresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/27 15:54
 * Class ClassifyFragment  生态农业首页二级分类菜单
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements TypeListAdpter.OnItemSeletdListener {
    public RecyclerView mRecyclerView;
    public ListView mListView;
    public String mTypeCode;
    public TypeListAdpter mListAdpter;
    public ClassGridAdapter mGridAdapter;
    public String picUrl;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if ("-1".equals(mTypeCode)){
            view=inflater.inflate(R.layout.fragment_activity,container,false);
        }else {
            view= inflater.inflate(R.layout.fragment_classify, container, false);
            initViews(view);
        }
        return view;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        switch (mTypeCode) {
            case "1":
                activity.mTvActionBarTitle.setText("有机农业");
                break;
            case "-1":
                activity.mTvActionBarTitle.setText("敬请期待");
                break;

        }
        activity.mTvActionBarTitle.setText("");
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
        mGridAdapter = new ClassGridAdapter(this);
        //设置网格布局管理器
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
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
        mPresenter.getClassDatas(dataBean.getTypecode());
        mListAdpter.changeSelected(position);
    }


    @Override
    public void onResume() {
        super.onResume();
        // code不等于空白页，并且code不为空
        if (!("-1".equals(mTypeCode))&&mTypeCode != null) {
            mPresenter.getTypeDatas(mTypeCode);
        }
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                NongYeMainActivity.NYMAIN_ACTIONBAR,
                new ActionBarControlModel(NongYeMainActivity.HIDE_NAVIGTION, "分类", 1, 1, 0, 1)));
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                NongYeMainActivity.NYMAIN_ACTIONBAR,
                new ActionBarControlModel(NongYeMainActivity.SHOW_ALL, "首页", 1, 0, 0, 1)));
    }

    @Override
    public ClassifyPresenter initPresenter() {
        return new ClassifyPresenter();
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
