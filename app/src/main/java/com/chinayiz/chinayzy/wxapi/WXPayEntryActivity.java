package com.chinayiz.chinayzy.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler,EventBusCallback {
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;
	public static final String WECHAT_BACK="WECHAT_BACK";
	public static final int WECHAR_BACK=1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        EventBus.getDefault().register(this);
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.i(TAG, "onPayFinish, errCode = " + resp.errCode);

//	   Intent data=new Intent(this, CommonActivity.class);
//		data.putExtra("code",resp.errCode);
//		setResult(WECHAR_BACK,data);
		finish();
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,WECHAT_BACK,resp));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void runUiThread(EventMessage message) {

	}

	@Override
	@Subscribe (threadMode = ThreadMode.BACKGROUND)
	public void runBgThread(EventMessage message) {

	}

	@Override
	public void disposeNetMsg(EventMessage message) {

	}

	@Override
	public void disposeInfoMsg(EventMessage message) {

	}
}