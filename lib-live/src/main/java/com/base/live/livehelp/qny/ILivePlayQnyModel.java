package com.base.live.livehelp.qny;


/**
 * 七牛云播放
 */
public interface ILivePlayQnyModel  {
    /**
     * 设置回调
     * @param mQnyCallBack
     */
    void setQnyCallBack(IQnyCallBack mQnyCallBack);
    /**
     * 绑定地址
     * @param path
     */
    void bindData(String path);
    /**
     * 跳转进度
     * @param seek
     */
    void seekTo(long seek);
    /**
     * 是否正在播放
     * @return
     */
    boolean isPlaying();

    /**
     * 当前播放的位置
     * @return
     */
    long getCurrentPosition();

    /**
     * 暂停播放
     */
    void pause();

    /**
     * 是否循环播放
     * @param loop
     */
    void setLoop(boolean loop);
    /**
     * 不带地址的播放
     */
    void start();

    /**
     * 生命周期
     */
    void onPause();

    /**
     * 生命周期
     */
    void onResume();
}
