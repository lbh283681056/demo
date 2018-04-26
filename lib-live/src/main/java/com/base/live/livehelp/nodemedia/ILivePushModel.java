package com.base.live.livehelp.nodemedia;


import com.base.live.livehelp.base.IBaseLive;

public interface ILivePushModel extends IBaseLive {



    /**
     * 闪光灯
     * @param isOpen
     */
    void setFlsh(boolean isOpen);

    /**
     * 摄像头
     */
    void switchCamera();

}
