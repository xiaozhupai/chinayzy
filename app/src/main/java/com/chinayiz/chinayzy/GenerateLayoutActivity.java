package com.chinayiz.chinayzy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chinayiz.chinayzy.views.refreshView.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.refreshView.PullableRecycleView;

public class GenerateLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    private PullableRecycleView nongye_home_recyclerLayout;
    private PullToRefreshLayout refresh_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        initView();

    }

    private void initView() {
        View iv_seek = findViewById(R.id.iv_seek);
        View iv_services =findViewById(R.id.iv_services);
        nongye_home_recyclerLayout = (PullableRecycleView) findViewById(R.id.nongye_home_recyclerLayout);
        refresh_view = (PullToRefreshLayout) findViewById(R.id.refresh_view);

        iv_seek.setOnClickListener(this);
        iv_services.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_seek:

                break;
            case R.id.iv_services:

                break;
        }
    }
}
