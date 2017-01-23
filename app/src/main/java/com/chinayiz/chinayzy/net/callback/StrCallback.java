package com.chinayiz.chinayzy.net.callback;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/19 14:08
 * Class StrCallback   所有网络请求回调库
 */
public abstract class StrCallback extends Callback<String>{
    @Override
    public String parseNetworkResponse(Response response, int i) throws Exception {
        return response.body().string();
    }

}
