package com.base.download.util;

import com.base.download.BuildConfig;

/**
 * 调试类
 * 
 * @author linxin
 * @Date 2013年11月9日 上午11:26:23
 */
public class Log {

	/**
	 * @see {@link android.util.Log#v(String, String)}
	 * @param tag
	 * @param msg
	 */
	public static void v(String tag, String msg) {
		if (BuildConfig.DEBUG)
			android.util.Log.v(tag, msg);
	}

	/**
	 * @see {@link android.util.Log#v(String, String, Throwable)}
	 * @param tag
	 * @param msg
	 * @param tr
	 */
	public static void v(String tag, String msg, Throwable tr) {
		if (BuildConfig.DEBUG)
			android.util.Log.v(tag, msg, tr);
	}

	/**
	 * @see {@link android.util.Log#d(String, String)}
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, String msg) {
		if (BuildConfig.DEBUG)
			android.util.Log.d(tag, msg);
	}

	/**
	 * @see {@link android.util.Log#d(String, String, Throwable)}
	 * @param tag
	 * @param msg
	 * @param tr
	 */
	public static void d(String tag, String msg, Throwable tr) {
		if (BuildConfig.DEBUG)
			android.util.Log.d(tag, msg, tr);
	}

	/**
	 * @see {@link android.util.Log#e(String, String)}
	 * @param tag
	 * @param msg
	 */
	public static void e(String tag, String msg) {
		if (BuildConfig.DEBUG)
			android.util.Log.e(tag, msg);
	}

	/**
	 * @see {@link android.util.Log#e(String, String, Throwable)}
	 * @param tag
	 * @param msg
	 * @param tr
	 */
	public static void e(String tag, String msg, Throwable tr) {
		if (BuildConfig.DEBUG)
			android.util.Log.e(tag, msg, tr);
	}

	/**
	 * @see {@link android.util.Log#i(String, String)}
	 * @param tag
	 * @param msg
	 */
	public static void i(String tag, String msg) {
		if (BuildConfig.DEBUG)
			android.util.Log.i(tag, msg);
	}

	/**
	 * @see {@link android.util.Log#i(String, String, Throwable)}
	 * @param tag
	 * @param msg
	 * @param tr
	 */
	public static void i(String tag, String msg, Throwable tr) {
		if (BuildConfig.DEBUG)
			android.util.Log.i(tag, msg, tr);
	}

	/**
	 * @see {@link android.util.Log#w(String, String)}
	 * @param tag
	 * @param msg
	 */
	public static void w(String tag, String msg) {
		if (BuildConfig.DEBUG)
			android.util.Log.w(tag, msg);
	}

	/**
	 * @see {@link android.util.Log#w(String, Throwable)}
	 * @param tag
	 * @param msg
	 */
	public static void w(String tag, Throwable tr) {
		if (BuildConfig.DEBUG)
			android.util.Log.w(tag, tr);
	}

	/**
	 * @see {@link android.util.Log#w(String, String, Throwable)}
	 * @param tag
	 * @param msg
	 * @param tr
	 */
	public static void w(String tag, String msg, Throwable tr) {
		if (BuildConfig.DEBUG)
			android.util.Log.w(tag, msg, tr);
	}

	/**
	 * @see {@link android.util.Log#wtf(String, String)}
	 * @param tag
	 * @param msg
	 */
	public static void wtf(String tag, String msg) {
		if (BuildConfig.DEBUG)
			android.util.Log.wtf(tag, msg);
	}

	/**
	 * @see {@link android.util.Log#wtf(String, Throwable)}
	 * @param tag
	 * @param msg
	 */
	public static void wtf(String tag, Throwable tr) {
		if (BuildConfig.DEBUG)
			android.util.Log.wtf(tag, tr);
	}

	/**
	 * @see {@link android.util.Log#wtf(String, String, Throwable)}
	 * @param tag
	 * @param msg
	 * @param tr
	 */
	public static void wtf(String tag, String msg, Throwable tr) {
		if (BuildConfig.DEBUG)
			android.util.Log.wtf(tag, msg, tr);
	}
}
