package com.chinayiz.chinayzy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.chinayiz.chinayzy.utils.AppUtil;
import com.orhanobut.logger.Logger;

public class StartActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        APP.Version= String.valueOf(AppUtil.getVersionCode(this));
        Logger.i("版本号="+APP.Version);
//        SystemClock.sleep(1000);
        SharedPreferences sp=getSharedPreferences("slide", Context.MODE_PRIVATE);
        final String first=sp.getString("isfirst","");
        Logger.i("热更新修复");
      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              enterHomeActivity(first);

          }
      },2000);

    }

    private void enterHomeActivity(String first) {
        if (first.equals("1")){
            Intent intent=new Intent(this,NewMainActivity.class);
            startActivity(intent);
        }else {
            Intent intent=new Intent(this,ScreenSildeActivity.class);
            startActivity(intent);
        }
        finish();
    }


}
