package com.base.live.livehelp.qny;



import android.content.Context;
import android.media.AudioManager;
import android.os.Message;

import com.base.live.livehelp.base.BaseLiveHelp;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.PLOnBufferingUpdateListener;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;

import java.io.IOException;

/**
 * Created by linbinghuang on 2018/5/2.
 */

public abstract class QnyBasePlayModel  extends BaseLiveHelp implements ILivePlayQnyModel{
    protected static final String TAG = QnyBasePlayModel.class.getSimpleName();
    protected PLMediaPlayer mMediaPlayer;
    protected boolean mIsStopped = false;
    /**
     * 回调
     */
    protected IQnyCallBack mQnyCallBack;

    @Override
    public void setQnyCallBack(IQnyCallBack mQnyCallBack) {
        this.mQnyCallBack = mQnyCallBack;
    }
    @Override
    public void onResume() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }
    @Override
    public void onPause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        release();
        AudioManager audioManager = (AudioManager) mActivity.getSystemService(Context.AUDIO_SERVICE);
        audioManager.abandonAudioFocus(null);
    }
    @Override
    public void setAudioEnable(boolean enable) {
        if (mMediaPlayer != null) {
        }
    }
    @Override
    public void setVideoEnable(boolean enable) {
        if (mMediaPlayer != null) {
            mMediaPlayer.setVideoEnabled(enable);
        }
    }
    @Override
    public void start(String url) {
        if (mIsStopped) {
            prepare();
        } else {
            try {
                mMediaPlayer.setDataSource(url);
                mMediaPlayer.prepareAsync();
                mMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void start() {
        if (mIsStopped) {
            prepare();
        } else {
            mMediaPlayer.start();
        }
    }
    @Override
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
        }
        mIsStopped = true;
        mMediaPlayer = null;
    }
    @Override
    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }
    @Override
    public void reStar(String url) {
        stop();
        start(url);

    }
    protected void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    @Override
    public boolean isPlaying() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        }
        return false;
    }
    @Override
    public long getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }
    @Override
    public void setLoop(boolean loop) {
        if (mMediaPlayer != null) {
            mMediaPlayer.setLooping(loop);
        }
    }
    @Override
    public void seekTo(long seek) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(seek);
        }
    }
    protected AVOptions getAVOptions() {
        AVOptions options = new AVOptions();

        // DNS 服务器设置
// 若不设置此项，则默认使用 DNSPod 的 httpdns 服务
// 若设置为 127.0.0.1，则会使用系统的 DNS 服务器
// 若设置为其他 DNS 服务器地址，则会使用设置的服务器
        // options.setString(AVOptions.KEY_DNS_SERVER, server);
// DNS 缓存设置
// 若不设置此项，则每次播放未缓存的域名时都会进行 DNS 解析，并将结果缓存
// 参数为 String[]，包含了要缓存 DNS 结果的域名列表
// SDK 在初始化时会解析列表中的域名，并将结果缓存
        //   options.setStringArray(AVOptions.KEY_DOMAIN_LIST, domainList);
// 解码方式:
// codec＝AVOptions.MEDIA_CODEC_HW_DECODE，硬解
// codec=AVOptions.MEDIA_CODEC_SW_DECODE, 软解
// codec=AVOptions.MEDIA_CODEC_AUTO, 硬解优先，失败后自动切换到软解
// 默认值是：MEDIA_CODEC_SW_DECODE
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_HW_DECODE);
// 若设置为 1，则底层会进行一些针对直播流的优化
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
// 快开模式，启用后会加快该播放器实例再次打开相同协议的视频流的速度
        options.setInteger(AVOptions.KEY_FAST_OPEN, 1);
// 打开重试次数，设置后若打开流地址失败，则会进行重试
        options.setInteger(AVOptions.KEY_OPEN_RETRY_TIMES, 5);
// 预设置 SDK 的 log 等级， 0-4 分别为 v/d/i/w/e
        options.setInteger(AVOptions.KEY_LOG_LEVEL, 2);
// 准备超时时间，包括创建资源、建立连接、请求码流等，单位是 ms
// 默认值是：无
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
// 默认的缓存大小，单位是 ms
// 默认值是：500
        options.setInteger(AVOptions.KEY_CACHE_BUFFER_DURATION, 500);
// 最大的缓存大小，单位是 ms
// 默认值是：2000，若设置值小于 KEY_CACHE_BUFFER_DURATION 则不会生效
        options.setInteger(AVOptions.KEY_MAX_CACHE_BUFFER_DURATION, 4000);
// 是否开启直播优化，1 为开启，0 为关闭。若开启，视频暂停后再次开始播放时会触发追帧机制
// 默认为 0
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
// 设置拖动模式，1 位精准模式，即会拖动到时间戳的那一秒；0 为普通模式，会拖动到时间戳最近的关键帧。默认为 0
//        options.setInteger(AVOptions.KEY_SEEK_MODE);
// 设置 DRM 密钥
//        byte[] key = {xxx, xxx, xxx, xxx, xxx ……};
//        options.setByteArray(AVOptions.KEY_DRM_KEY, key);
// 设置偏好的视频格式，设置后会加快对应格式视频流的打开速度，但播放其他格式会出错
// m3u8 = 1, mp4 = 2, flv = 3
        options.setInteger(AVOptions.KEY_PREFER_FORMAT, 3);
// 开启解码后的视频数据回调
// 默认值为 0，设置为 1 则开启
        options.setInteger(AVOptions.KEY_VIDEO_DATA_CALLBACK, 1);
// 开启解码后的音频数据回调
// 默认值为 0，设置为 1 则开启
        options.setInteger(AVOptions.KEY_VIDEO_DATA_CALLBACK, 1);
        return options;
    }
    protected void prepare() {
        try {
            mMediaPlayer = new PLMediaPlayer(mActivity, getAVOptions());
            mMediaPlayer.setOnPreparedListener(mOnPreparedListener);
            mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            mMediaPlayer.setOnErrorListener(mOnErrorListener);
            mMediaPlayer.setOnInfoListener(mOnInfoListener);
            mMediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    private PLOnPreparedListener mOnPreparedListener = new PLOnPreparedListener() {
        @Override
        public void onPrepared(int preparedTime) {
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
                mIsStopped = false;
            }
        }

    };

    private PLOnInfoListener mOnInfoListener = new PLOnInfoListener() {
        @Override
        public void onInfo(int what, int extra) {
            if (mQnyCallBack != null) {
                mQnyCallBack.onInfo(what,extra);
            }
        }
    };

    private PLOnBufferingUpdateListener mOnBufferingUpdateListener = new PLOnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(int percent) {
            if (mQnyCallBack != null) {
                mQnyCallBack.onBufferingUpdate(percent);
            }
        }
    };


    private PLOnCompletionListener mOnCompletionListener = new PLOnCompletionListener() {
        @Override
        public void onCompletion() {
            if (mQnyCallBack != null) {
                mQnyCallBack.onCompletion();
            }
        }
    };

    private PLOnErrorListener mOnErrorListener = new PLOnErrorListener() {
        @Override
        public boolean onError(int errorCode) {
            if (mQnyCallBack != null) {
                mQnyCallBack.onError(errorCode);
            }
            return true;
        }
    };





}
