package com.base.live.livehelp.qny;

/**
 * Created by linbinghuang on 2018/5/2.
 */

public interface IQnyCallBack {
    void onInfo(int what, int extra);

    void onBufferingUpdate(int percent);

    void onCompletion();

    void onError(int errorCode);

    void capturepic(byte[] data, int size, int width, int height, int format, long ts);

    void onVideoFrameAvailable(byte[] data, int size, int width, int height, int format, long ts);

    void onAudioFrameAvailable(byte[] data, int size, int samplerate, int channels, int datawidth, long ts);
}
