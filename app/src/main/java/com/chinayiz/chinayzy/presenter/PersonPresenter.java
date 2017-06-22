package com.chinayiz.chinayzy.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.UserModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.AddressListFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.LabelFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.UserFragment;
import com.chinayiz.chinayzy.utils.PutObjectSamples;
import com.chinayiz.chinayzy.utils.SDCardUtil;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.widget.ArrayAlertDialog;
import com.chinayiz.chinayzy.widget.Tag;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**    个人资料
 * Created by Administrator on 2017/1/13.
 */

public class PersonPresenter extends BasePresenter<PersonFragment> {
    private UserNet net= UserNet.getNet();
    public List<Tag> tags_list=new ArrayList<>();
    private ArrayAlertDialog dialog;
    private static final int CAMERA=1;
    private static final int PHOTO=0;
    public static final int IMAGE_REQUEST_CODE = 0x00008000;
    public static final int RESIZE_REQUEST_CODE = 0x00008001;
    public static final int CAMERA_REQUEST_CODE = 0x00008002;
    private Uri imageUri;
    public Activity activity;
    private   String uploadFilePath = "<upload_file_path>";
    private   String uploadObject = "";
    private static final String downloadObject = "sampleObject";
    private String newurl;
    public UserModel.DataBean bean;
    public static final String HIGJT="0";
    public static final String JUNIOR="1";
    public static final String UNDERGRADUATE="2";
    public static final String GRADUATE="3";
    public static final String NO_MARRIAGE="0";
    public static final String MARRIAGE="1";
    public static final String DIVORCED="2";
    public static final String MEMBER="0";
    public static final String PARTY_MEMBER="1";
    public static final String MASSES="2";

    @Override
    public void onCreate() {
        getData();
    }

