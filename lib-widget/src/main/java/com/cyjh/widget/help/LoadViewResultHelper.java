package com.cyjh.widget.help;

import com.cyjh.widget.adapter.IAdapterHelp;
import com.cyjh.widget.inf.ILoadState;
import com.cyjh.widget.inf.ILoadViewState;
import com.cyjh.widget.inf.IloadViewResult;

import java.util.List;

/**
 * @author linbinghuang
 * 记载后逻辑处理
 */
public class LoadViewResultHelper {

//	/**
//	 * 加載空或加載成功后的布局
//	 * @param data 本次加载的数据
//	 * @param isLastPage 是否是最后一页
//	 * @param adapterHelp 适配器帮助接口
//	 * @param viewResult
//	 * @param iLoadState
//	 */
//	public static void loadIsEmpty(List data, int isLastPage,
//								   IAdapterHelp adapterHelp, ILoadViewState viewResult,
//								   ILoadState iLoadState) {
//		if (data == null||data.isEmpty()) {
//			if (adapterHelp == null || adapterHelp.getCount() == 0) {
//				iLoadState.onLoadEmpty();
//			} else {
//				viewResult.onLoadEmpty();
//			}
//		} else {
//			viewResult.onLoadSuccess();
//			if (isLastPage==1) {
//				viewResult.onLoadComplete();
//			}
//		}
//	}
	/**
	 * 加載空或加載成功后的布局
	 * @param data 本次加载的数据
	 * @param isLastPage 是否是最后一页
	 * @param adapterHelp 适配器帮助接口
	 * @param viewResult
	 * @param iLoadState
	 */
	public static void loadIsEmpty(List data, int isLastPage,
								   IAdapterHelp adapterHelp, ILoadViewState viewResult,
								   IloadViewResult iLoadState) {
		if (data == null || data.isEmpty()) {
			if (adapterHelp == null || adapterHelp.getCount() == 0) {
				iLoadState.onLoadEmpty();
			} else {
				viewResult.onLoadEmpty();
			}
		} else {
			iLoadState.onLoadSuccess();
			viewResult.onLoadSuccess();
			if (isLastPage==1) {
				viewResult.onLoadComplete();
			}
		}
	}

	/**
	 * 加载失败后布局
	 * @param adapterHelp
	 * @param viewResult
	 * @param iLoadState
	 */
	public static void loadIsFailed(IAdapterHelp adapterHelp,
									ILoadViewState viewResult, IloadViewResult iLoadState) {
		if (adapterHelp == null || adapterHelp.getCount() == 0) {
			iLoadState.onLoadFailed();
		} else {
			viewResult.onLoadFailed();
		}
	}
	/**
	 * 加载失败后布局
	 * @param adapterHelp
	 * @param viewResult
	 * @param iLoadState
	 */
	public static void loadIsFailed(IAdapterHelp adapterHelp,
									ILoadViewState viewResult, ILoadState iLoadState) {
		if (adapterHelp == null || adapterHelp.getCount() == 0) {
			iLoadState.onLoadFailed();
		} else {
			viewResult.onLoadFailed();
		}
	}

}
