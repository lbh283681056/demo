package com.base.live.livehelp.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

/**
 */

public interface IBaseLive {
    /**
     * 绑定数据
     * @param activity
     * @param bundle
     */
    void bindData(Activity activity, Bundle bundle);


    /**
     * 创建播放
     */
    void onCreate( View playView);

    /**
     * 销毁播放
     */
    void onDestroy();
    /**
     * 开始播放
     */
    void start(String url);
    /**
     * 停止播放
     */
    void stop();

    /**
     * 重新开播
     * @param url
     */
    void reStar(String url);
    /**
     * 设置回调
     */
    void setCallBack(Handler callBackHandler);

    /**
     * 截图
     * @param path
     */
    void capturePicture(String path);
    /**
     * 禁止音频
     * @param enable
     */
    void setAudioEnable(boolean enable);

    /**
     * 禁止音视频
     * @param enable
     */
    void  setVideoEnable(boolean enable);
}
