package com.chinayiz.chinayzy.ui.common;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.GoodsTypeMeunAdapter;
import com.chinayiz.chinayzy.adapter.StoreHomeAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.StoreInfo;
import com.chinayiz.chinayzy.entity.response.StoreGoodsListModel;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;
import com.chinayiz.chinayzy.presenter.StorePresenter;
import com.chinayiz.chinayzy.views.GlideRoundTransform;
import com.chinayiz.chinayzy.views.MyDecoration;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/5 11:15
 * Class StoreFragment 店铺主页
 */

public class StoreFragment extends BaseFragment<StorePresenter> implements View.OnClickListener, StoreHomeAdapter.OnRecyclerViewItemClickListener, AdapterView.OnItemClickListener {
    public StoreInfoModel mStoreInfoModel;
    public List<StoreInfoModel.DataBean.TypecodeBean> mTypecodeList = new ArrayList<>();
    public List<StoreGoodsListModel.DataBean> mDataList = new ArrayList<>();
    private TextView mTvSort, mAcctionBarTitle, mTvGoodsType;
    public StoreInfo mStoreInfo;
    public View mProgress;
    public GoodsTypeMeunAdapter mGoodsTypeMeunAdapter;
    public StoreHomeAdapter mAdapter;
    public String mStoreID;
    public RecyclerView mRvStoreHome;
    private PopupWindow mPopupWindow;
    private View popupWindowview;
    private View mNvGoodsMenu2;
    private ListView mTypeListView;
    private View mView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_store,container,false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        mAdapter = new StoreHomeAdapter();
        mAdapter.setOnItemClickListener(this);


        mProgress =  view.findViewById(R.id.ll_progress);
        mTvSort = (TextView)  view.findViewById(R.id.tv_sort);
        mTvGoodsType = (TextView)  view.findViewById(R.id.tv_goodsType);
        mNvGoodsMenu2 =  view.findViewById(R.id.nv_goodsMenu2);
        mRvStoreHome = (RecyclerView)  view.findViewById(R.id.rv_StoreHome);
        //设置网格布局管理器
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //添加headview核心代码
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //如果位置是0，那么这个条目将占用SpanCount()这么多的列数，在此也就是2
                //而如果不是0，则说明不是Header，就占用1列即可
                return mAdapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
            }
        });
        mRvStoreHome.setLayoutManager(layoutManager);
        //设置分割线
        mRvStoreHome.addItemDecoration(new MyDecoration());
        //悬浮菜单核心代码
        mRvStoreHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstVisibleItemPosition() == 0) {
                    mView = layoutManager.findViewByPosition(0);
                    if (mView.getBottom() <= 100) {
                        mNvGoodsMenu2.setVisibility(View.VISIBLE);
                    } else if (mView.getBottom() > 100) {
                        mNvGoodsMenu2.setVisibility(View.INVISIBLE);
                    }
                } else {
                    mNvGoodsMenu2.setVisibility(View.VISIBLE);
                }
            }
        });

        mTvSort.setOnClickListener(this);
        mTvGoodsType.setOnClickListener(this);
        popupWindowview = View.inflate(getActivity(), R.layout.store_goods_typelist, null);
        mTypeListView = (ListView) popupWindowview.findViewById(R.id.lv_GoodsTypeList);
    }
    @Override
    public void onInintData(Bundle bundle) {
        mStoreID=bundle.getString("storeID");
        if (mStoreID==null){BaseActivity.showToast(getActivity(),"未知错误，请重试");}
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sort://智能排序
                mAdapter.noopsycheSort();
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_goodsType://类型选择
                showTypeMeun(v);
                break;
            case R.id.iv_brand://品牌信息
                showBrandInfo();
                break;
        }
    }
    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mIvActionBarMore.setClickable(false);
       activity.mIvActionBarMore.setVisibility(View.GONE);
        mActivity=activity;
    }

    @Override
    public StorePresenter initPresenter() {
        return new StorePresenter();
    }
    /**
     * 显示品牌信息
     */
    public void showBrandInfo() {
        View mDialogView;
        AlertDialog mDialog;
        AlertDialog.Builder mDialogBuilder;
        TextView name;
        TextView info;
        ImageView logo;
        mDialogBuilder = new AlertDialog.Builder(getActivity());
        mDialogView = View.inflate(getActivity(), R.layout.dialog_storeinfo, null);

        name = (TextView) mDialogView.findViewById(R.id.tv_stareName);
        name.setText(mStoreInfoModel.getData().getSname());
        info = (TextView) mDialogView.findViewById(R.id.tv_storeInfo);
        info.setText(mStoreInfoModel.getData().getIntroduction());
        logo = (ImageView) mDialogView.findViewById(R.id.iv_storeLogo);

        Glide.with(this)
                .load(mStoreInfoModel.getData().getPic())
                //设置logo圆角
                .transform(new GlideRoundTransform(getActivity(), 8))
                .into(logo);
        mDialog = mDialogBuilder.setView(mDialogView)
                .setCancelable(true)
                .create();
        mDialog.show();
    }
    /**
     * 显示类型菜单
     *
     * @param view
     */
    public void showTypeMeun(View view) {
        mTypeListView.setAdapter(mGoodsTypeMeunAdapter);
        mTypeListView.setOnItemClickListener(this);
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(popupWindowview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                    // 这里如果返回true的话，touch事件将被拦截
                    // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                }
            });
            //要为popWindow设置一个背景才有效
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        }
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        mPopupWindow.showAsDropDown(view, 0, 3);
    }
    @Override
    protected void onVisible() {

    }
    @Override
    /**
     * 点击店铺商品
     */
    public void onItemClick(View view, String goodsID) {
        Skip.toGoodsDetail(getActivity(),goodsID);
        Logger.i("点击店铺商品发送消息！="+goodsID);
    }
    @Override
    protected void onInvisible() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view.findViewById(R.id.tv_GoodsTypeName);
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        mTvGoodsType.setText(textView.getText());
        //设置适配器中的悬浮头指示
        mAdapter.getHomeHead().setType(textView.getText().toString());
        //请求不同类型
        mPresenter.mRequestUtils.getGoodsListByPosition(mStoreID
                , mGoodsTypeMeunAdapter.getTypecodeList().get(position).getTypecode(), "1", "16");
    }
}
