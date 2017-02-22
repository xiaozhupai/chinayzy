package com.chinayiz.chinayzy.base;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/27 10:19
 * Class BaseFragmentView
 */

public interface BaseFragmentView extends BaseView{

    void startFragment(Fragment tofragment, String tag);

    Context getContext();

    Bundle getBundle();

    BaseFragment getFragment();
}
