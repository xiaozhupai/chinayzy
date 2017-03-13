package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.TagsModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.LabelFragment;
import com.chinayiz.chinayzy.widget.Tag;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**个性标签
 * Created by Administrator on 2017/2/18.
 */

public class LabelPresenter extends BasePresenter<LabelFragment> {
    private UserNet net=UserNet.getNet();
    public List<TagsModel.DataBean.TaglistBean>  tags_staple;   //常用标签
    public List<Tag>  tags_mystle=new ArrayList<>();   //自己的个性标签
    public static final String LABEL="LABEL";

    @Override
    protected void onCreate() {
        net.getTags();
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
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType()==EventMessage.INFORM_EVENT){
            disposeNetMsg(message);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
              switch (message.getDataType()){
                  case Commons.GETTAGS:
                      TagsModel model= (TagsModel) message.getData();
                    tags_staple=model.getData().getTaglist();
                      List <Tag> list=new ArrayList<>();
                      for (TagsModel.DataBean.TaglistBean bean: tags_staple){
                          Tag tag=new Tag();
                          tag.setTitle(bean.getTagname());
                          tag.setId(bean.getTagid());
                          list.add(tag);
                          if (!TextUtils.isEmpty(bean.getIsxz())){
                              if (bean.getIsxz().equals("1")){  //该标签是选中状态
                                  for (Tag my_tag:tags_mystle){
                                      if (my_tag.getTitle().equals(bean.getTagname())){
                                          my_tag.setId(bean.getTagid());
                                      }
                                  }
                              }
                          }

                      }
                      mView.tlv_staple.setTags(list);
                      break;
                  case Commons.FINISHTAGS:
                      BaseResponseModel model2= (BaseResponseModel) message.getData();
                      BaseActivity.showToast(mView.getActivity(),model2.getMsg());
                      if (model2.getCode().equals("100")){
                          EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,LABEL,tags_mystle));
                          mView.mActivity.onBackPressed();
                      }

                      break;
              }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    //个性标签
    public void mystyleTag(Tag tag){
               tags_mystle.remove(tag);
        mView.tlv_mystyle.setTags(tags_mystle);
    }

    //常用标签
    public void stapleTag(Tag tag){
        for (Tag bean:tags_mystle){
            if (bean.getTitle().equals(tag.getTitle())){
                return;
            }
        }
        if (tags_mystle.size()==5){
            BaseActivity.showToast(mView.getActivity(),"最多添加5个标签");
            return;
        }
        Tag mystyle_tag=new Tag();
         mystyle_tag.setId(tag.getId());
        mystyle_tag.setTitle(tag.getTitle());
          tags_mystle.add(mystyle_tag);
        mView.tlv_mystyle.setTags(tags_mystle);
    }

    /**
     * 手动添加标签
     */
    public void AddTag(){
        String label=mView.et_label.getText().toString().trim();
        if (TextUtils.isEmpty(label)){
        BaseActivity.showToast(mView.getActivity(),"标签不能为空");
            return;
        }
        if (tags_mystle.size()==5){
            BaseActivity.showToast(mView.getActivity(),"最多添加5个标签");
            return;
        }
        for (Tag bean:tags_mystle){
            if (bean.getTitle().equals(label)){
                return;
            }
        }

        Tag tag=new Tag();
        tag.setTitle(label);
        tags_mystle.add(tag);
        mView.tlv_mystyle.setTags(tags_mystle);

    }

    //完成
    public void submit(){
        StringBuilder tag=new StringBuilder();
       StringBuilder tagid=new StringBuilder();

        for (int i=0;i<tags_mystle.size();i++){
            Tag bean=tags_mystle.get(i);
            tag.append(bean.getTitle());
            tagid.append(bean.getId());
            if (i!=tags_mystle.size()-1){
                tag.append(",");
                tagid.append(",");
            }
        }
        Logger.i("tag"+tag);
        Logger.i("tagid"+tagid);
        net.getFinishTags(tag.toString(),tagid.toString());
    }
}
