package com.chinayiz.chinayzy.autoUpdate;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/3 17:06
 * Class UpdateService 开始后台下载更新apk服务并写入到SD卡
 */
public class UpdateService extends IntentService {
    /**
     * 下载完成
     */
    public static final String UPDATA_APK_FINISH = "UPDATA_APP_finish";
    /**
     * 下载中
     */
    public static final String UPDATA_APK_ON = "UPDATA_APP_dowload";

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
        if (uri != null) {
            OkHttpUtils.
                    get()
                    .tag("downloadURI")
                    .url(uri)
                    .build()
                    .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "chinayzy.apk") {
                        @Override
                        public void inProgress(float progress, long total, int id) {
                            super.inProgress(progress, total, id);
                            float prs = progress * 100;
                            int ss = (int) prs;
                            EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT, UPDATA_APK_ON, ss));
                        }

                        @Override
                        public void onError(Call call, Exception e, int i) {
                            Logger.i("错误", e.toString());
                            BaseActivity.showToast(UpdateService.this, "未知更新错误");
                        }

                        @Override
                        public void onResponse(File file, int i) {
                            Logger.i("下载完成，文件地址=" + file.getAbsolutePath());
                            mEditer = getSharedPreferences("update", Context.MODE_PRIVATE).edit();
                            mEditer.putString("apkPath", file.getAbsolutePath());
                            mEditer.commit();
                            setPermission(file.getAbsolutePath());
                            mEditer = getSharedPreferences("update", Context.MODE_PRIVATE).edit();
                            mEditer.putBoolean("isLoad", true);
                            mEditer.commit();

                            EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT, UPDATA_APK_FINISH, ""));
                        }
                    });
        }
    }

    /**
     * 提升下载文件的读取权限
     * @param filePath   文件目录
     */
    public static void setPermission(String filePath) {
        String command = "chmod " + "777" + " " + filePath;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy");
        mEditer = null;
    }
}
