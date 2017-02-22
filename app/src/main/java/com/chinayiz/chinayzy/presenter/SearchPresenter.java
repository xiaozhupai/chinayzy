package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.database.SearchDao;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.SearchLabelModel;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.SearchFragment;
import com.chinayiz.chinayzy.ui.fragment.SearchResultFragment;
import com.chinayiz.chinayzy.widget.Tag;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/** 搜索
 * Created by Administrator on 2017/1/4.
 */

public class SearchPresenter extends BasePresenter<SearchFragment> {
    private Net net=Net.getNet();
    public static final String TITLE="TITLE";
    @Override
    public void onCreate() {
        net.getALLTab();
        List<String> data=SearchDao.findall();
        for (int i=0;i<data.size();i++){
            Tag tag=new Tag();
            tag.setTitle(data.get(i));
            mView.data_search.add(tag);
        }
        mView.tagview2.setTags(mView.data_search);
    }

    @Override
    public void onDestroy() {

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        switch (message.getDataType()){
            case  Contants.GETSEARCHKEY:   //获得所有标签
                SearchLabelModel model2= (SearchLabelModel) message.getData();
                mView.data_hot.clear();
                for (String title: model2.getData().getHotlist()) {
                    Tag tag=new Tag();
                    tag.setTitle(title);
                    mView.data_hot.add(tag);
                }
                mView.tagview.setTags(mView.data_hot);
                break;
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    /**
     * 删除所有的历史记录
     */
    public void removeAll(){
        mView.data_search.clear();
        SearchDao.deleteAll();
        mView.tagview2.setTags(mView.data_search);
    }

    /**
     * 跳转到搜索结果页面
     * @param title
     */
    public void toResult(String title){
        SearchResultFragment fragment=new SearchResultFragment(title);
        mView.startFragment(fragment,fragment.getTag());

    }


}
