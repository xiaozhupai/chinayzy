package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.GoodsSetPresenter;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/19 15:13
 * Class GoodsSetFragment 商品分类code查询结果集
 */

public class GoodsSetFragment extends BaseFragment<GoodsSetPresenter> implements CompoundButton.OnCheckedChangeListener {
    public RecyclerView mRecyclerView;
    public String itemCode;
    public String typeName;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_goodsset,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rv_goodsSet);
        return view;
    }

    @Override
    public void onInintData(Bundle bundle) {
        String str=bundle.getString("itemCode","-1");
        Logger.i("数据="+str);
        String[] strs=str.split(",");
        itemCode=strs[0];
        typeName=strs[1];
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText(typeName);
        activity.mIvActionBarMore.setVisibility(View.GONE);
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("");
        activity.mCbActionBarEdit.setButtonDrawable(R.drawable.actionbar_edit);
        activity.mCbActionBarEdit.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Logger.i("是否选中="+isChecked);
    }

    @Override
    public GoodsSetPresenter initPresenter() {
        return new GoodsSetPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }
}
