package com.chinayiz.chinayzy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void onTest(View view) {
        BaseActivity.showToast(this,"发送请求成功");
        OkHttpUtils
                .post()
                .url("http://www.wbus.cn/getQueryServlet")
                .addParams("Type","LineDetail")
                .addParams("lineNo","340")
                .addParams("direction","0")
                .addParams("cityId ","")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.e("测试数据返回="+e);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Logger.i("测试数据返回="+s);
                    }
                });
    }
}
