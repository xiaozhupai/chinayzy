package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
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
import com.chinayiz.chinayzy.widget.Tag;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**    个人资料
 * Created by Administrator on 2017/1/13.
 */

public class PersonPresenter extends BasePresenter<PersonFragment> {
    private UserNet net=UserNet.getNet();
    public List<Tag> tags_list=new ArrayList<>();
    public UserModel.DataBean bean;
    @Override
    public void onCreate() {
        net.getUserInfo();
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
                        Tag tag=new Tag();
                        tag.setTitle(tags[i]);
                        tags_list.add(tag);
                    }
                    mView.tlv_list.setTags(tags_list);
                }
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
          }
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
      mView.activity.addFragment(new LabelFragment(bean!=null?bean.getTag():""));
    }

    public void toUsername(){
        mView.activity.addFragment(new UserNameFragment(bean!=null?bean.getNickname():""));
    }
}
