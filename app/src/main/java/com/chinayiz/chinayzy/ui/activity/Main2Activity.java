package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.CommonAdaphter;
import com.chinayiz.chinayzy.adapter.MyStepAdaphter;
import com.chinayiz.chinayzy.adapter.MyStepHeadAdaphter;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Activity {
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       listView= (ListView) findViewById(R.id.listview);
        MyStepHeadAdaphter heads=new MyStepHeadAdaphter(this);
      CommonAdaphter adaphter=new CommonAdaphter(this,heads);
        List<String> list=new ArrayList();
        for (int i=0;i<10;i++){
            list.add("dsds");
        }
        listView.setAdapter(adaphter);
    }
}
