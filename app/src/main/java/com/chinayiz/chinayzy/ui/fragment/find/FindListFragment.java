package com.chinayiz.chinayzy.ui.fragment.find;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.FindAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.FindListPresenter;
import com.chinayiz.chinayzy.views.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.PullableGridView;
import java.util.ArrayList;
import java.util.List;

/** 发现列表
 * A simple {@link Fragment} subclass.
 */

public class FindListFragment extends BaseFragment<FindListPresenter> implements AdapterView.OnItemClickListener {
    private PullableGridView gd_find_list;
    private FindAdaphter adaphter;
    private PullToRefreshLayout pullToRefreshLayout;


    @Override
    protected void onVisible() {
    }

    @Override
    protected void onInvisible() {
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view=initView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_find_list,container,false);
        pullToRefreshLayout= (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        gd_find_list= (PullableGridView) view.findViewById(R.id.gd_find_list);
        gd_find_list.setOnItemClickListener(this);
        List list=new ArrayList();

        for (int i=0;i<10;i++){
            list.add("dsds");
        }

        adaphter=new FindAdaphter(mContext,list);
        gd_find_list.setAdapter(adaphter);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                Toast.makeText(mContext,"refresh",Toast.LENGTH_LONG).show();
                pullToRefreshLayout.refreshFinish(0);
            }


            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                Toast.makeText(mContext,"refresh",Toast.LENGTH_LONG).show();
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });
        return gd_find_list;
    }

    @Override
    public FindListPresenter initPresenter() {
        return new FindListPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startFragment(new FindDetailFragment());
    }
}
