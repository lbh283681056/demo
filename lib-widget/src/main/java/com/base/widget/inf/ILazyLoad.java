package com.base.widget.inf;



/**
  * @ClassName: ILazyLoad
  * @Description: 添加loadData
  * @author Comsys-linbinghuang
  * @date 2014-9-1 下午2:17:58
  *
  */
public interface ILazyLoad {

	/**
	 * 加载数据
	 *
	 * @return
	 */
	 boolean firstdata();


	/** 可以传入页数
	 * @param page
	 */
	 void loadData(int page);
}
