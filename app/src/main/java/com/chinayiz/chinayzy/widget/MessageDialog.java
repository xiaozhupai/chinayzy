/**
 * Copyright (C) 2014 Luki(liulongke@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chinayiz.chinayzy.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chinayiz.chinayzy.R;
import static android.renderscript.Sampler.Value.WRAP;
import android.view.Gravity;


import static android.view.View.VISIBLE;


/**
 * @author Luki
 */
public class MessageDialog extends DialogUtils.XDialog implements View.OnClickListener {

	private TextView  vButton1, vButton2;
	private View vDivider;
	public TextView message;


	/**
	 * @param context
	 */
	public MessageDialog(Context context) {
		super(context);
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.CENTER);
		setContentView(R.layout.dialog_message);
	  message= (TextView) findViewById(R.id.dialog_message);
		vButton1 = (TextView) findViewById(R.id.dialog_button1);
		vButton2 = (TextView) findViewById(R.id.dialog_button2);
		vDivider = findViewById(R.id.dialog_divider);
		vButton1.setOnClickListener(this);
		vButton2.setOnClickListener(this);
	}

	/**
	 * @param text the vButton1 to set
	 */
	public void setButton1(String text, View.OnClickListener listener) {
		vButton1.setText(text);
		if (listener != null) {
			vButton1.setOnClickListener(listener);
		}
	}

	/**
	 * @param text the vButton1 to set
	 */
	public void setButton2(String text, View.OnClickListener listener) {
		vButton2.setText(text);
		vButton1.setBackgroundResource(R.drawable.dialog_corner_left);
		vDivider.setVisibility(VISIBLE);
		vButton2.setVisibility(VISIBLE);
		if (listener != null) {
			vButton2.setOnClickListener(listener);
		}
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		dismiss();
	}

}
