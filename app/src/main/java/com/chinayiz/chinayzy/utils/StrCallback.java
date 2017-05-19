package com.chinayiz.chinayzy.utils;

import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/19 14:41
 * Class StrCallBack 所有请求的回调接口
 */

public abstract class StrCallback extends AbsCallback<String>{
    /**
     * 成功
     */
    public static final String RESPONSE_CODE_SUCCESS="100";
    /**
     * 失败
     */
    public static final String RESPONSE_CODE_ERROR="101";
    /**
     * 账号异常
     */
    public static final String RESPONSE_CODE_USER_OUT="109";

    public String convertSuccess(Response response) throws Exception {
        String s = StringConvert.create().convertSuccess(response);
        response.close();
        BaseResponseModel model=CommonRequestUtils.mGson.fromJson(s,BaseResponseModel.class);
        if (RESPONSE_CODE_USER_OUT.equals(model.getCode())){
            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,RESPONSE_CODE_USER_OUT,""));
        }
        return s;
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
        EventBus.getDefault().post(new EventMessage(EventMessage.ERROR_EVENT,"",e));
    }
}
