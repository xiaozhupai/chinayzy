package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.WeightPresenter;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.orhanobut.logger.Logger;

/**
 * A simple {@link Fragment} subclass.  体重
 */
public class WeightFragment extends BaseFragment<WeightPresenter> {
    public String param;
    public EditText et_weight;
    public TextView tv_right;
    public MineActivity mineActivity;

    public WeightFragment(String param) {
        this.param = param;
    }

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
    public void onInitActionBar(BaseActivity activity) {
        mineActivity= (MineActivity) activity;
        mineActivity.mTvActionBarTitle.setText("体重");
        mineActivity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        mineActivity.mCbActionBarEdit.setText("完成");
        mineActivity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.i("完成");
                mPresenter.submit();
            }
        });

    }



    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weight,null);
        et_weight = (EditText) view.findViewById(R.id.et_weight);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        et_weight.setText(param);
        return view;

    }

    @Override
    public WeightPresenter initPresenter() {
        return new WeightPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }


}
