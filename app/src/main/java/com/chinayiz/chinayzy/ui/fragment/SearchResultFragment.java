package com.chinayiz.chinayzy.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.SearchResultAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.presenter.SearchResultPresenter;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.widget.SearchPopuwindow;
import com.orhanobut.logger.Logger;

/**
 * 搜索结果
 */
@SuppressLint("ValidFragment")
public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {
    private TextView tv_hot;
    public GridView gd_list;
    public CheckBox cb_sale,cb_price,cb_brand,tv_self,tv_credit;
    public SearchResultAdaphter adaphter;
    public SearchResultAdaphter adaphter2;
    public PullToRefreshLayout refresh_view;
    public String title;
    public int index=2;
    public boolean isList=true;  //是否是列表排列
    public int type=1;
    public int page=1;
    public int mPostion;
    public ImageView mIvActionBarCart;
    public LinearLayout ll_two;
    public String isself="0";
    public String credit="0";
    public String brands="";
    public boolean isMail;


    @Override
    protected void onVisible() {
    }


    @Override
    protected void onInvisible() {
    }

    @Override
    public void onInitActionBar(final BaseActivity activity) {
        activity.mActionBar.setVisibility(View.VISIBLE);
        if (!isMail){
            activity.mIvActionBarCart.setVisibility(View.VISIBLE);
            activity.mIvActionBarCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UserSeeion.isLogin(getActivity())) {
                        Skip.toShopCart(getActivity());
                    }
                }
            });
            activity.mIvActionBarMore.setVisibility(View.VISIBLE);
            activity.mIvActionBarMore.setImageResource(R.mipmap.icon_gridview);
            activity.mIvActionBarMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isList){
                        activity.mIvActionBarMore.setImageResource(R.mipmap.icon_listview);
                        isList=false;
                        type=2;
                        gd_list.setNumColumns(2);
                        adaphter2.setData(mPresenter.data,2);
                        gd_list.setAdapter(adaphter2);
                    }else {
                        activity.mIvActionBarMore.setImageResource(R.mipmap.icon_gridview);
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
        activity.mTvActionBarTitle.setText(title);
    }

    @Override
    public void onInintData(Bundle bundle) {
        if (bundle.getString("itemCode")!=null){  //大众商城
            title="商城购物";
            isMail=true;
        }else {
            title=bundle.getString("titel","-1");
            isMail=false;
        }
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
        tv_self= (CheckBox) view.findViewById(R.id.tv_self);
        tv_credit= (CheckBox) view.findViewById(R.id.tv_credit);
        cb_brand= (CheckBox) view.findViewById(R.id.cb_brand);
        ll_two= (LinearLayout) view.findViewById(R.id.ll_two);
        tv_self.setOnClickListener(this);
        tv_credit.setOnClickListener(this);
        tv_hot.setOnClickListener(this);
        adaphter.setRefreshLayout(refresh_view);
        adaphter2.setRefreshLayout(refresh_view);
        gd_list.setAdapter(adaphter);
        gd_list.setOnItemClickListener(this);
        tv_self.setOnCheckedChangeListener(this);
        tv_credit.setOnCheckedChangeListener(this);
        cb_brand.setOnCheckedChangeListener(this);
        cb_price.setOnCheckedChangeListener(this);
        cb_sale.setOnCheckedChangeListener(this);
        refresh_view.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                page=1;
                mPresenter.getData();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                page++;
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

    //将所有的设置成不选中状态
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
        switch (v.getId()){
            case R.id.tv_hot:
                setAll();
                page=1;
                index=2;
                tv_hot.setTextColor(getResources().getColor(R.color.search_selected));
                mPresenter.getData();
                break;
        }

    }

    private void setCheck(TextView tv,boolean isChecked){
        if (isChecked){
            Drawable nav_up2=getResources().getDrawable(R.mipmap.icon_checked);
            nav_up2.setBounds(0, 0, nav_up2.getMinimumWidth(), nav_up2.getMinimumHeight());
            tv.setCompoundDrawables(nav_up2,null,null,null);
            tv.setTextColor(Color.parseColor("#ff3952"));
            tv.setBackgroundResource(R.drawable.shape_corner_red_solid);
        }else {
//        Drawable down=getResources().getDrawable(R.mipmap.icon_down);
//        down.setBounds(0, 0, down.getMinimumWidth(), down.getMinimumHeight());
//        cb_brand.setCompoundDrawables(null,null,down,null);
            tv.setTextColor(getResources().getColor(R.color.find_text));
            tv.setBackgroundResource(R.drawable.shape_corner_search);
            tv.setCompoundDrawables(null,null,null,null);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Logger.i("点击每一个商品");
        SearchFarmModel.DataBean bean=mPresenter.data.get(i);
        Skip.toNewGoodsDetail(getActivity(),bean.getGoodsid()+"");
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean b) {

        switch (buttonView.getId()){
            case R.id.tv_self:   //自营
                setCheck(tv_self,b);
                if (b){
                    isself="1";
                }else {
                    isself="0";
                }
                mPresenter.getData();
                break;
            case R.id.tv_credit:  //信用
                setCheck(tv_credit,b);
                if (b){
                    credit="1";
                }else {
                    credit="0";
                }
                mPresenter.getData();
                break;
            case R.id.cb_price://价格
                setAll();
                page=1;
                cb_price.setTextColor(getResources().getColor(R.color.search_selected));
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
                break;
            case R.id.cb_brand://品牌
                    cb_brand.setBackgroundResource(R.mipmap.icon_search_brand_selected);
                    Drawable nav=getResources().getDrawable(R.mipmap.icon_up);
                    nav.setBounds(0, 0, nav.getMinimumWidth(), nav.getMinimumHeight());
                    cb_brand.setCompoundDrawables(null, null, nav, null);
                SearchPopuwindow popupWindow;
                if (mPresenter.list_brands==null){
                    popupWindow= new SearchPopuwindow(getActivity(),title,isMail);
                }else {
                    popupWindow=new SearchPopuwindow(getActivity(),title,mPresenter.list_brands,isMail);
                }
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            Drawable nav_up=getResources().getDrawable(R.mipmap.icon_down);
                            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                            cb_brand.setCompoundDrawables(null, null, nav_up, null);
                            cb_brand.setBackgroundResource(R.drawable.shape_corner_search);
                        }
                    });

                    popupWindow.showAsDropDown(ll_two);
                break;
            case R.id.cb_sale:  //销量
                setAll();
                page=1;
                cb_sale.setTextColor(getResources().getColor(R.color.search_selected));
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
                break;
        }
    }
}
