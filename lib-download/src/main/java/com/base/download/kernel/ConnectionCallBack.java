package com.base.download.kernel;

/**
 * TODO linqiang form AppMarketUtil.CommonCallBack
 * 描述:
 * @author linqiang(866116)
 * @Since 2013-1-28
 * @param <E>
 */
public interface ConnectionCallBack<E> {
	public void invoke(final E... arg);
}
