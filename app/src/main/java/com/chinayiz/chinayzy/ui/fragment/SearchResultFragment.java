package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.SearchResultAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SearchResultPresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果
 */
public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements View.OnClickListener {
    private TextView tv_hot;
    private TextView tv_sale;
    private TextView tv_price;
    public ListView lv_list;
    public GridView gd_list;
    public SearchResultAdaphter adaphter;
    public SearchResultAdaphter adaphter2;
    public static final String TITLE="TITLE";
    public List<Fragment> fragments=new ArrayList<>();
    public String title;
    public int index=1;
    public NongYeMainActivity activity;
    public boolean isList=true;  //是否是列表排列

    public SearchResultFragment(String title) {
        this.title=title;
    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void onInvisible() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, null);
        gd_list= (GridView) view.findViewById(R.id.gd_list);
        tv_hot = (TextView) view.findViewById(R.id.tv_hot);
        tv_hot.setOnClickListener(this);
        tv_sale = (TextView)view.findViewById(R.id.tv_sale);
        tv_sale.setOnClickListener(this);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_price.setOnClickListener(this);
        lv_list= (ListView) view.findViewById(R.id.lv_list);
        List list=new ArrayList();
        adaphter=new SearchResultAdaphter(list,1,getActivity());
        adaphter2=new SearchResultAdaphter(list,2,getActivity());
        lv_list.setAdapter(adaphter);
        gd_list.setAdapter(adaphter2);

        activity= (NongYeMainActivity) getActivity();
        activity.mTvActionBarTitle.setText(title);
        activity.mIvActionBarMore.setImageResource(R.mipmap.icon_listview);
        activity.mIvActionBarMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isList){
                    activity.mIvActionBarMore.setImageResource(R.mipmap.icon_gridview);
                    isList=false;
                    lv_list.setVisibility(View.GONE);
                    gd_list.setVisibility(View.VISIBLE);
                }else {
                    activity.mIvActionBarMore.setImageResource(R.mipmap.icon_listview);
                    isList=true;
                    lv_list.setVisibility(View.VISIBLE);
                    gd_list.setVisibility(View.GONE);
                }
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
        tv_sale.setTextColor(getResources().getColor(R.color.find_text));
        tv_price.setTextColor(getResources().getColor(R.color.find_text));
    }

    @Override
    public void onClick(View v) {
        setAll();
        switch (v.getId()){
            case R.id.tv_hot:
                index=1;
                tv_hot.setTextColor(getResources().getColor(R.color.find_green_text));
                break;
            case R.id.tv_sale:
                index=2;
                tv_sale.setTextColor(getResources().getColor(R.color.find_green_text));
                break;
            case R.id.tv_price:
                index=3;
                tv_price.setTextColor(getResources().getColor(R.color.find_green_text));
                break;
        }
        mPresenter.getData();
    }
}
