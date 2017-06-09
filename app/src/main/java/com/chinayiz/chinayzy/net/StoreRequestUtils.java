package com.chinayiz.chinayzy.net;

import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_EatThemeModel;
import com.chinayiz.chinayzy.entity.response.StoreHomeTabModel;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/6/5 14:55
 * Class StoreRequestUtils   亿众商城接口工具
 */

public class StoreRequestUtils {
    public static Gson mGson = new Gson();
    public static StoreRequestUtils mUtil;
    private StoreRequestUtils() {
    }

    public static StoreRequestUtils getRequestUtils() {
        if (mUtil == null) {
            mUtil = new StoreRequestUtils();
            return mUtil;
        }
        return mUtil;
    }
    /**
     * 获取商城首页商品类型主题名
     */
    public void getStoreHomeThemeName() {
        OkGo.post(Commons.API + Commons.STORE_HOME_TABS)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.STORE_HOME_TABS
                                    , mGson.fromJson(s, StoreHomeTabModel.class)));
                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     * 获取商城不同类型的banner广告图
     */
    public void getStoreHomeBanner(String itemcode) {
        OkGo.post(Commons.API + Commons.STORE_HOME_BANNER)
                .params("itemcode",itemcode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {

//                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
//                                    , Commons.UPDATA
//                                    , mGson.fromJson(s, AppUpdataModel.class)));

                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     * 获取商城不同类型的商品主题
     */
    public void getStoreHomeTheme(String itemcode) {
        OkGo.post(Commons.API + Commons.STORE_HOME_THEME)
                .params("itemcode",itemcode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {

                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
                                    , Commons.STORE_HOME_THEME
                                    , mGson.fromJson(s, NY_EatThemeModel.class)));

                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
    /**
     * 获取商城不同类型的商品集合
     */
    public void getStoreHomeGoodss(String page,String size,String itemcode) {
        OkGo.post(Commons.API + Commons.STORE_HOME_GOODSS)
                .params("page",itemcode)
                .params("size",itemcode)
                .params("itemcode",itemcode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {

//                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT
//                                    , Commons.UPDATA
//                                    , mGson.fromJson(s, AppUpdataModel.class)));

                        }catch (Exception e){
                            onError(null,response,e);
                        }
                    }
                });
    }
}
