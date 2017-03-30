package com.chinayiz.chinayzy.presenter;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.RegisterModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.Login.LoginNet;
import com.chinayiz.chinayzy.ui.fragment.register.RegisterInfoFragment;
import com.chinayiz.chinayzy.utils.PutObjectSamples;
import com.chinayiz.chinayzy.utils.SDCardUtil;
import com.chinayiz.chinayzy.widget.ArrayAlertDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/23.
 */

public class RegisterInfoPresenter extends BasePresenter<RegisterInfoFragment> {
    private String specificaddress;
    public ArrayAlertDialog dialog;
    private static final int CAMERA=1;
    private static final int PHOTO=0;
    public static final int IMAGE_REQUEST_CODE = 0x00008000;
    public static final int RESIZE_REQUEST_CODE = 0x00008001;
    public static final int CAMERA_REQUEST_CODE = 0x00008002;
    private Uri imageUri;
    private   String uploadFilePath = "<upload_file_path>";
    private   String uploadObject = "";
    private int index;  //0  身份证正面   1 背面
    private static final int CARD=0;
    private static final int CARD_BACK=1;
    private String card;
    private String card_back;
    private String sex_code;   //性别
    private String ismarriage_code;  //婚姻
    private String education_code;  //学历
    private String politics_code;     //政治面貌
    public static final String REGISTERINFO_BACK="REGISTERINFO_BACK";

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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
             disposeInfoMsg(message);

    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
   
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
          switch (message.getDataType()){
              case AddAddressPresenter.PICKVIEW:
                  specificaddress= (String) message.getData();
                  String a[]= specificaddress.split(",");
                  mView.et_address.setText(a[0]+a[1]+a[2]);
                  break;
              case CAMERA_REQUEST_CODE+"":
                  if (SDCardUtil.isSDCARDMounted(mView.getActivity())){
                      resizeImage(imageUri);
                  } else {
                      BaseActivity.showToast(mView.getActivity(),"未找到存储卡，无法存储照片！");
                  }
                  break;
              case IMAGE_REQUEST_CODE+"":
                  Intent data= (Intent) message.getData();
                  resizeImage(data.getData());
                  break;
              case RESIZE_REQUEST_CODE+"":  //图片剪裁
                  Intent data1= (Intent) message.getData();
                  if (data1 != null) {
                      uploadImage(data1);
                  }
                  break;
              case Commons.REGISTER:
                  RegisterModel model= (RegisterModel) message.getData();
                  BaseActivity.showToast(mView.getActivity(),model.getMsg());
                  if (model.getCode().equals("100")){
                      APP.sUserid=model.getData().getUserid()+"";
                      mView.mActivity.finish();
                      EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,REGISTERINFO_BACK,""));

                  }

                  break;
              case PutObjectSamples.PUTOBJECTSAMPLES:
                  if (index==0){
                      card=Commons.HOST+"/"+uploadObject;
                      Glide.with(mView.getActivity()).load(card).into(mView.iv_card);
                  }else {
                      card_back=Commons.HOST+"/"+uploadObject;
                      Glide.with(mView.getActivity()).load(card_back).into(mView.iv_card_back);
                  }
                  break;

          }
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


    //提交
    public void submit() {
        // validate

        String nickname = mView.et_nickname.getText().toString().trim();
        if (TextUtils.isEmpty(nickname)) {
            Toast.makeText(mView.getActivity(), "请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = mView.et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(mView.getActivity(), "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String sex = mView.et_sex.getText().toString().trim();
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(mView.getActivity(), "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }

        String birth = mView.et_birth.getText().toString().trim();
        if (TextUtils.isEmpty(birth)) {
            Toast.makeText(mView.getActivity(), "请选择出生年月", Toast.LENGTH_SHORT).show();
            return;
        }

        String idcard = mView.et_card.getText().toString().trim();
        if (TextUtils.isEmpty(idcard)) {
            Toast.makeText(mView.getActivity(), "请输入身份证号", Toast.LENGTH_SHORT).show();
            return;
        }

        String address = mView.et_address.getText().toString().trim();
        String marriage = mView.et_marriage.getText().toString().trim();
        String height = mView.et_height.getText().toString().trim();
        String weight = mView.et_weight.getText().toString().trim();
        String education = mView.et_education.getText().toString().trim();
        String policatical = mView.et_policatical.getText().toString().trim();


        Pattern pattern= Pattern.compile("^\\d{15}|\\d{18}$");
        Matcher matcher=pattern.matcher(idcard);
        if (!matcher.find()){
            BaseActivity.showToast(mView.getActivity(),"请输入正确的身份证");
            return;
        }

        if (TextUtils.isEmpty(card)){
            BaseActivity.showToast(mView.getActivity(),"请上传身份证正面");
            return;
        }
        if (TextUtils.isEmpty(card_back)){
            BaseActivity.showToast(mView.getActivity(),"请上传身份证背面");
            return;
        }

        if (sex.equals("男")){
            sex_code="0";
        }else {
          sex_code="1";
        }
        if (!TextUtils.isEmpty(marriage)){
            if (marriage.equals("未婚")){
                ismarriage_code="0";
            }else if (marriage.equals("已婚")){
                ismarriage_code="1";
            }else {
                ismarriage_code="2";
            }
        }else {
            ismarriage_code="";
        }
        if (!TextUtils.isEmpty(education)){
            switch (education){
                case "高中":
                    education_code="1";
                    break;
                case "大专":
                    education_code="2";
                    break;
                case "本科":
                    education_code="3";
                    break;
                case  "研究生":
                    education_code="4";
                    break;
            }
        }else {
            education_code="";
        }
        if (!TextUtils.isEmpty(policatical)){
            if (policatical.equals("团员")){
               politics_code="1";
            }else if (policatical.equals("党员")){
                politics_code="2";
            }else {  //群众
                politics_code="3";
            }
        }else {
            politics_code="";
        }


        LoginNet.getLoginNet().toRegister(mView.phone,mView.password,mView.code,nickname,idcard,username,card+","+card_back,sex_code,birth,address,ismarriage_code,height,weight,education_code,politics_code);


    }

    //身份证 正面
    public void toCard() {
        toPhoto(CARD);
    }

    //身份证反面
    public void toCardBack() {
        toPhoto(CARD_BACK);
    }

    public void toPhoto(int index){
         this.index=index;
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
}
