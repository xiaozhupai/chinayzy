package com.chinayiz.chinayzy.autoUpdate;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;

import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import okhttp3.Call;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/3 17:06
 * Class UpdateService 开始后台下载更新apk服务并写入到SD卡
 */
public class UpdateService extends IntentService {
    public static final String UPDATA_APP="UPDATA_APP";
    private SharedPreferences.Editor mEditer;

    public UpdateService() {
        super("UpdateService");
    }
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String uri = intent.getStringExtra("downloadURI");
       if (uri!=null){
           OkHttpUtils.
                   get()
                   .tag("downloadURI")
                   .url(uri)
                   .build()
                   .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "chinayzy.apk") {
                       @Override
                       public void inProgress(float progress, long total, int id) {
                           super.inProgress(progress, total, id);
                       }
                       @Override
                       public void onError(Call call, Exception e, int i) {
                           Logger.i("错误",e.toString());
                       }
                       @Override
                       public void onResponse(File file, int i) {
                           Logger.i("下载完成，文件地址="+file.getAbsolutePath());
                           mEditer=getSharedPreferences("update", Context.MODE_PRIVATE).edit();
                           mEditer.putString("apkPath",file.getAbsolutePath());
                           mEditer.putBoolean("isLoad",true);
                           mEditer.commit();
                           EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,UPDATA_APP,""));
                       }
                   });
       }

        }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy");
        mEditer=null;
    }
}
