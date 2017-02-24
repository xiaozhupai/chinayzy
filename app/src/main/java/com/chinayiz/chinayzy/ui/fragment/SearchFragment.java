package com.chinayiz.chinayzy.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.database.SearchDao;
import com.chinayiz.chinayzy.presenter.SearchPresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.chinayiz.chinayzy.views.SearchEditText;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.chinayiz.chinayzy.widget.Tag;
import com.chinayiz.chinayzy.widget.TagListView;
import com.chinayiz.chinayzy.widget.TagView;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment<SearchPresenter> implements View.OnClickListener {
    private SearchEditText sv_search;
    private TextView tv_cancel;
    public TagListView tagview;
    private ImageView iv_delete;
    public TagListView tagview2;
    public MessageDialog dialog;
    public List<Tag> data_hot=new ArrayList<>();
    public List<Tag> data_search=new ArrayList<>();


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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);
        sv_search = (SearchEditText) view.findViewById(R.id.sv_search);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tagview = (TagListView) view.findViewById(R.id.tagview);
        tagview.setOnClickListener(this);
        iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(this);
        tagview2 = (TagListView) view.findViewById(R.id.tagview2);
        tagview2.setOnClickListener(this);



        tagview.setOnTagClickListener(new TagListView.OnTagClickListener() {
            @Override
            public void onTagClick(TagView tagView, Tag tag) {
                if (!SearchDao.findTitle(tag.getTitle())){
                    SearchDao.add(tag.getTitle());
                    data_search.add(tag);
                    tagview2.setTags(data_search);
                }
                mPresenter.toResult(tag.getTitle());
                Logger.i("热门标签");
            }
        });

        tagview2.setOnTagClickListener(new TagListView.OnTagClickListener() {
            @Override
            public void onTagClick(TagView tagView, Tag tag) {
                Logger.i("历史标签");
                mPresenter.toResult(tag.getTitle());
            }
        });
        sv_search.setOnSearchClickListener(new SearchEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                String query=sv_search.getText().toString().trim();
                Logger.i("搜索");
                if (!SearchDao.findTitle(query)){
                    SearchDao.add(query);
                    Tag tag=new Tag();
                    tag.setTitle(query);
                    data_search.add(tag);
                    tagview2.setTags(data_search);

                }
                mPresenter.toResult(query);
            }
        });

        return view;
    }


    @Override
    public SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NongYeMainActivity activity= (NongYeMainActivity) getActivity();
        activity.mActionBar.setVisibility(View.GONE);

        View view = initView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                getFragmentManager().popBackStack();
                break;
            case R.id.iv_delete:
                if (dialog==null){
                    dialog=new MessageDialog(getActivity());
                }
                dialog.setMessage("确定删除搜索历史吗?");
                dialog.setButton1("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.i("取消");
                        dialog.dismiss();
                    }
                });
                dialog.setButton2("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.i("确定");
                        mPresenter.removeAll();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
    }
}
