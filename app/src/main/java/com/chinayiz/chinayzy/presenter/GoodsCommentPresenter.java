package com.chinayiz.chinayzy.presenter;

import android.app.FragmentManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.mine.GoodsCommentFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static com.chinayiz.chinayzy.APP.oss;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/20 10:18
 * Class GoodsCommentPresenter
 */

public class GoodsCommentPresenter extends BasePresenter<GoodsCommentFragment> {
    public CommonRequestUtils mRequestUtils=CommonRequestUtils.getRequestUtils();
    public   StringBuffer mBuffer;
    private  String uploadObject = "";
    private  int imagesCount=0;

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.COMMENT_ORDER:{
                Logger.i("商品评论成功了");
                mView.getActivity().getFragmentManager().popBackStack("OrderDetailFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
        }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case GoodsCommentFragment.COMMENT_DATA:{
                mBuffer=new StringBuffer();
                mView.path.clear();
                mView.path.addAll((List<String>) message.getData());
                imagesCount=mView.path.size();
                int i=0;
                for (String str : mView.path) {
                    // 拼接远程资源地址
                    Calendar calendar=Calendar.getInstance();
                    int year=calendar.get(Calendar.YEAR);
                    String date= DateFormat.format("MMdd",Calendar.getInstance(Locale.CHINA))+"";
                    String lastname= UUID.randomUUID()+".png";
                    uploadObject="v1/"+year+"/"+date+"/"+lastname;
                    i++;
                    if (i==imagesCount){
                        mBuffer.append(Commons.HOST+"/"+uploadObject);
                    }else {
                        mBuffer.append(Commons.HOST+"/"+uploadObject+",");
                    }
                   asyncPutObjectFromLocalFile(APP.testBucket,uploadObject,str);
                }
                mView.mRequestModel.setgPic(null);
                mView.mRequestModel.setPic(mBuffer.toString());
                break;
            }
        }
    }
    // 从本地文件上传，使用非阻塞的异步接口
    public void asyncPutObjectFromLocalFile(String bucket,String object,String uploadFilePath) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, object, uploadFilePath);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Logger.i("OSS回调：="+mBuffer.toString());

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(mView.getActivity(), "未知错误，请重试");
        }
    }


    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }
}
