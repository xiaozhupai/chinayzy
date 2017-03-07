package com.chinayiz.chinayzy.ui.fragment;

import android.annotation.SuppressLint;
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
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.SearchResultPresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.chinayiz.chinayzy.ui.fragment.find.FindDetailFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

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
    public static final String TITLE="TITLE";
    public String title;
    public int index=2;
    public NongYeMainActivity activity;
    public boolean isList=true;  //是否是列表排列
    public int type=1;

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
        cb_sale= (CheckBox) view.findViewById(R.id.cb_sale);
        cb_price= (CheckBox) view.findViewById(R.id.cb_price);
        tv_hot = (TextView) view.findViewById(R.id.tv_hot);
        tv_hot.setOnClickListener(this);
        final List list=new ArrayList();
        adaphter=new SearchResultAdaphter(list,1,getActivity());
        adaphter2=new SearchResultAdaphter(list,2,getActivity());
        gd_list.setAdapter(adaphter);
        gd_list.setOnItemClickListener(this);
        cb_sale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
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
        activity= (NongYeMainActivity) getActivity();
        activity.mTvActionBarTitle.setText(title);
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
                index=2;
                tv_hot.setTextColor(getResources().getColor(R.color.find_green_text));
                break;
        }
        mPresenter.getData();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Logger.i("点击每一个商品");
//       startFragment(new FindDetailFragment("1"),"FindDetailFragment");
    }
}
