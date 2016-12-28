package com.chinayiz.chinayzy.autoUpdate;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.Version;
import com.chinayiz.chinayzy.net.callback.VersionCallBack;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 11:34
 * Class APKUpdade 用于检查应用是否有新版本以及控制下载安装
 */
public class APKUpdadeUtils {
    public static boolean isWarnUser=true;//判断用户是否需要提醒
    private static final String INSPECT_UPDATE_URL="http://rap.taobao.org/mockjsdata/12311/api.chinayzy.updateapp";//检查更新地址
    public static Version mVersion;
    public static void inspectVersion(final Context con){

        OkHttpUtils.post()
                .url(INSPECT_UPDATE_URL)
                .addParams("version", APP.VERSION)
                .tag("inspect")
                .build().execute(new VersionCallBack() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Logger.i(e.toString()+i);
            }

            @Override
            public void onResponse(Version version, int i) {
                mVersion=version;
                updateNewVersion(con);
                Logger.i(mVersion.toString());
            }
        });
    }

    /**
     * 更新新版本
     * @param context
     */
    private static void updateNewVersion(Context context){
        if (mVersion==null){
            return;
        }
        switch (mVersion.getIsNewVersion()){
            case Version.COERCE_VERSION:{//强制更新版
                WarnUserUpdate(context);
                return;
            }
            case Version.NEW_VERSION:{//有新版本
               if(!isWarnUser){
                   return;
               }
                WarnUserUpdate(context);
                return;
            }
            case Version.NO_VERSION:{//没有新版本
                return;
            }
        }

    }
    private static void WarnUserUpdate(final Context context){
        new MaterialDialog.Builder(context)
                .iconRes(R.mipmap.ic_launcher)
                .limitIconToDefaultSize()
                .title(mVersion.getUpdateTitle())
                .content(mVersion.getUpdateMessge())
                .positiveText("下载更新")
                .negativeText("取消")
                .negativeColor(Color.BLACK)
                .positiveColor(context.getResources().getColor(R.color.colorAccent))
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Logger.i("选择了"+which.name()+"是否勾选"+dialog.isPromptCheckBoxChecked());
                        if (dialog.isPromptCheckBoxChecked()){
                            isWarnUser=false;
                        }else {
                            isWarnUser=true;
                        }
                        dialog.dismiss();
                    }
                })
                .checkBoxPromptRes(R.string.update_CheckBox_Messgea, false, null)
                .show();
    }


}
