package com.chinayiz.chinayzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.ui.common.GoodsActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TestActivity extends BaseActivity {
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter() {
            @Override
            protected void onCreate() {

            }

            @Override
            protected void onDestroy() {

            }

            @Override
            public void onSaveInstanceState(Bundle outState) {

            }

            @Override
            public void runUiThread(EventMessage message) {

            }

            @Override
            @Subscribe(threadMode = ThreadMode.BACKGROUND)
            public void runBgThread(EventMessage message) {

            }

            @Override
            @Subscribe(threadMode = ThreadMode.MAIN)
            public void disposeNetMsg(EventMessage message) {

            }

            @Override
            public void disposeInfoMsg(EventMessage message) {

            }
        };
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

    }


    private void toast(String info) {
        if (mToast == null) {
            mToast = Toast.makeText(this, info, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(info);
        }
        mToast.show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_action1: {
                Intent intent=new Intent(this, GoodsActivity.class);
                intent.putExtra("goodsID","8");
                startActivity(intent);
                break;
            }
            case R.id.btn_action2: {

                break;
            }
            case R.id.btn_action3: {

                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
