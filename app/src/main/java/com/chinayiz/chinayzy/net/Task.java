package com.chinayiz.chinayzy.net;


import android.text.TextUtils;
import android.util.Log;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.entity.AppInfo;
import com.chinayiz.chinayzy.entity.response.Version;
import com.chinayiz.chinayzy.net.callback.HttpCallBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/5/6.
 */
public class Task<T> {
    public static final String ERR_MSG="服务器连接错误";
    private static final String TAG="Task";
    private Gson gson=new Gson();
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");



    public void doGet(String url, Map params,HttpCallBack<T> callback){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
//        OkHttpUtils.excute(call, callback);
    }

    public  void doPost(String url, Map<String, String> params, final Type type, final HttpCallBack<T> callback){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormBody.Builder builder= new FormBody.Builder();
        for (String key:params.keySet()) {
            builder.add(key,params.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.fail(ERR_MSG,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                if (!TextUtils.isEmpty(s)) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        String code = jsonObject.getString("code");
                        String result = jsonObject.getString("result");
                        Log.i(TAG, "code:" + code + "result" + result);
                        T t = gson.fromJson(result, type);
////                        if (code.equals("true")) {
////                            T t = gson.fromJson(result, type);
////                            callback.success(t);
////                        } else {
////                            callback.fail(result);
//                        }
                        callback.success(t);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callback.fail(e.toString(),e);
                    }
                }
            }
        });


    }
//    //上传文件
//    public void upload(final HttpCallBack<T> callBack){
//        final Type type=new TypeToken<String>(){}.getType();
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        File file = new File(Environment.getExternalStorageDirectory(), "clear_data.xls");
//        System.out.println(Environment.getExternalStorageDirectory().toString());
//        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//
//        RequestBody requestBody = new MultipartBuilder().addFormDataPart("userid", UserSession.getUsername()).
//                addPart(Headers.of(
//                "Content-Disposition",
//                "form-data; name=\"upload\";filename=\"clear_data.xls\""), fileBody).
//             type(MultipartBuilder.FORM)
//                     .build();
//        Request request = new Request.Builder()
//                .url(Constants._URL+"sync.action")
//                .post(requestBody)
//                .build();
//
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                callBack.fail(ERR_MSG);
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                //     Log.i(TAG,response.body().string());
//
//                    String s = response.body().string();
//                    if (!TextUtils.isEmpty(s)) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(s);
//                            String code = jsonObject.getString("code");
//                            String result = jsonObject.getString("result");
//                            Log.i(TAG, "code:" + code + "result" + result);
//                            if (code.equals("true")) {
//                                T t = gson.fromJson(result, type);
//                                callBack.success(t);
//                            } else {
//                                callBack.fail(result);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            callBack.fail(ERR_MSG);
//                        }
//                    }
//
//            }
//        });
//    }

