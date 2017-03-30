package com.chinayiz.chinayzy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class StartActivity extends Activity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        setContentView(R.layout.activity_start);
//        SystemClock.sleep(1000);
        SharedPreferences sp=getSharedPreferences("slide", Context.MODE_PRIVATE);
        final String first=sp.getString("isfirst","");

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              enterHomeActivity(first);

          }
      },2000);

    }

    private void enterHomeActivity(String first) {
        if (first.equals("1")){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }else {
            Intent intent=new Intent(this,ScreenSildeActivity.class);
            startActivity(intent);
        }
        finish();
    }


}
