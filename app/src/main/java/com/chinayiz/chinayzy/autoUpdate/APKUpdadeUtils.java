package com.chinayiz.chinayzy.autoUpdate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.VersionModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2016/12/28 11:34
 * Class APKUpdade 用于检查应用是否有新版本以及控制下载安装
 */
public class APKUpdadeUtils {
    private Context mContext;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditer;
    //是否下载
    private boolean isDownload=false;
    //是否提醒
    private boolean isWarn =true;
    //检查更新地址
    private  final String INSPECT_UPDATE_URL="http://rap.taobao.org/mockjsdata/12311/api.chinayzy.updateapp";
    //新版本信息
    public VersionModel mVersionModel;
    public APKUpdadeUtils(Context con){
        mContext=con;
        mPreferences=mContext.getSharedPreferences("update",Context.MODE_PRIVATE);
    }

    /**
     * 检查是否有更新版本
     */
//    public void inspectVersion(){
//        isDownload=mPreferences.getBoolean("isDownload",false);
//        isWarn=mPreferences.getBoolean("isWarn",true);
//        OkHttpUtils.post()
//                .url(INSPECT_UPDATE_URL)
//                .addParams("version", AppInfo.VERSION_NAME)
//                .tag("inspect")
//                .build().execute();
//
//    }

//    ...................................................................
//    @Override
//    public void onError(Call call, Exception e, int i) {
//        Logger.i(e.toString()+"错误码："+i);
//    }
//    @Override
//    public void onResponse(VersionModel versionModel, int i) {
//        mVersionModel = versionModel;
//        switch (mVersionModel.getIsNewVersion()){
//            case VersionModel.COERCE_VERSION:{//强制更新版本
//                Logger.i("强制更新版本");
//                warnUserUpdate(mContext,isDownload);
//                break;
//            }
//            case VersionModel.NEW_VERSION:{//有更新版本
//                if (isWarn){
//                    Logger.i("有更新版本");
//                    warnUserUpdate(mContext,isDownload);
//                }
//                break;
//            }
//            case VersionModel.NO_VERSION:{//没有更新版本
//                Logger.i("没有新版本");
//                break;
//            }
//        }
//    }
    /**
     * dialog提醒用户更新/安装
     * @param context
     * @param isDownload 是否已经下载
     */
    public void warnUserUpdate(final Context context, final boolean isDownload){
        final String temp;
            if (isDownload){
                temp="安装";
            }else {
                temp="更新";
            }
            new MaterialDialog.Builder(context)
                    .iconRes(R.mipmap.ic_launcher)
                    .limitIconToDefaultSize()
                    .title(mVersionModel.getUpdateTitle())
                    .content(mVersionModel.getUpdateMessge())
                    .positiveText(temp)
                    .negativeText("取消")
                    .negativeColor(Color.BLACK)
                    .positiveColor(Color.rgb(255,64,129))
                    .onAny(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            //更新/下载
                            if (DialogAction.POSITIVE.toString().equals(which.name())){
                                Toast.makeText(context, "确认更新，不再提醒："+dialog.isPromptCheckBoxChecked(), Toast.LENGTH_SHORT).show();
                                if (isDownload){//是否已经下载
                                    doInstall(context);
                                }else {
                                    doDownload(context);
                                }
                            //取消更新/安装
                            }else if (DialogAction.NEGATIVE.toString().equals(which.name())){
                                Toast.makeText(context, "取消更新，不再提醒："+dialog.isPromptCheckBoxChecked(), Toast.LENGTH_SHORT).show();
                            }

                        }


                    })
                    .checkBoxPrompt("不要再提醒", false, new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            mEditer=mPreferences.edit();
                            mEditer.putBoolean("isWarn",b);
                            mEditer.commit();
                        }
                    })
                    .build()
                    .show();
    }

    /**
     * 开启服务下载apk
     */
    private void doDownload(Context context) {
        Intent inten=new Intent(context,UpdateService.class);
        inten.putExtra("downloadURI", mVersionModel.getDownloadUri());
        context.startService(inten);
    }

    /**
     * 安装apk
     */

    public  void doInstall(Context context) {

    }
}
