package com.base.widget.swiperefreshlayout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.AbsListView;

/**
 * 默认下来刷新
 * 
 * @author linbinghuang
 * @param <T>
 *
 */
public abstract class ReHeadDefaultSwipeRefreshLayout<T> extends
		ReDefaultSwipeRefreshLayout {

	public ReHeadDefaultSwipeRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean canChildScrollUp() {
		if (android.os.Build.VERSION.SDK_INT < 14) {
			if (mListView instanceof AbsListView) {
				final AbsListView absListView = (AbsListView) mListView;
				return absListView.getChildCount() > 0
						&& (absListView.getFirstVisiblePosition() > 0 || absListView
								.getChildAt(0).getTop() < absListView
								.getPaddingTop());
			} else {
				return mListView.getScrollY() > 0;
			}
		} else {
			return ViewCompat.canScrollVertically(mListView, -1);
		}
	}

}
