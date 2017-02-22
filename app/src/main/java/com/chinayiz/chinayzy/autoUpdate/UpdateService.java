package com.chinayiz.chinayzy.autoUpdate;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/3 17:06
 * Class UpdateService 开始后台下载更新apk服务并写入到SD卡
 */
public class UpdateService extends IntentService {
    private SharedPreferences.Editor mEditor;

    public UpdateService() {
        super("UpdateService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mEditor = getSharedPreferences("update", Context.MODE_PRIVATE).edit();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Logger.i("开始下载");
        String uri = intent.getStringExtra("downloadURI");
        OkHttpUtils.
                get()
                .tag("UpdateService")
                .url(uri)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "chinayzy.apk") {

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        Log.e("UpdateService", "下载进度: "+progress*100);
                    }
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Logger.i("错误",e.toString());
                    }
                    @Override
                    public void onResponse(File file, int i) {
                        Logger.i("文件地址"+file.getAbsolutePath());
                        mEditor.putBoolean("isDownload",true);
                        mEditor.commit();
                    }
                });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy");
    }
}
