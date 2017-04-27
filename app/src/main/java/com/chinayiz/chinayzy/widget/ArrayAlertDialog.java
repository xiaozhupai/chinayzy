package com.chinayiz.chinayzy.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

import java.util.Arrays;
import java.util.List;



/**
 * 仿照IOS 底部弹出框
 */
public class ArrayAlertDialog extends Dialog implements View.OnClickListener {
	private int mGravity;
	private Context mContext;
	private List<String> data;
	private OnItemClickListener listener;
	private String titleString;

	/**
	 * 创建自定义dialog
	 *
	 * @param context context
	 * @param gravity 布局文件的对齐方式
	 * @param data    data
	 */
	public ArrayAlertDialog(Context context, int gravity, String[] data) {
		this(context, gravity, Arrays.asList(data));
	}

	/**
	 * 创建自定义dialog
	 *
	 * @param context context
	 * @param gravity 布局文件的对齐方式
	 * @param data    data
	 */

	public ArrayAlertDialog(Context context, int gravity, List<String> data) {
		super(context, R.style.Dialog);
		this.mContext = context;
		this.mGravity = gravity;
		this.data = data;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_array, null);
		ListView lv = (ListView) view.findViewById(R.id.custom_array_lv);
		LinearLayout titleLayout = (LinearLayout) view.findViewById(R.id.custom_array_title_layout);
		TextView title = (TextView) view.findViewById(R.id.custom_array_title);
		if (!TextUtils.isEmpty(titleString)) {
			titleLayout.setVisibility(View.VISIBLE);
			title.setText(titleString);
		}
		TextView cancel = (TextView) view.findViewById(R.id.custom_array_cancel);
		cancel.setOnClickListener(this);
		lv.setAdapter(new ArrayAdapter<>(mContext, R.layout.dialog_array_item, R.id.custom_array_item_name, data));
		lv.setOnItemClickListener(listener);

		setContentView(view);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(mGravity);
	}

	public void setTitle(String titleString) {
		this.titleString = titleString;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.custom_array_cancel:
				this.dismiss();
				break;
		}
	}
}
