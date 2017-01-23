package com.chinayiz.chinayzy.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/20 9:40
 * Class FragmentAlternate Fragment 交替接口，由Presenter实现
 */

public interface FragmentAlternate {
    /**
     *  显示fragment
     * @param transaction Fragment事务
     * @param fragment  fragment
     */
    void addOrShowFragment(FragmentTransaction transaction,Fragment fragment);
}
