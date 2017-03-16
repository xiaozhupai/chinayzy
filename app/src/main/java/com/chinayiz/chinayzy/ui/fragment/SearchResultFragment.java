package com.chinayiz.chinayzy.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.SearchResultAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.presenter.SearchResultPresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.chinayiz.chinayzy.ui.common.GoodsActivity;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.orhanobut.logger.Logger;

/**
 * 搜索结果
 */
public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView tv_hot;
    private TextView tv_sale;
    private TextView tv_price;
    public GridView gd_list;
    public CheckBox cb_sale,cb_price;
    public SearchResultAdaphter adaphter;
    public SearchResultAdaphter adaphter2;
    public PullToRefreshLayout refresh_view;
    public static final String TITLE="TITLE";
    public String title;
    public int index=2;
    public NongYeMainActivity activity;
    public boolean isList=true;  //是否是列表排列
    public int type=1;
    public int page=1;
    public int mPostion;

    public SearchResultFragment(String title) {
        this.title=title;
    }

    @Override
    protected void onVisible() {
    }


    @Override
    protected void onInvisible() {
    }

    @Override
    public void onInitActionBar(final BaseActivity activity) {
        activity.mActionBar.setVisibility(View.VISIBLE);
        activity.mTvActionBarTitle.setText(title);
        activity.mIvActionBarMore.setVisibility(View.VISIBLE);
        activity.mIvActionBarMore.setImageResource(R.mipmap.icon_listview);
        activity.mIvActionBarMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isList){
                    activity.mIvActionBarMore.setImageResource(R.mipmap.icon_gridview);
                    isList=false;
                    type=2;
                    gd_list.setNumColumns(2);
                    adaphter2.setData(mPresenter.data,2);
                    gd_list.setAdapter(adaphter2);
                }else {
                    activity.mIvActionBarMore.setImageResource(R.mipmap.icon_listview);
                    isList=true;
                    type=1;
                    gd_list.setNumColumns(1);
                    adaphter.setData(mPresenter.data,1);
                    gd_list.setAdapter(adaphter);
                }
                mPresenter.getData();
            }
        });
    }

    @Override
    public void onInintData(Bundle bundle) {
        adaphter=new SearchResultAdaphter(null,1,getActivity());
        adaphter2=new SearchResultAdaphter(null,2,getActivity());

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, null);
        refresh_view= (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        gd_list= (GridView) view.findViewById(R.id.gd_list);
        cb_sale= (CheckBox) view.findViewById(R.id.cb_sale);
        cb_price= (CheckBox) view.findViewById(R.id.cb_price);
        tv_hot = (TextView) view.findViewById(R.id.tv_hot);
        tv_hot.setOnClickListener(this);
        adaphter.setRefreshLayout(refresh_view);
        adaphter2.setRefreshLayout(refresh_view);
        gd_list.setAdapter(adaphter);
        gd_list.setOnItemClickListener(this);
        refresh_view.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                page=1;
                mPresenter.getData();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
//              mPostion=gd_list.getFirstVisiblePosition();
                page++;
                mPresenter.getData();
            }
        });
        cb_sale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                page=1;
                setAll();
                cb_sale.setTextColor(getResources().getColor(R.color.find_green_text));

                if (b){
                    index=3;
                    Drawable nav_up=getResources().getDrawable(R.mipmap.icon_sort_down);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    cb_sale.setCompoundDrawables(null, null, nav_up, null);
                }else {
                    index=4;
                    Drawable nav_up=getResources().getDrawable(R.mipmap.icon_sort_up);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    cb_sale.setCompoundDrawables(null, null, nav_up, null);
                }
                mPresenter.getData();
            }
        });

        cb_price.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                page=1;
                setAll();
                cb_price.setTextColor(getResources().getColor(R.color.find_green_text));
                if (b){
                    index=5;
                    Drawable nav_up=getResources().getDrawable(R.mipmap.icon_sort_down);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    cb_price.setCompoundDrawables(null, null, nav_up, null);
                }else{
                    index=6;
                    Drawable nav_up=getResources().getDrawable(R.mipmap.icon_sort_up);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    cb_price.setCompoundDrawables(null, null, nav_up, null);
                }
                mPresenter.getData();
            }
        });



        return view;
    }

    @Override
    public SearchResultPresenter initPresenter() {
        return new SearchResultPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    public void setAll(){
        tv_hot.setTextColor(getResources().getColor(R.color.find_text));
        cb_sale.setTextColor(getResources().getColor(R.color.find_text));
        cb_price.setTextColor(getResources().getColor(R.color.find_text));
        Drawable nav_up=getResources().getDrawable(R.mipmap.icon_sort_normal);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        cb_sale.setCompoundDrawables(null, null, nav_up, null);
        cb_price.setCompoundDrawables(null, null, nav_up, null);
    }

    @Override
    public void onClick(View v) {
        setAll();
        switch (v.getId()){
            case R.id.tv_hot:
                page=1;
                index=2;
                tv_hot.setTextColor(getResources().getColor(R.color.find_green_text));
                break;
        }
        mPresenter.getData();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Logger.i("点击每一个商品");
        SearchFarmModel.DataBean bean=mPresenter.data.get(i);

    }
}
