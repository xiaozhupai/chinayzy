package com.chinayiz.chinayzy.views.goodsParticular;

import android.content.Context;
import android.view.View;


public class TopDetailInfoPage implements McoySnapPageLayout.McoySnapPage{
	
	private Context context;
	private View rootView = null;

	private McoyScrollView mcoyScrollView = null;

	public TopDetailInfoPage(Context context, View rootView ,int resID) {
		this.context = context;
		this.rootView = rootView;
		mcoyScrollView = (McoyScrollView) this.rootView.findViewById(resID);
	}

	public McoyScrollView getMcoyScrollView() {
		return mcoyScrollView;
	}

	@Override
	public View getRootView() {
		return rootView;
	}

	@Override
	public boolean isAtTop() {
		return true;
	}

	@Override
	public boolean isAtBottom() {
        int scrollY = mcoyScrollView.getScrollY();
        int height = mcoyScrollView.getHeight();
        int scrollViewMeasuredHeight = mcoyScrollView.getChildAt(0).getMeasuredHeight();
        if ((scrollY + height) >= scrollViewMeasuredHeight) {
            return true;
        }
        return false;
	}

}
