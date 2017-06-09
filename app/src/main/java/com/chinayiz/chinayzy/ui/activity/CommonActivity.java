/**
 * Copyright (C) 2014 Luki(liulongke@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chinayiz.chinayzy.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.presenter.CommonPresenter;
import com.chinayiz.chinayzy.ui.fragment.WebPowerFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.GoodsCommentFragment;
import com.chinayiz.chinayzy.utils.StrCallback;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * @author Luki
 */

public class CommonActivity extends BaseActivity<CommonPresenter> implements FragmentManager.OnBackStackChangedListener {
    public static final String RESULT_BACK = "RESULT_BACK";
    public static final int IMAGE_REQUEST_CODE = 0x00008000;
    public static final int RESIZE_REQUEST_CODE = 0x00008001;
    private WebPowerFragment mWebPowerFragment;
    public static final int CAMERA_REQUEST_CODE = 0x00008002;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_button:
                Logger.i("onClick=返回");
                if (WebPowerFragment.CLASS_NAME.equals(className)) {
                    if (mFragment instanceof WebPowerFragment){
                        mWebPowerFragment = (WebPowerFragment) mFragment;
                        Logger.i("onClick=是 WEB 视图");
                        if (mWebPowerFragment.wv_view.canGoBack()) {
                            mWebPowerFragment.wv_view.goBack();
                            return;
                        }
                    }
                }
                onBackPressed();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

	/* (non-Javadoc)
     * @see cn.stlc.app.BaseActivity#onCreate(android.os.Bundle)
	 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APP.register(this);
        getIntent().putExtras(savedInstanceState == null ? new Bundle() : savedInstanceState);
        setContentView(R.layout.activity_common);
        initActionBar();
        addtoFragment(getIntent());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mFragment instanceof WebPowerFragment) {
            mWebPowerFragment = (WebPowerFragment) mFragment;
            if (mWebPowerFragment.wv_view.canGoBack()) {
                mWebPowerFragment.wv_view.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initActionBar() {
        //actionbar
        mActionBar = findViewById(R.id.rl_ActionBar);
        mIvBackButton = (ImageView) findViewById(R.id.iv_back_button);
        mTvActionBarTitle = (TextView) findViewById(R.id.tv_actionbar_title);
        mIvActionBarMore = (ImageView) findViewById(R.id.iv_more_button);
        mIvActionBarCart = (ImageView) findViewById(R.id.iv_shopcart);
        mCbActionBarEdit = (CheckBox) findViewById(R.id.cb_edit_button);
        mIvActionBarMore.setVisibility(View.GONE);
        mTvActionBarTitle.setTextColor(Color.parseColor("#1c1c1c"));
        mIvBackButton.setImageResource(R.mipmap.back_arrow);
//		mActionBar.setBackgroundColor(Color.parseColor("#f5f5f5"));
        mIvBackButton.setOnClickListener(this);

    }

    /* (non-Javadoc)
         * @see android.support.v4.app.FragmentActivity#onNewIntent(android.content.Intent)
         */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        addtoFragment(intent);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

	/* (non-Javadoc)
	 * @see
	 */

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (login_flag.equals(StrCallback.RESPONSE_CODE_USER_OUT)) {
            if (mMaterialDialog!=null) {
                mMaterialDialog.dismiss();
            }
            finish();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    /* (non-Javadoc)
     * @see cn.stlc.app.BaseActivity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        APP.unRegister(this);
        super.onDestroy();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.i("选择图片成功返回");
        if (requestCode == IMAGE_REQUEST_CODE || requestCode == RESIZE_REQUEST_CODE || requestCode == CAMERA_REQUEST_CODE) {
            EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, requestCode + "", data));
        }
        if (requestCode == GoodsCommentFragment.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT,
                    GoodsCommentFragment.COMMENT_DATA, pathList));
            for (String path : pathList) {
                Logger.i("图片路径" + path);
            }
        }
        if (requestCode == WebPowerFragment.REQUEST_USER_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Logger.i("身份证图片路径" + path);
                EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT,
                        WebPowerFragment.USER_INFO_DATA, path));
            }
        }
        /**
         *  WebView JS选择图片
         */
        if (requestCode == WebPowerFragment.REQUEST_USER_IMG) {
            showToast(this,"选择图片返回");
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (Build.VERSION.SDK_INT >= 21) {//5.0以上版本处理
                onActivityResultAboveL(requestCode, resultCode, data);
                }else {
                EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                        WebPowerFragment.USER_INFO_IMG, result));
            }

        }
//        if (requestCode==WebPowerFragment.REQUEST_USER_IMG&&resultCode==RESULT_OK&&data != null){
//            if (Build.VERSION.SDK_INT >= 21) {//5.0以上版本处理
//                Uri uri = data.getData();
//                Uri[] uris = new Uri[]{uri};
//                EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT,
//                        WebPowerFragment.USER_INFO_IMG, uris));
//                   /* ClipData clipData = data.getClipData();  //选择多张
//                    if (clipData != null) {
//                        for (int i = 0; i < clipData.getItemCount(); i++) {
//                            ClipData.Item item = clipData.getItemAt(i);
//                            Uri uri = item.getUri();
//                            uris[i]=uri;
//                        }
//                    }*/
//            } else {//4.4以下处理
//                Uri uri = data.getData();
//                EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT,
//                        WebPowerFragment.USER_INFO_IMG, uri));
//            }
//        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != WebPowerFragment.REQUEST_USER_IMG ) return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                WebPowerFragment.USER_INFO_IMG, results));
    }

}
