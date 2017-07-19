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
import com.microquation.linkedme.android.LinkedME;
import com.microquation.linkedme.android.util.LinkProperties;
import com.orhanobut.logger.Logger;

public class StartActivity extends Activity {
    private boolean canToGoods =false;
    private String goodsId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        APP.Version = String.valueOf(AppUtil.getVersionCode(this));
        Logger.i("版本号=" + APP.Version);
        SharedPreferences sp = getSharedPreferences("slide", Context.MODE_PRIVATE);
        final String first = sp.getString("isfirst", "");
        if (getIntent() != null) {
            //获取与深度链接相关的值
            LinkProperties linkProperties = getIntent().getParcelableExtra(LinkedME.LM_LINKPROPERTIES);
            if (linkProperties != null) {
                Logger.w("LinkedME-Demo渠道：" + linkProperties.getChannel());
                Logger.w("LinkedME-携带数据：" + linkProperties.getControlParams());
                Logger.w("LinkedME-深度链接：" + linkProperties.getLMLink());
                Logger.w("LinkedME-是否为新安装：" + linkProperties.isLMNewUser());
                if (linkProperties.getControlParams().containsKey("goodsID")){
                    goodsId=linkProperties.getControlParams().get("goodsID");
                    canToGoods =true;
                }

//                if (view != null) {
//                    //根据不同的参数进行页面跳转,detail代表具体跳转到哪个页面,此处语义指详情页
//                    if (view.equals("detail")) {
//                        //DetailActivity类不存在,此处语义指要跳转的详情页,参数也是由上面的HashMap对象指定
//                        Intent intent = new intent(MiddleActivity.this, DetailActivity.class);
//                        startActivity(intent);
//                    }
//                }
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHomeActivity(first);
            }
        }, 2000);

    }
    @Override
    public void onStart() {
        super.onStart();
        String device_id = LinkedME.getInstance().getDeviceId();
        Logger.w("设备唯一标识="+device_id);
    }
    private void enterHomeActivity(String first) {
        switch (first) {
            case "1": {
                if (canToGoods) {
                    Skip.toNewGoodsDetail(StartActivity.this,goodsId);
                }else {
                    Intent intent = new Intent(this, NewMainActivity.class);
                    startActivity(intent);
                }
                break;
            }
            default:{
                Intent intent = new Intent(this, ScreenSildeActivity.class);
                startActivity(intent);
            }
        }
        finish();
    }


}
