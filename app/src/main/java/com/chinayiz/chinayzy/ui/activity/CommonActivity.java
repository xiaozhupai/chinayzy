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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.presenter.Presenter;
import com.orhanobut.logger.Logger;

import java.util.Stack;




/**
 * @author Luki
 */

public class CommonActivity extends BaseActivity<Presenter> implements FragmentManager.OnBackStackChangedListener {
	private Stack<BaseFragment> ftStack = new Stack<>();



	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.iv_back_button:
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
	protected void initActionBar() {
		//actionbar
		mActionBar=findViewById(R.id.rl_ActionBar);
		mIvBackButton = (ImageView) findViewById(R.id.iv_back_button);
		mTvActionBarTitle = (TextView) findViewById(R.id.tv_actionbar_title);
		mIvActionBarMore = (ImageView) findViewById(R.id.iv_more_button);
		mIvActionBarCart= (ImageView) findViewById(R.id.iv_shopcart);
		mCbActionBarEdit= (CheckBox) findViewById(R.id.cb_edit_button);
		mTvActionBarTitle.setText("个人中心");
		mIvActionBarMore.setVisibility(View.GONE);
		mTvActionBarTitle.setTextColor(getResources().getColor(R.color.white));
		mIvBackButton.setImageResource(R.mipmap.back_arrow);
		mActionBar.setBackgroundColor(Color.parseColor("#ff3951"));
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
	public void onResume() {
		super.onResume();

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
	protected Presenter initPresenter() {
		return new Presenter();
	}

	@Override
	protected void onCreateActivity(Bundle savedInstanceState) {

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}


}
