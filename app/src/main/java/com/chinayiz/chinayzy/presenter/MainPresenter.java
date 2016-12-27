package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;

import com.chinayiz.chinayzy.MainActivity;
import com.chinayiz.chinayzy.base.BasePresenter;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 10:57
 * Class MainPresenter
 */
public class MainPresenter extends BasePresenter <MainActivity>{

    @Override
    public void onCreate() {
        mView.getActivity();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
