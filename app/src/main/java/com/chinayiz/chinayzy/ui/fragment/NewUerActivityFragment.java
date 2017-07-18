package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.NewUerActivityPresenter;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/7/17 11:05
 * Class NewUerActivityFragment
 */

public class NewUerActivityFragment extends BaseFragment<NewUerActivityPresenter> {

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newuser_activity, null);
        Button button= (Button) view.findViewById(R.id.toRegist_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip.toRegister(getActivity());
            }
        });
        return view;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("注册好礼");
    }

    @Override
    public NewUerActivityPresenter initPresenter() {
        return new NewUerActivityPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }
}
