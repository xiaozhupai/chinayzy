package com.chinayiz.chinayzy.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.TagsModel;
import com.chinayiz.chinayzy.entity.response.UserModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.CardFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.EmailFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.HeightFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.LabelFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SexFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.TrueNameFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.UserNameFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.WeightFragment;
import com.chinayiz.chinayzy.utils.PutObjectSamples;
import com.chinayiz.chinayzy.utils.SDCardUtil;
import com.chinayiz.chinayzy.widget.ArrayAlertDialog;
import com.chinayiz.chinayzy.widget.Tag;
import com.mob.tools.gui.MobViewPager;
import com.mob.tools.gui.PullToRequestBaseAdapter;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**    个人资料
 * Created by Administrator on 2017/1/13.
 */

public class PersonPresenter extends BasePresenter<PersonFragment> {
    private UserNet net=UserNet.getNet();
    public List<Tag> tags_list=new ArrayList<>();
    private ArrayAlertDialog dialog;
    private static final int CAMERA=1;
    private static final int PHOTO=0;
    public static final int IMAGE_REQUEST_CODE = 0x00008000;
    public static final int RESIZE_REQUEST_CODE = 0x00008001;
    public static final int CAMERA_REQUEST_CODE = 0x00008002;
    private Uri imageUri;
    public Activity activity;
    private OSS oss;
    // 运行sample前需要配置以下字段为有效的值
    private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com"; // yzy-app-img.oss-cn-shanghai.aliyuncs.com  外网地址
    private static final String accessKeyId = "LTAIUtA7pQz24S4r";
    private static final String accessKeySecret = "uAD9Mkes66zETtRKXy7fSmZLyIJ2s7";
    private   String uploadFilePath = "<upload_file_path>";
    private static final String testBucket = "yzy-app-img";
    private   String uploadObject = "";
    private static final String downloadObject = "sampleObject";
    private String newurl;

    public UserModel.DataBean bean;
    @Override
    public void onCreate() {
        net.getUserInfo();
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(mView.getActivity(), endpoint, credentialProvider, conf);
    }

