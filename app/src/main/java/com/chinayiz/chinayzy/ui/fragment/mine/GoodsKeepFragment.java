package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.GoodsKeepAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.GoodsKeepPresenter;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableListView;

/**   宝贝收藏
 * A simple {@link Fragment} subclass.
 */
public class GoodsKeepFragment extends BaseFragment<GoodsKeepPresenter> {
    public PullableListView lv_list;
    public PullToRefreshLayout pullrefresh;
    public GoodsKeepAdaphter adaphter;
    public int page=1;


    @Override
    protected void onVisible() {
     if (isInit){
         adaphter.onGetData(1);
     }
        isInit=false;
    }


    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onInintData(Bundle bundle) {
        adaphter=new GoodsKeepAdaphter(mActivity,null);
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("宝贝收藏");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content_keep,null);
        pullrefresh= (PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        lv_list   =(PullableListView) view.findViewById(R.id.lv_list);
        adaphter.setRefreshLayout(pullrefresh);
        pullrefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                adaphter.onRefresh();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                adaphter.LoadMore();
            }
        });

        lv_list.setAdapter(adaphter);

        // step 1. create a MenuCreator
//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//
//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getActivity());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(dp2px(90));
//                // set a icon
//                deleteItem.setTitle("删除");
//                // add to menu
//                menu.addMenuItem(deleteItem);
//            }
//        };
//        lv_list.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
//
//        lv_list.setMenuCreator(creator);
//        lv_list.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                return true;
//            }
//        });
//        // 监测用户在ListView的SwipeMenu侧滑事件。
//        lv_list.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
//
//            @Override
//            public void onSwipeStart(int pos) {
//                Log.i("位置:" + pos, "开始侧滑...");
//            }
//
//            @Override
//            public void onSwipeEnd(int pos) {
//                Log.i("位置:" + pos, "侧滑结束.");
//            }
//        });
        return view;
    }

    @Override
    public GoodsKeepPresenter initPresenter() {
        return new GoodsKeepPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }



}
