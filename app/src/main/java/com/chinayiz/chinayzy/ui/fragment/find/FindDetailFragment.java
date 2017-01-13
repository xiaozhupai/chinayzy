package com.chinayiz.chinayzy.ui.fragment.find;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.FindDetailPresenter;
import com.chinayiz.chinayzy.widget.ShareDialog;

/**发现详情
 * A simple {@link Fragment} subclass.
 */
public class FindDetailFragment extends BaseFragment<FindDetailPresenter> implements View.OnClickListener {


    private ImageView iv_love;
    private LinearLayout lv_love;
    private LinearLayout lv_keep;
    private LinearLayout lv_share;
    private LinearLayout lv_find_detail_bottom;
    private ShareDialog dialog;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_find_detail, null);
        iv_love = (ImageView) view.findViewById(R.id.iv_love);
        iv_love.setOnClickListener(this);
        lv_love = (LinearLayout) view.findViewById(R.id.lv_love);
        lv_love.setOnClickListener(this);
        lv_keep = (LinearLayout) view.findViewById(R.id.lv_keep);
        lv_keep.setOnClickListener(this);
        lv_share = (LinearLayout) view.findViewById(R.id.lv_share);
        lv_share.setOnClickListener(this);
        lv_find_detail_bottom = (LinearLayout) view.findViewById(R.id.lv_find_detail_bottom);
        lv_find_detail_bottom.setOnClickListener(this);
        return view;

    }

    @Override
    public FindDetailPresenter initPresenter() {
        return new FindDetailPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
        View view = View.inflate(getActivity(), R.layout.fragment_find_detail, null);
        initView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv_love:  //点赞

                break;
            case R.id.lv_keep:  //收藏

                break;
            case R.id.lv_share:   //分享
                if (dialog==null){
                    dialog=new ShareDialog(mContext);
                }
                dialog.show();

                break;
        }
    }
}
