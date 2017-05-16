package com.chinayiz.chinayzy.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.ui.common.CommonWebFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.ImGoldFragment;

public class GoldActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private ImGoldFragment mImGoldFragment;
    private CommonWebFragment mWebFragment;
    private canGoBack mBack;

    public void setBack(canGoBack back) {
        mBack = back;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold);
        mFragmentManager = getFragmentManager();
        mImGoldFragment = new ImGoldFragment();
        mFragmentManager
                .beginTransaction()
                .add(R.id.fl_goldLayout, mImGoldFragment, "ImGoldFragment")
                .commit();
    }

    @Override
    public void onBackPressed() {
       if (mBack!=null){
           if (mBack.canKeyBacck()){
               mBack.goBacck();
               return;
           }
       }
        super.onBackPressed();
    }

    public interface canGoBack{
         boolean canKeyBacck();
         void goBacck();
    }
}
