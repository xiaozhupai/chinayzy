package com.chinayiz.chinayzy.ui.common;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
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
import com.chinayiz.chinayzy.adapter.GoodsTypeMeunAdapter;
import com.chinayiz.chinayzy.adapter.StoreHomeAdapter;
import com.chinayiz.chinayzy.adapter.viewHolder.StoreHomeHead;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.StoreInfo;
import com.chinayiz.chinayzy.entity.response.StoreGoodsListModel;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.chinayiz.chinayzy.views.GlideRoundTransform;
import com.chinayiz.chinayzy.views.MyDecoration;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/4 15:32
 * StoreActivity  店铺首页
 */
public class StoreActivity extends AppCompatActivity implements EventBusCallback,
        View.OnClickListener, AdapterView.OnItemClickListener, StoreHomeAdapter.OnRecyclerViewItemClickListener {
    public StoreInfoModel mStoreInfoModel;
    public CommonRequestUtils mRequestUtils = CommonRequestUtils.getRequestUtils();
    public List<StoreInfoModel.DataBean.TypecodeBean> mTypecodeList = new ArrayList<>();
    private List<StoreGoodsListModel.DataBean> mDataList = new ArrayList<>();
    private TextView mTvSort, mAcctionBarTitle, mTvGoodsType;
    private StoreInfo mStoreInfo;
    private View mProgress;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        EventBus.getDefault().register(this);
        Intent intent=getIntent();
        mStoreID=intent.getStringExtra("storeID");
        if (mStoreID==null){throw new RuntimeException("店铺ID为空，启动失败");}
        mRequestUtils.getStoerInfo(mStoreID);
        initView();
    }

    private void initView() {
        mAdapter = new StoreHomeAdapter();
        mAdapter.setOnItemClickListener(this);

        mAcctionBarTitle = (TextView) findViewById(R.id.tv_actionbar_title);
        ImageView back = (ImageView) findViewById(R.id.iv_back_button);
        back.setOnClickListener(this);
        findViewById(R.id.iv_more_button).setVisibility(View.GONE);
        mProgress = findViewById(R.id.ll_progress);
        mTvSort = (TextView) findViewById(R.id.tv_sort);
        mTvGoodsType = (TextView) findViewById(R.id.tv_goodsType);
        mNvGoodsMenu2 = findViewById(R.id.nv_goodsMenu2);
        mRvStoreHome = (RecyclerView) findViewById(R.id.rv_StoreHome);
        //设置网格布局管理器
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
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
        popupWindowview = View.inflate(this, R.layout.store_goods_typelist, null);
        mTypeListView = (ListView) popupWindowview.findViewById(R.id.lv_GoodsTypeList);
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
            case R.id.iv_back_button: {//返回键
                onBackPressed();
            }

        }
    }

    /**
     * 显示品牌信息
     */
    private void showBrandInfo() {
        View mDialogView;
        AlertDialog mDialog;
        AlertDialog.Builder mDialogBuilder;
        TextView name;
        TextView info;
        ImageView logo;
        mDialogBuilder = new AlertDialog.Builder(this);
        mDialogView = View.inflate(this, R.layout.dialog_storeinfo, null);

        name = (TextView) mDialogView.findViewById(R.id.tv_stareName);
        name.setText(mStoreInfoModel.getData().getSname());
        info = (TextView) mDialogView.findViewById(R.id.tv_storeInfo);
        info.setText(mStoreInfoModel.getData().getIntroduction());
        logo = (ImageView) mDialogView.findViewById(R.id.iv_storeLogo);

        Glide.with(this)
                .load(mStoreInfoModel.getData().getPic())
                //设置logo圆角
                .transform(new GlideRoundTransform(this, 8))
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
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.STORE_HOME: {
                mStoreInfoModel = (StoreInfoModel) message.getData();
                mGoodsTypeMeunAdapter = new GoodsTypeMeunAdapter(this);
                //逆向排序分类数据
                Collections.reverse(mStoreInfoModel.getData().getTypecodelist());
                mGoodsTypeMeunAdapter.setTypecodeList(mStoreInfoModel.getData().getTypecodelist());
                mGoodsTypeMeunAdapter.notifyDataSetChanged();
                if (!mStoreInfoModel.getData().getTypecodelist().isEmpty()) {
                    mRequestUtils.getGoodsListByPosition(mStoreID
                            , mGoodsTypeMeunAdapter.getTypecodeList().get(0).getTypecode(), "1", "16");
                }
                mStoreInfo = new StoreInfo(mStoreInfoModel.getData().getIsself()
                        , mStoreInfoModel.getData().getIsattention()
                        , mStoreInfoModel.getData().getPic()
                        , mStoreInfoModel.getData().getBigicon()
                        , mStoreInfoModel.getData().getSname()
                        , mStoreInfoModel.getData().getIntroduction());
                //标题设置加粗
                TextPaint tp = mAcctionBarTitle.getPaint();
                tp.setFakeBoldText(true);
                mAcctionBarTitle.setText(mStoreInfo.getSname());
                mAdapter.setData(mDataList, mStoreInfo);
                mAdapter.notifyDataSetChanged();
                mRvStoreHome.setAdapter(mAdapter);
                break;
            }
            case Commons.FORTYPEBY_GOODSS: {
                mProgress.setVisibility(View.GONE);
                StoreGoodsListModel model = (StoreGoodsListModel) message.getData();
                mDataList = model.getData();
                mAdapter.setData(mDataList);
                mAdapter.notifyDataSetChanged();
                break;
            }
            //悬浮头视图点击事件
            case StoreHomeHead.CLICK: {
                onClick((View) message.getData());
                break;
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()) {
            case StoreHomeHead.CHECKED: {//关注商店
                if ((boolean) message.getData()) {
                    mRequestUtils.doAttentionStore(mStoreID);
                } else {
                    mRequestUtils.doUnAttentionStore(mStoreID);
                }
                break;
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (EventMessage.NET_EVENT == message.getEventType()) {
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (EventMessage.INFORM_EVENT == message.getEventType()) {
            disposeInfoMsg(message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRequestUtils = null;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view.findViewById(R.id.tv_GoodsTypeName);
        Logger.i("点击了：=" + textView.getText());
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        mTvGoodsType.setText(textView.getText());
        //设置适配器中的悬浮头指示
        mAdapter.getHomeHead().setType(textView.getText().toString());
        //请求不同类型
        mRequestUtils.getGoodsListByPosition(mStoreID
                , mGoodsTypeMeunAdapter.getTypecodeList().get(position).getTypecode(), "1", "16");
    }

    @Override
    /**
     * 点击店铺商品
     */
    public void onItemClick(View view, String goodsID) {
        Intent intent=new Intent(this,GoodsActivity.class);
        startActivity(intent);
        EventBus.getDefault()
                .post(new EventMessage(BaseMessage.NET_EVENT
                        ,GoodsActivity.REFRESH,goodsID));
        Logger.i("点击店铺商品发送消息！="+goodsID);
    }
}
