package com.base.live.livehelp.qny;

import android.content.Context;
import android.media.AudioManager;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.base.live.livehelp.base.BaseLiveHelp;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.PLOnAudioFrameListener;
import com.pili.pldroid.player.PLOnBufferingUpdateListener;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;

import java.io.IOException;

/**
 * Created by linbinghuang on 2018/3/5.
 * 七牛云播放器业务
 */

public class Qny1PlayModel extends BaseLiveHelp {

    private SurfaceView mSurfaceView;
    private PLMediaPlayer mMediaPlayer;
    private int mSurfaceWidth = 0;
    private int mSurfaceHeight = 0;

    private boolean mIsStopped = false;

    private AVOptions getAVOptions() {
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
        options.setInteger(AVOptions.KEY_VIDEO_DATA_CALLBACK, 0);
// 开启解码后的音频数据回调
// 默认值为 0，设置为 1 则开启
        options.setInteger(AVOptions.KEY_VIDEO_DATA_CALLBACK, 0);
        return options;
    }

    public void bindView(SurfaceView surfaceView) {
        mSurfaceView = surfaceView;
        prepare();

//        mMediaPlayer = new PLMediaPlayer(mActivity, getAVOptions());
//        mMediaPlayer.setOnInfoListener(mOnInfoListener);
//        mMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
//        mMediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
//        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
//        mMediaPlayer.setOnErrorListener(mOnErrorListener);
////        mMediaPlayer.setOnVideoFrameListener(mOnVideoFrameListener);
////        mMediaPlayer.setOnAudioFrameListener(mOnAudioFrameListener);
//        AudioManager audioManager = (AudioManager) mActivity.getSystemService(Context.AUDIO_SERVICE);
//        audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
//        mMediaPlayer.setWakeMode(mActivity, PowerManager.PARTIAL_WAKE_LOCK);
//        if (mSurfaceView != null) {
//            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
//        }
        mMediaPlayer.setOnAudioFrameListener(new PLOnAudioFrameListener() {
            @Override
            public void onAudioFrameAvailable(byte[] bytes, int i, int i1, int i2, int i3, long l) {

            }
        });
    }



//    @Override
//    public void start() {
//        if (mMediaPlayer != null) {
//            mMediaPlayer.start();
//        }
//    }
//
//
//
//    @Override
//    public void pause() {
//        if (mMediaPlayer != null) {
//            mMediaPlayer.pause();
//        }
//    }






    @Override
    public void setAudioEnable(boolean enable) {

    }

    @Override
    public void setVideoEnable(boolean enable) {

    }



    @Override
    public void onCreate(View playView) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        release();
        AudioManager audioManager = (AudioManager) mActivity.getSystemService(Context.AUDIO_SERVICE);
        audioManager.abandonAudioFocus(null);
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
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
        }
        mIsStopped = true;
        mMediaPlayer = null;
    }

    @Override
    public void reStar(String url) {

    }

    @Override
    public void capturePicture(String path) {

    }


    public void releaseWithoutStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.setDisplay(null);
        }
    }

    private void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void prepare() {
        if (mMediaPlayer != null) {
            if (mSurfaceView != null) {
                mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            }
            return;
        }

        try {
            mMediaPlayer = new PLMediaPlayer(mActivity, getAVOptions());
            mMediaPlayer.setOnPreparedListener(mOnPreparedListener);
            mMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
            mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            mMediaPlayer.setOnErrorListener(mOnErrorListener);
            mMediaPlayer.setOnInfoListener(mOnInfoListener);
            mMediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
            // set replay if completed
            // mMediaPlayer.setLooping(true);
            if (mSurfaceView != null) {
                mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            }
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    private SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            prepare();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // release();
            releaseWithoutStop();
        }
    };

    private PLOnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLOnVideoSizeChangedListener() {
        public void onVideoSizeChanged(int width, int height) {
            // resize the display window to fit the screen
            if (mSurfaceView == null) {
                return;
            }
            if (width != 0 && height != 0) {
                float ratioW = (float) width / (float) mSurfaceWidth;
                float ratioH = (float) height / (float) mSurfaceHeight;
                float ratio = Math.max(ratioW, ratioH);
                width = (int) Math.ceil((float) width / ratio);
                height = (int) Math.ceil((float) height / ratio);
                FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(width, height);
                layout.gravity = Gravity.CENTER;
                mSurfaceView.setLayoutParams(layout);
            }
        }
    };

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
            switch (what) {
                case PLOnInfoListener.MEDIA_INFO_BUFFERING_START:
                    break;
                case PLOnInfoListener.MEDIA_INFO_BUFFERING_END:
//                    mLoadingView.setVisibility(View.GONE);
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_RENDERING_START:
//                    mLoadingView.setVisibility(View.GONE);
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_GOP_TIME:
                    break;
                case PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START:
//                    mLoadingView.setVisibility(View.GONE);
                    break;
                case PLOnInfoListener.MEDIA_INFO_SWITCHING_SW_DECODE:
                    break;
                case PLOnInfoListener.MEDIA_INFO_METADATA:
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_BITRATE:
                case PLOnInfoListener.MEDIA_INFO_VIDEO_FPS:
                    break;
                case PLOnInfoListener.MEDIA_INFO_CONNECTED:
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                default:
                    break;
            }
        }
    };

    private PLOnBufferingUpdateListener mOnBufferingUpdateListener = new PLOnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(int percent) {
        }
    };

    /**
     * Listen the event of playing complete
     * For playing local file, it's called when reading the file EOF
     * For playing network stream, it's called when the buffered bytes played over
     * <p>
     * If setLooping(true) is called, the player will restart automatically
     * And ｀onCompletion｀ will not be called
     */
    private PLOnCompletionListener mOnCompletionListener = new PLOnCompletionListener() {
        @Override
        public void onCompletion() {
        }
    };

    private PLOnErrorListener mOnErrorListener = new PLOnErrorListener() {
        @Override
        public boolean onError(int errorCode) {
            switch (errorCode) {
                case PLOnErrorListener.ERROR_CODE_IO_ERROR:
                    /**
                     * SDK will do reconnecting automatically
                     */
                    return false;
                case PLOnErrorListener.ERROR_CODE_OPEN_FAILED:
                    break;
                case PLOnErrorListener.ERROR_CODE_SEEK_FAILED:
                    break;
                default:
                    break;
            }
            return true;
        }
    };


}