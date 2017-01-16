package com.chinayiz.chinayzy.ui.fragment.cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ShopCartPresenter;

/**购物车
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends BaseFragment<ShopCartPresenter> {


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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_cart,null);
    }

    @Override
    public ShopCartPresenter initPresenter() {
        return new ShopCartPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}
