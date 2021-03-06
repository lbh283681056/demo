package com.base.download.intf;

import android.os.Parcelable;

import com.base.download.BaseDownloadStateFactory.State;
import com.base.download.kernel.BaseDownloadInfo;

public interface IDownloadState extends Parcelable {

	/**
	 * 获取下载状态
	 * 
	 * @return
	 */
	public State getState();

	/**
	 * 点击事件
	 * 
	 * @param context
	 */
	public void onClick(IDownloadClickHelper<? extends BaseDownloadInfo> clickHelper);

	/**
	 * 显示
	 * 
	 * @param view
	 */
	public void display(IDownloadDisplayHelper<? extends BaseDownloadInfo> displayHelper);
}
