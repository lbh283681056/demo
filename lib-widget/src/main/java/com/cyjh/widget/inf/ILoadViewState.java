package com.cyjh.widget.inf;

/**
 * 加载返回结果
 * @author linbinghuang
 *主要做上下啦刷新加载结果
 */
public interface ILoadViewState extends IloadViewResult{
	/**
	 * 完全结束
	 */
	 void onLoadComplete();

}
