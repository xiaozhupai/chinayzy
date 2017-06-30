package com.chinayiz.chinayzy.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.WechatAccessModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.Login.LoginNet;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler,EventBusCallback {
	
	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
	
	private Button gotoBtn, regBtn, launchBtn, checkBtn, payBtn, favButton;
	
	// IWXAPI �ǵ�����app��΢��ͨ�ŵ�openapi�ӿ�
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        EventBus.getDefault().register(this);
        // ͨ��WXAPIFactory��������ȡIWXAPI��ʵ��
    	api = WXAPIFactory.createWXAPI(this,Constants.APP_ID, false);

    	regBtn = (Button) findViewById(R.id.reg_btn);
    	regBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ����appע�ᵽ΢��
			    api.registerApp(Constants.APP_ID);
			}
		});
    	
        gotoBtn = (Button) findViewById(R.id.goto_send_btn);
//        gotoBtn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//		        startActivity(new Intent(WXEntryActivity.this, SendToWXActivity.class));
//		        finish();
//			}
//		});
        
        launchBtn = (Button) findViewById(R.id.launch_wx_btn);
        launchBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(WXEntryActivity.this, "launch result = " + api.openWXApp(), Toast.LENGTH_LONG).show();
			}
		});
        
        checkBtn = (Button) findViewById(R.id.check_timeline_supported_btn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int wxSdkVersion = api.getWXAppSupportAPI();
				if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
					Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline supported", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline not supported", Toast.LENGTH_LONG).show();
				}
			}
		});
        
        payBtn = (Button) findViewById(R.id.goto_pay_btn);
//        payBtn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(WXEntryActivity.this, PayActivity.class));
//		        finish();
//			}
//		});
//
//        favButton = (Button) findViewById(R.id.goto_fav_btn);
//        favButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(WXEntryActivity.this, AddFavoriteToWXActivity.class));
//				finish();
//			}
//		});
        
        // debug
       
        // debug end
        
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	// ΢�ŷ������󵽵�����Ӧ��ʱ����ص����÷���
	@Override
	public void onReq(BaseReq req) {
		Toast.makeText(this, "openid = " + req.openId, Toast.LENGTH_SHORT).show();
		
		switch (req.getType()) {
		case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
//			goToGetMsg();
			break;
		case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
			goToShowMsg((ShowMessageFromWX.Req) req);
			break;
		case ConstantsAPI.COMMAND_LAUNCH_BY_WX:
			Toast.makeText(this, R.string.launch_from_wx, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}



	// ������Ӧ�÷��͵�΢�ŵ�����������Ӧ�������ص����÷���
	@Override
	public void onResp(BaseResp resp) {
		if (APP.APP_DBG){
			Toast.makeText(this, "openid = " + resp.openId, Toast.LENGTH_SHORT).show();

			if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
				Toast.makeText(this, "code = " + ((SendAuth.Resp) resp).code, Toast.LENGTH_SHORT).show();
			}
		}

		int result = 0;
		
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			if (resp instanceof  SendAuth.Resp){  //登录成功的回调
				LoginNet.getLoginNet().togetAccessToken(((SendAuth.Resp) resp).code);
			}else {  //分享成功的回调

			}
			result = R.string.errcode_success;

			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			result = R.string.errcode_cancel;
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			result = R.string.errcode_deny;
			break;
		default:
			result = R.string.errcode_unknown;
			break;
		}
		finish();
//		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}
	
//	private void goToGetMsg() {
//		Intent intent = new Intent(this, GetFromWXActivity.class);
//		intent.putExtras(getIntent());
//		startActivity(intent);
//		finish();
//	}
	
	private void goToShowMsg(ShowMessageFromWX.Req showReq) {
		WXMediaMessage wxMsg = showReq.message;
		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
		
		StringBuffer msg = new StringBuffer(); // ��֯һ������ʾ����Ϣ����
		msg.append("description: ");
		msg.append(wxMsg.description);
		msg.append("\n");
		msg.append("extInfo: ");
		msg.append(obj.extInfo);
		msg.append("\n");
		msg.append("filePath: ");
		msg.append(obj.filePath);
		
//		Intent intent = new Intent(this, ShowFromWXActivity.class);
//		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
//		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
//		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
//		startActivity(intent);
//		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
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

	}

	@Override
	public void disposeNetMsg(EventMessage message) {
       switch (message.getDataType()){
		   case Commons.ACCESS_TOKEN:
             WechatAccessModel model= (WechatAccessModel) message.getData();
			 if (model.getExpires_in()>7200){   //access_token接口调用超时
                   LoginNet.getLoginNet().toRefreshToken(model.getRefresh_token());
			 }
		   	break;
		   case Commons.REFRESH_TOKEN:
			   WechatAccessModel model2= (WechatAccessModel) message.getData();
			   LoginNet.getLoginNet().togetWechatUserInfo(model2.getAccess_token(),model2.getOpenid());
		   	break;
		   case Commons.WECHAT_USERINFO:

		   	break;
	   }
	}

	@Override
	public void disposeInfoMsg(EventMessage message) {

	}
}