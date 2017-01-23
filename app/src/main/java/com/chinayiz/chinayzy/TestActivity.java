package com.chinayiz.chinayzy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class TestActivity extends AppCompatActivity {


    private LinearLayout mLyTypeBtn1;
    private LinearLayout mLyTypeBtn2;
    private LinearLayout mLyTypeBtn3;
    private LinearLayout mLyTypeBtn4;
    private LinearLayout mLyTypeBtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nongye_home_teypmenu);
        initView();


    }


    private void initView() {

        mLyTypeBtn1 = (LinearLayout) findViewById(R.id.ly_type_btn1);

        mLyTypeBtn2 = (LinearLayout) findViewById(R.id.ly_type_btn2);

        mLyTypeBtn3 = (LinearLayout) findViewById(R.id.ly_type_btn3);

        mLyTypeBtn4 = (LinearLayout) findViewById(R.id.ly_type_btn4);

        mLyTypeBtn5 = (LinearLayout) findViewById(R.id.ly_type_btn5);
    }
}