    /**
     * 上传头像
     * @param data
     */
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
                    new PutObjectSamples(APP.oss, APP.testBucket, uploadObject, uploadFilePath).asyncPutObjectFromLocalFile();
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
        if (message.getEventType()== EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }else if (message.getEventType()== EventMessage.ERROR_EVENT){
            if (mView.refresh_view!=null){
                mView.refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }

    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType()== EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){

            case Commons.GETUSERINFO: //个人中心接口
                UserModel model= (UserModel) message.getData();
                bean=model.getData();
                if (!TextUtils.isEmpty(bean.getPic())){
                    Glide.with(mView.getActivity()).load(bean.getPic()).into(mView.iv_person_head);
                }
                if (!TextUtils.isEmpty(bean.getNickname())){
                    mView.tv_person_username.setText(bean.getNickname());
                }
//                if (!TextUtils.isEmpty(bean.getEmail())){
//                    mView.tv_person_email.setText(bean.getEmail());
//                }
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
                if (!TextUtils.isEmpty(bean.getBirthday())){
                    mView.tv_person_birthday.setText(bean.getBirthday());
                }
                if (!TextUtils.isEmpty(bean.getEducational())){
                         switch (bean.getEducational()){
                             case HIGJT:
                                 mView.tv_person_education.setText("中专");
                                 break;
                             case JUNIOR:
                                 mView.tv_person_education.setText("大专");
                                 break;
                             case UNDERGRADUATE:
                                 mView.tv_person_education.setText("本科");
                                 break;
                             case GRADUATE:
                                 mView.tv_person_education.setText("研究生");
                                 break;
                         }
                }
                if (!TextUtils.isEmpty(bean.getIsmarriage())){
                    switch (bean.getIsmarriage()){
                        case NO_MARRIAGE:
                            mView.tv_person_ismarriage.setText("未婚");
                            break;
                        case MARRIAGE:
                            mView.tv_person_ismarriage.setText("已婚");
                            break;
                        case DIVORCED:
                            mView.tv_person_ismarriage.setText("离异");
                            break;
                    }
                }

                if (!TextUtils.isEmpty(bean.getPolitics())){
                    switch (bean.getPolitics()){
                        case MEMBER:
                            mView.tv_person_politics.setText("团员");
                            break;
                        case PARTY_MEMBER:
                            mView.tv_person_politics.setText("党员");
                            break;
                        case MASSES:
                            mView.tv_person_politics.setText("群众");
                            break;
                    }
                }
                if (!TextUtils.isEmpty(bean.getUsualplace())){
                    mView.tv_person_usualplace.setText(bean.getUsualplace());
                }
                if (bean.getSys_auth().equals("1")){
                    mView.tv_person_factname.setText("认证成功");
                }else {
                    mView.tv_person_factname.setText("未认证");
                }

//                if (!TextUtils.isEmpty(bean.getIdcard())){
//                    String idcard=bean.getIdcard();
//                    String first=idcard.substring(0,4);
//                    String last=idcard.substring(idcard.length()-4,idcard.length());
//                    mView.tv_person_card.setText(first+"****"+last);
//                }
//                //标签
//                tags_list.clear();
//                if (!TextUtils.isEmpty(bean.getTag())){
//
//                    String [] tags=bean.getTag().split(",");
//                    for (int i = 0; i <tags.length ; i++) {
//                        if (!TextUtils.isEmpty(tags[i])){
//                            Tag tag=new Tag();
//                            tag.setTitle(tags[i]);
//                            tags_list.add(tag);
//                        }
//                    }
//                    mView.tlv_list.setTags(tags_list);
//                }
                if (mView.refresh_view!=null){
                    mView.refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
                break;
            case UserNet.PIC:  //上传图片回调
                BaseResponseModel model1= (BaseResponseModel) message.getData();
                if (model1.getCode().equals("100")){
                    Glide.with(mView).load(newurl).into(mView.iv_person_head);
                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, MinePresenter.UPDATEMINE,""));
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
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, MinePresenter.UPDATEMINE,""));
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
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, MinePresenter.UPDATEMINE,""));
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
                newurl= Commons.HOST+"/"+uploadObject;
                Logger.i("newurl------"+newurl);
                net.getEditerUser(UserNet.PIC,newurl);
                break;
            case UserNamePresenter.BACK:
                getData();
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
                            mView. mActivity.startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
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
                        mView.mActivity.startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE);
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
        mView.mActivity.startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }




    public void toSex(){
        mView.mActivity.addFragment(new UserFragment(1,bean!=null?bean.getSex():"","性别"));
    }


    public void toHeight(){

        mView.mActivity.addFragment(new UserFragment(5,bean!=null?bean.getHeight():"","身高"));
    }

    public void toWeight(){
        mView.mActivity.addFragment(new UserFragment(6,bean!=null?bean.getWeight():"","体重"));
    }


    public void toLabel(){

        mView.mActivity.addFragment(new LabelFragment(bean!=null?tags_list:new ArrayList<Tag>()));
    }

    public void toUsername(){
        mView.mActivity.addFragment(new UserFragment(0,bean!=null?bean.getNickname():"","用户名"));
    }


    public void toAddress() {
        mView.mActivity.addFragment(new AddressListFragment());
    }

    public void getData() {
        net.getUserInfo();
    }

    public void toBirthday() {
        mView.mActivity.addFragment(new UserFragment(2,bean!=null?bean.getBirthday():"","出生日期"));
    }

    public void toUsualplace() {
        mView.mActivity.addFragment(new UserFragment(3,bean!=null?bean.getUsualplace():"","常驻地"));
    }

    public void toIsmarriage() {
        mView.mActivity.addFragment(new UserFragment(4,bean!=null?bean.getIsmarriage():"","婚姻状况"));
    }

    public void toEducation() {
        mView.mActivity.addFragment(new UserFragment(7,bean!=null?bean.getEducational():"","学历"));
    }

    public void toPolitics() {
        mView.mActivity.addFragment(new UserFragment(8,bean!=null?bean.getPolitics():"","政治面貌"));
    }
}
