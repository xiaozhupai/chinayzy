package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.CommentListModel;
import com.chinayiz.chinayzy.views.GlideRoundTransform;
import com.hedgehog.ratingbar.RatingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/27 10:35
 * Class CommentListAdapter 评论列表适配器
 */
public class CommentListAdapter extends BaseAdapter {
    private List<CommentListModel.DataBean.CommentlistBean> mDataList = new ArrayList<>();
    private Fragment mFragment;
    private ViewHolder mHolder;

    public CommentListAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    public void setCommentDatas(List<CommentListModel.DataBean.CommentlistBean> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentListModel.DataBean.CommentlistBean data = mDataList.get(position);
        String[]  picUrl=null;
        View view;
        int sum = data.getDeliverypoint() + data.getServicepoint() + data.getDescpoint();
        if (convertView == null) {
            mHolder = new ViewHolder();
            view = View.inflate(mFragment.getActivity(), R.layout.comment_item, null);
            mHolder.mRatingBar = (RatingBar) view.findViewById(R.id.rb_commentScore);
            mHolder.mUserIcon = (ImageView) view.findViewById(R.id.iv_userIcon);
            mHolder.mUserName = (TextView) view.findViewById(R.id.tv_userName);
            mHolder.mCreateDate = (TextView) view.findViewById(R.id.tv_createDate);
            mHolder.mCommentContent = (TextView) view.findViewById(R.id.tv_commentContent);
            mHolder.mCommentPics = view.findViewById(R.id.ll_commentPics);
            mHolder.mCommentImages.add((ImageView) view.findViewById(R.id.iv_commentPic1));
            mHolder.mCommentImages.add((ImageView) view.findViewById(R.id.iv_commentPic2));
            mHolder.mCommentImages.add((ImageView) view.findViewById(R.id.iv_commentPic3));
            mHolder.mCommentImages.add((ImageView) view.findViewById(R.id.iv_commentPic4));
            view.setTag(mHolder);
        } else {
            view = convertView;
            mHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mFragment)
                .load(data.getPic())
                .transform(new GlideRoundTransform(mFragment.getActivity()))
                .into(mHolder.mUserIcon);
        mHolder.mRatingBar.setStar(sum / 3);
        mHolder.mCommentContent.setText(data.getCommentscontent());
        mHolder.mCreateDate.setText(data.getCreatetime());
        char[] chars;
        if (data.getNickname() != null) {//名字是否为空
            if ("1".equals(data.getIsanonymity())) {//是否匿名
                if (data.getNickname() != null) {

                }
                chars = data.getNickname().toCharArray();
                mHolder.mUserName.setText(chars[0] + "***" + chars[chars.length - 1]);
            } else {
                mHolder.mUserName.setText(data.getNickname());
            }
        }

        if (data.getCpic()!=null){
            picUrl = data.getCpic().split(",");
        }
        if (picUrl!=null&&picUrl.length != 0) {//判断是否有评论图片
            for (int i = 0; i < picUrl.length; i++) {
                Glide.with(mFragment)
                        .load(picUrl[i])
                        .transform(new GlideRoundTransform(mFragment.getActivity()))
                        .into(mHolder.mCommentImages.get(i));
            }
        } else {
            mHolder.mCommentPics.setVisibility(View.GONE);
        }
        return view;
    }

    private class ViewHolder {
        RatingBar mRatingBar;
        ImageView mUserIcon;
        List<ImageView> mCommentImages = new ArrayList<>(4);
        TextView mUserName, mCommentContent, mCreateDate;
        View mCommentPics;
    }
}
