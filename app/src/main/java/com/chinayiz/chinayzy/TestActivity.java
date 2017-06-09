package com.chinayiz.chinayzy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.StoreRequestUtils;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;

import okhttp3.Call;
import okhttp3.Response;

public class TestActivity extends Activity {

    public StoreRequestUtils mRequestUtils =StoreRequestUtils.getRequestUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void onTest(View view) {
        BaseActivity.showToast(this, "发送请求成功");
//        mRequestUtils.getStoreHomeThemeName();
        mRequestUtils.getStoreHomeTheme("016001");
    }

    public void onTest1(View view) {
        BaseActivity.showToast(this, "启动");
        getGoodsSet(1,"10","016001");
    }
    private void getGoodsSet(int page, String size,String tabCode) {
        OkGo.post(Commons.API + Commons.STORE_HOME_GOODSS)
                .params("page",page)
                .params("size",size)
                .params("itemcode",tabCode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {

                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
}
