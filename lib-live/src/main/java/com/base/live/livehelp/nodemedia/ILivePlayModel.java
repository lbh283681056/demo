package com.base.live.livehelp.nodemedia;


import android.view.View;

import com.base.live.livehelp.base.IBaseLive;

public interface ILivePlayModel extends IBaseLive {

    /**
     * 创建播放
     * @param playView
     * @param mixBuffer
     * @param maxBuffer
     */
    void onCreate(View playView, int mixBuffer, int maxBuffer);


    /**
     * 获取渲染的view
     * @return
     */
    View getRenderView();
}
