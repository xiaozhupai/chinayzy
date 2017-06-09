package com.chinayiz.chinayzy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class GenerateLayoutActivity extends AppCompatActivity {


    private RecyclerView recycleView;
    private BGARefreshLayout refresh_layout;
    private View ll_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stotr_tab);
        initView();


    }

    private void initView() {
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        refresh_layout = (BGARefreshLayout) findViewById(R.id.refresh_layout);
        ll_progress = findViewById(R.id.ll_progress);
    }
}
