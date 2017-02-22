package com.chinayiz.chinayzy.base;

import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/20 9:40
 * Class FragmentAlternate Fragment 交替接口
 */

public interface FragmentAlternate {

    /**
     * Fragment 交替接口
     * @param transaction FragmentTransaction事务
     * @param fragment  需要显示的Fragment
     */
    void addOrShowFragment(FragmentTransaction transaction,Fragment fragment);
}
