package com.chinayiz.chinayzy.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.CommentListAdapter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.CommentListModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/27 9:47
 * Class CommentsFragment 评论列表
 */
public class CommentsFragment extends Fragment{
    /**
     * 评论列表启动
     */
    public static final int START=1;

    private ListView mCommentList;
    private CommentListAdapter mAdapter;
    private StateListener mStateListener;

    public void setStateListener(StateListener listener){
        mStateListener=listener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_comments, container,false);
        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mStateListener.stateChange(START);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mStateListener.stateChange(-1);
    }

    private void initView(View view) {
        mAdapter=new CommentListAdapter(this);
        mCommentList= (ListView) view.findViewById(R.id.lv_comments);
        mCommentList.setAdapter(mAdapter);
    }

    public void setCommentData(EventMessage message){
        CommentListModel model= (CommentListModel) message.getData();
        mAdapter.setCommentDatas(model.getData().getCommentlist());
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 监听评论列表状态
     */
    public interface StateListener{
        void stateChange(int stateCode);
    }
}
