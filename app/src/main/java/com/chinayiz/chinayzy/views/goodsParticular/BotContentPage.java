package com.chinayiz.chinayzy.views.goodsParticular;

import android.content.Context;
import android.view.View;

public class BotContentPage implements McoySnapPageLayout.McoySnapPage {
	private Context context;
	private View rootView = null;

	private ScrollListener mScrollListener;

	public void setScrollListener(ScrollListener scrollListener) {
		mScrollListener = scrollListener;
	}
	public BotContentPage(Context context, View rootView) {
		this.context = context;
		this.rootView = rootView;
	}
	@Override
	public View getRootView() {
		return rootView;
	}

	@Override
	public boolean isAtTop() {
		if (mScrollListener!=null){
			return mScrollListener.isAtTop();
		}
		return true;
	}

	@Override
	public boolean isAtBottom() {
		return false;
	}

	/**
	 * 解决相关商品无法滑动到顶部bug
	 */
	public interface ScrollListener{
		/**
		 *  子视图是否滑到最顶部
		 * @return
		 */
		boolean isAtTop();

	}
}
