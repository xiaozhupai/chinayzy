package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.ui.fragment.mine.SuggestFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**  c
 * Created by Administrator on 2017/1/10.
 */

public class SuggestPresenter extends BasePresenter<SuggestFragment> {
    private UserNet net=UserNet.getNet();
    @Override
    public void onCreate() {

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
    }


    @Override
    public void disposeNetMsg(EventMessage message) {
            if (message.getDataType()== Commons.ADDIDEA){
               BaseResponseModel model= (BaseResponseModel) message.getData();
                Toast.makeText(mView.getActivity(),model.getMsg(),Toast.LENGTH_SHORT).show();
                if (model.getCode().equals("100")){

                mView.mineActivity.onBackPressed();
                }
            }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    public void submit() {
        // validate
        String title =mView. et_suggest_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(mView.getActivity(), "输入主要问题模块,如收藏模块", Toast.LENGTH_SHORT).show();
            return;
        }

        String content =mView. et_suggest_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(mView.getActivity(), "问题详细描述", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
       net.getAddIdea(title,content);

    }

}
