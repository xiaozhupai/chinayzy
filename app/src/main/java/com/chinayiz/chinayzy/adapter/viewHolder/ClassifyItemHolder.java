package com.chinayiz.chinayzy.adapter.viewHolder;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.entity.response.ClassifyCodesModel;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/1 9:16
 * Class ClassifyItemHolder
 */
public class ClassifyItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTextView;
    private ImageView mImageView;
    private Fragment mFragment;
    private View mView;
    private ClassifyCodesModel.DataBean mDataBean;
    public ClassifyItemHolder(View itemView) {
        super(itemView);
        mImageView= (ImageView) itemView.findViewById(R.id.iv_classifyPic);
        mTextView= (TextView) itemView.findViewById(R.id.tv_classifyName);
        mView=itemView;
        mView.setOnClickListener(this);
    }
    public void setDatas(Fragment fragment,ClassifyCodesModel.DataBean dataBean){
        mFragment=fragment;
        mDataBean=dataBean;
        Glide.with(fragment)
                .load(mDataBean.getTypepic())
                .into(mImageView);
        mTextView.setText(mDataBean.getItemname());
        mView.setTag(R.id.tag_click,mDataBean.getItemname());
        mView.setTag(R.id.tag_detailId,mDataBean.getItemcode());
    }
    @Override
    public void onClick(View v) {
        Logger.i("点击三级="+v.getTag(R.id.tag_detailId));

        String titel= (String) v.getTag(R.id.tag_click);
        Skip.toSearchResult(mFragment.getActivity(),titel);
    }
}
