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
 * Class CommentsFragment
 */
public class CommentsFragment extends Fragment{
    private ListView mCommentList;
    private CommentListAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_comments, container,false);
        initView(view);
        return view;
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
}
