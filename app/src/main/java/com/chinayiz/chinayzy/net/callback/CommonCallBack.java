package com.chinayiz.chinayzy.net.callback;

import com.chinayiz.chinayzy.entity.response.Version;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 14:12
 * Class CommonCallBack 检查版本回调接口
 */
public abstract class CommonCallBack extends Callback<Version> {

    @Override
    public Version parseNetworkResponse(Response response, int i) throws Exception {

           String str= response.body().string();
            Logger.i("body"+str.toString());
           return new Gson().fromJson(str,Version.class);
    }

}
