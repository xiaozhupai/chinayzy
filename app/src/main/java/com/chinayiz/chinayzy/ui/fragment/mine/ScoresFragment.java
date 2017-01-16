package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ScoresPresenter;

/**    我的积分
 * A simple {@link Fragment} subclass.
 */
public class ScoresFragment extends BaseFragment<ScoresPresenter> {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scores, container, false);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public ScoresPresenter initPresenter() {
        return new ScoresPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}
