package com.chinayiz.chinayzy.ui.common;

import android.annotation.SuppressLint;
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
import com.chinayiz.chinayzy.net.CommonRequestUtils;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/27 9:47
 * Class CommentsFragment 评论列表
 */
@SuppressLint("ValidFragment")
public class CommentsFragment extends Fragment{
    /**
     * 评论列表启动
     */
    public static final int START=1;
    private ListView mCommentList;
    private CommentListAdapter mAdapter;
    private View mView;
    private CommentListModel mModel;
    private boolean islaoad=false;
    private int coun=0;
    private String goodsID;

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_comments, container,false);
        initView(view);
        return view;
    }

    public void setCoun(int coun) {
        this.coun = coun;
    }

    public void setPager(String goodsid){
        if (coun==0){
            if (mView!=null){
                mView.setVisibility(View.VISIBLE);
            }
        }else {
            if (!islaoad) {
                CommonRequestUtils.getRequestUtils().getCommentList(goodsid,"1","20");
            }
        }
    }
    public static CommentsFragment getInstance() {
        return new CommentsFragment();
    }
    
    private void initView(View view) {
        mAdapter=new CommentListAdapter(this);
        mView=view.findViewById(R.id._ll_nullComment);
        mCommentList= (ListView) view.findViewById(R.id.lv_comments);
        mCommentList.setAdapter(mAdapter);
    }

    public void setCommentData(EventMessage message){
        mModel = (CommentListModel) message.getData();
        mAdapter.setCommentDatas(mModel.getData().getCommentlist());
        mAdapter.notifyDataSetChanged();
    }

}