    private void uploadImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            String path;
            FileOutputStream fos = null;
            String filename= DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".png";
            final Bitmap photo = extras.getParcelable("data");
            File file = new File("/sdcard/chinayzy/");
            path=file.getPath()+filename;
            try {
                fos=new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
            uploadFilePath=path;
            Calendar calendar=Calendar.getInstance();
            int year=calendar.get(Calendar.YEAR);
            String date=DateFormat.format("MMdd",Calendar.getInstance(Locale.CHINA))+"";
            String lastname=UUID.randomUUID()+".png";
            uploadObject="v1/"+year+"/"+date+"/"+lastname;
            Logger.i(uploadObject);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new PutObjectSamples(oss, testBucket, uploadObject, uploadFilePath).asyncPutObjectFromLocalFile();
                }
            }).start();
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }

    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType()==EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){

            case Commons.GETUSERINFO:
                UserModel model= (UserModel) message.getData();
                bean=model.getData();
                if (!TextUtils.isEmpty(bean.getPic())){
                    Glide.with(mView.getActivity()).load(bean.getPic()).into(mView.iv_person_head);
                }
                if (!TextUtils.isEmpty(bean.getNickname())){
                    mView.tv_person_username.setText(bean.getNickname());
                }
                if (!TextUtils.isEmpty(bean.getEmail())){
                    mView.tv_person_email.setText(bean.getEmail());
                }
                if (!TextUtils.isEmpty(bean.getSex())){
                    if (bean.getSex().equals("0")){
                        mView.tv_person_sex.setText("男");
                    }else {
                        mView.tv_person_sex.setText("女");
                    }
                }
                if (!TextUtils.isEmpty(bean.getHeight())){
                    mView.tv_person_height.setText(bean.getHeight()+"cm");
                }
                if (!TextUtils.isEmpty(bean.getWeight())){
                    mView.tv_person_weight.setText(bean.getWeight()+"kg");
                }
                if (!TextUtils.isEmpty(bean.getTruename())){
                    mView.tv_person_factname.setText(bean.getTruename());
                }
                if (!TextUtils.isEmpty(bean.getIdcard())){
                    String idcard=bean.getIdcard();
                    String first=idcard.substring(0,4);
                    String last=idcard.substring(idcard.length()-4,idcard.length());
                    mView.tv_person_card.setText(first+"****"+last);
                }
                if (!TextUtils.isEmpty(bean.getTag())){

                    String [] tags=bean.getTag().split(",");
                    for (int i = 0; i <tags.length ; i++) {
                        if (!TextUtils.isEmpty(tags[i])){
                            Tag tag=new Tag();
                            tag.setTitle(tags[i]);
                            tags_list.add(tag);
                        }
                    }
                    mView.tlv_list.setTags(tags_list);
                }
                break;
            case UserNet.PIC:
                BaseResponseModel model1= (BaseResponseModel) message.getData();
                if (model1.getCode().equals("100")){
                    Glide.with(mView).load(newurl).into(mView.iv_person_head);
                }
                BaseActivity.showToast(mView.getActivity(),model1.getMsg());
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case UserNet.EMAIL:
                mView.tv_person_email.setText(message.getData().toString());
                break;
            case UserNet.HEIGHT:
                mView.tv_person_height.setText(message.getData().toString()+"cm");
                break;
            case UserNet.WEIGHT:
                mView.tv_person_weight.setText(message.getData().toString()+"kg");
                break;
            case UserNet.IDCARD:
                String idcard=message.getData().toString();
                String first=idcard.substring(0,4);
                String last=idcard.substring(idcard.length()-4,idcard.length());
                mView.tv_person_card.setText(first+"****"+last);
                break;
            case UserNet.SEX:
                Logger.i("sex返回值");
                Logger.i(message.getData().toString());
                mView.tv_person_sex.setText(message.getData().toString());
                break;
            case UserNet.TRUENAME:
                mView.tv_person_factname.setText(message.getData().toString());
                break;
            case UserNet.TAGS:
                tags_list= (List<Tag>) message.getData();
                mView.tlv_list.setTags(tags_list);
                break;
            case UserNet.NICKNAME:
                mView.tv_person_username.setText(message.getData().toString());
                break;
            case LabelPresenter.LABEL:
                tags_list= (List<Tag>) message.getData();
                mView.tlv_list.setTags(tags_list);
                break;
            case IMAGE_REQUEST_CODE+"":
                Intent data= (Intent) message.getData();
                resizeImage(data.getData());
                break;
            case CAMERA_REQUEST_CODE+"":
                if (SDCardUtil.isSDCARDMounted(activity)) {
                    resizeImage(imageUri);
                } else {
                    BaseActivity.showToast(mView.getActivity(),"未找到存储卡，无法存储照片！");
                }
                break;
            case RESIZE_REQUEST_CODE+"":
                Intent data1= (Intent) message.getData();
                Bundle bundle=data1.getExtras();
                if (data1 != null) {
                    uploadImage(data1);
                }
                break;
            case PutObjectSamples.PUTOBJECTSAMPLES:
                newurl=Commons.HOST+"/"+uploadObject;
                Logger.i("newurl------"+newurl);
                net.getEditerUser(UserNet.PIC,newurl);
                break;

        }
    }

    public void toHead(){
        if (dialog==null){
            dialog=new ArrayAlertDialog(mView.getActivity(), Gravity.BOTTOM,new String[]{"相册","相机"});
        }
        dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case CAMERA:
                        Logger.i("拍照");
                        String path = UUID.randomUUID().toString() + ".jpg";
                        if (SDCardUtil.isSDCARDMounted(mView.getActivity())) {
                            dialog.dismiss();
                            imageUri = Uri.fromFile(new File(SDCardUtil.getTempPath(mView.getActivity()), path));
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                            mView. activity.startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                        } else {
                            BaseActivity.showToast(mView.getActivity(),"请插入sd卡");
                        }
                        break;
                    case PHOTO:
                        Logger.i("相册");
                        dialog.dismiss();
                        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
                        galleryIntent.setType("image/*");
                        mView.activity.startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE);
                        break;
                }
            }
        });
        dialog.show();
    }







    /**
     * 裁剪图片
     *
     * @param uri uri
     */
    private void resizeImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        mView.activity.startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }



    public void toEmail(){
        mView.activity.addFragment(new EmailFragment(bean!=null?bean.getEmail():""));
    }
    public void toSex(){
        mView.activity.addFragment(new SexFragment(bean!=null?(bean.getSex()!="0"?"男":"女"):""));
    }
    public void tofactName(){
        mView.activity.addFragment(new TrueNameFragment(bean!=null?bean.getTruename():""));
    }

    public void toHeight(){

        mView.activity.addFragment(new HeightFragment(bean!=null?bean.getHeight():""));
    }

    public void toWeight(){
        mView.activity.addFragment(new WeightFragment(bean!=null?bean.getWeight():""));
    }
    public void toCard(){
        mView. activity.addFragment(new CardFragment(bean!=null?bean.getIdcard():""));
    }

    public void toLabel(){

        mView.activity.addFragment(new LabelFragment(bean!=null?tags_list:new ArrayList<Tag>()));
    }

    public void toUsername(){
        mView.activity.addFragment(new UserNameFragment(bean!=null?bean.getNickname():""));
    }


}
