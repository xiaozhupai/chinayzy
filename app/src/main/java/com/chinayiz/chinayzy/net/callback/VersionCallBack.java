package com.chinayiz.chinayzy.net.callback;

import com.chinayiz.chinayzy.entity.response.VersionModel;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 14:12
 * Class VersionCallBack 检查版本回调接口
 */
public abstract class VersionCallBack extends Callback<VersionModel> {
    @Override
    public VersionModel parseNetworkResponse(Response response, int i) throws Exception {
           String str= response.body().string();
            Logger.i("body"+str.toString());
           return new Gson().fromJson(str,VersionModel.class);
    }

}