//  //上传图片
//    public void uploadImage(EquirementBean bean,final HttpCallBack<T> callBack){
//        final Type type=new TypeToken<String>(){}.getType();
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//      MultipartBuilder multipartBuilder=new MultipartBuilder().type(MultipartBuilder.FORM);
//        if (!TextUtils.isEmpty(bean.image)){
//            String a[]=bean.image.split(",");
//            for (int i=0;i<a.length;i++){
//                if (!a[i].contains("/equipment_image")){
//                    multipartBuilder.addFormDataPart("image", a[i], RequestBody.create(MEDIA_TYPE_PNG, new File(a[i])));
//                }
//            }
//            System.out.println(bean.image);
//        }
//        multipartBuilder.addFormDataPart("path",bean.image);
//        multipartBuilder.addFormDataPart("userid", UserSession.getUsername());
//        multipartBuilder.addFormDataPart("eid", bean.eid + "");
//        multipartBuilder.addFormDataPart("pm2id", bean.pm2id);
//        multipartBuilder.addFormDataPart("ename", bean.ename);
//        multipartBuilder.addFormDataPart("assetid", bean.assetid);
//        multipartBuilder.addFormDataPart("manunum", bean.manunum);
//        multipartBuilder.addFormDataPart("emodel",bean.emodel);
//        multipartBuilder.addFormDataPart("manudate",bean.manudate);
//        multipartBuilder. addFormDataPart("address", bean.address);
//        multipartBuilder.addFormDataPart("lat", bean.lat);
//        multipartBuilder.addFormDataPart("len", bean.len);
//        multipartBuilder.addFormDataPart("flag",bean.flag+"");
//        multipartBuilder.addFormDataPart("line",bean.line);
//        multipartBuilder.addFormDataPart("cometype",bean.cometype);
//        multipartBuilder.addFormDataPart("station",bean.station);
//        multipartBuilder.addFormDataPart("location",bean.location);
//        multipartBuilder.addFormDataPart("maintenance",bean.maintenance);
//        multipartBuilder.addFormDataPart("assetpro",bean.assetpro);
//        multipartBuilder.addFormDataPart("utilitycode",UserSession.getPermissioncode());
//        multipartBuilder.addFormDataPart("etype",bean.etype);
//        multipartBuilder.addFormDataPart("tagnumber",bean.tagnumber);
//        multipartBuilder.addFormDataPart("remarks",bean.etype);
//        Request request = new Request.Builder()
//                .url(Constants._URL+"submitEquipment.action")
//                .post(multipartBuilder.build())
//                .build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                callBack.fail(ERR_MSG);
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                //     Log.i(TAG,response.body().string());
//                if (response.isSuccessful()) {
//                    String s = response.body().string();
//                    if (!TextUtils.isEmpty(s)) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(s);
//                            String code = jsonObject.getString("code");
//                            String result = jsonObject.getString("result");
//                            Log.i(TAG, "code:" + code + "result" + result);
//                            if (code.equals("true")) {
//                                T t = gson.fromJson(result, type);
//                                callBack.success(t);
//                            } else {
//                                callBack.fail(result);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            callBack.fail(ERR_MSG);
//                        }
//                    }
//                } else {
//                    callBack.fail(ERR_MSG);
//                }
//            }
//        });
//    }

    /**
     * 
     * @param callBack
     */
        public void toVersion(HttpCallBack<T> callBack){
        Type type=new TypeToken<Version>(){}.getType();
        Map<String,String> params=new HashMap<>();
        params.put("version", AppInfo.VERSION_NAME);
        doPost(Contants._URL + "test", params, type, callBack);
    }


//    public void toLogin(String username,String password,HttpCallBack<T> callBack){
//        Type type=new TypeToken<UserBean>(){}.getType();
//        Map<String,String> params=new HashMap<>();
//        params.put("userid", username);
//        params.put("password",password);
//        doPost(Constants._URL + "fragment_login.action", params, type, callBack);
//    }
//
//    public void toDownLoad(Map<String,List<String>> maps,HttpCallBack<T> callBack){
//
//        Type type=new TypeToken<List<EquirementBean>>(){}.getType();
//        Map<String,String> params=new HashMap<>();
//        params.put("etype_list", String.valueOf(maps));
//        doPost(Constants._URL + "download.action", params, type, callBack);
//    }
//
//    /**
//     * 注册
//     * @param nickname
//     * @param password
//     * @param phone
//     *
//     * @param callBack
//     */
//
//    public   void  Register(String username,String nickname,String password,String phone,String utilitycode,String utilityname,HttpCallBack<T> callBack){
//        Type type=new TypeToken<String>(){}.getType();
//        Map<String,String> params=new HashMap();
//        params.put("username",nickname);
//        params.put("phone",phone);
//        params.put("utilitycode",utilitycode);
//        params.put("userid",username);
//        params.put("utilityname",utilityname);
//        params.put("password",password);
//        doPost(Constants._URL + "register.action", params,type, callBack);
//    }
//
//    /**
//     * 获取验证码
//     * @param phone
//     * @param callback
//     */
//    public  void CodeTask(String phone,HttpCallBack<T> callback){
//        Type type=new TypeToken<String>(){}.getType();
//        Map<String,String> params=new HashMap();
//        params.put("phone",phone);
//        doPost(Constants._URL + "getSecurityCode.action", params,type, callback);
//    }



}
