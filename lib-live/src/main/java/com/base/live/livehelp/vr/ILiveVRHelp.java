package com.base.live.livehelp.vr;


import android.app.Activity;

/**
 * vr接口
 * Created by linbinghuang on 2017/7/31.
 */
public interface ILiveVRHelp {
    /**
     * 切换模式
     *
     * @param mActivity
     * @param i
     */
    void switchInteractiveMode(Activity mActivity, int i);
    /**
     * 切换模式
     *
     * @param mActivity
     * @param i
     */
    void switchDisplayMode(Activity mActivity, int i);
    /**
     * 转屏幕调用
     * @param activity
     */
    void onConfigurationChanged(Activity activity);


    /**
     * vr的生命周期
     */
     void onResume() ;

    /**
     * vr的生命周期
     */
     void onPause() ;

}
