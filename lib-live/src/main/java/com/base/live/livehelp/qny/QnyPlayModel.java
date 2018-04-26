package com.base.live.livehelp.qny;//package com.miyou.xylive.qly;
//
//import android.graphics.ImageFormat;
//import android.graphics.Rect;
//import android.graphics.YuvImage;
//import android.os.Message;
//import android.util.Log;
//
//import com.miyou.xylive.qly.base.BaseLiveHelp;
//import com.pili.pldroid.player.AVOptions;
//import com.pili.pldroid.player.PLOnAudioFrameListener;
//import com.pili.pldroid.player.PLOnBufferingUpdateListener;
//import com.pili.pldroid.player.PLOnCompletionListener;
//import com.pili.pldroid.player.PLOnErrorListener;
//import com.pili.pldroid.player.PLOnImageCapturedListener;
//import com.pili.pldroid.player.PLOnInfoListener;
//import com.pili.pldroid.player.PLOnVideoFrameListener;
//import com.pili.pldroid.player.PLOnVideoSizeChangedListener;
//import com.pili.pldroid.player.widget.PLVideoView;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * Created by linbinghuang on 2018/3/5.
// * 七牛云播放器业务
// */
//
//public class QnyPlayModel extends BaseLiveHelp {
//    private static final String TAG = "QnyPlayModel";
//    private PLVideoView mVideoView;
//    private boolean isCaptured;
//    public void bindView(PLVideoView videoView) {
//        mVideoView = videoView;
//        mVideoView.setAVOptions(getAVOptions());
//
//
//        mVideoView.setOnInfoListener(mOnInfoListener);
//        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
//        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
//        mVideoView.setOnCompletionListener(mOnCompletionListener);
//        mVideoView.setOnErrorListener(mOnErrorListener);
//        mVideoView.setOnVideoFrameListener(mOnVideoFrameListener);
//        mVideoView.setOnAudioFrameListener(mOnAudioFrameListener);
//        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
//
//        mVideoView.setOnImageCapturedListener(mPLOnImageCapturedListener);
////        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_16_9);
//    }
//
//    private AVOptions getAVOptions() {
//        AVOptions options = new AVOptions();
//        // DNS 服务器设置
//// 若不设置此项，则默认使用 DNSPod 的 httpdns 服务
//// 若设置为 127.0.0.1，则会使用系统的 DNS 服务器
//// 若设置为其他 DNS 服务器地址，则会使用设置的服务器
//        // options.setString(AVOptions.KEY_DNS_SERVER, server);
//// DNS 缓存设置
//// 若不设置此项，则每次播放未缓存的域名时都会进行 DNS 解析，并将结果缓存
//// 参数为 String[]，包含了要缓存 DNS 结果的域名列表
//// SDK 在初始化时会解析列表中的域名，并将结果缓存
//        //   options.setStringArray(AVOptions.KEY_DOMAIN_LIST, domainList);
//// 解码方式:
//// codec＝AVOptions.MEDIA_CODEC_HW_DECODE，硬解
//// codec=AVOptions.MEDIA_CODEC_SW_DECODE, 软解
//// codec=AVOptions.MEDIA_CODEC_AUTO, 硬解优先，失败后自动切换到软解
//// 默认值是：MEDIA_CODEC_SW_DECODE
//        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_HW_DECODE);
//// 若设置为 1，则底层会进行一些针对直播流的优化
//        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
//// 快开模式，启用后会加快该播放器实例再次打开相同协议的视频流的速度
//        options.setInteger(AVOptions.KEY_FAST_OPEN, 1);
//// 打开重试次数，设置后若打开流地址失败，则会进行重试
//        options.setInteger(AVOptions.KEY_OPEN_RETRY_TIMES, 5);
//// 预设置 SDK 的 log 等级， 0-4 分别为 v/d/i/w/e
//        options.setInteger(AVOptions.KEY_LOG_LEVEL, 2);
//// 准备超时时间，包括创建资源、建立连接、请求码流等，单位是 ms
//// 默认值是：无
//        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
//// 默认的缓存大小，单位是 ms
//// 默认值是：500
//        options.setInteger(AVOptions.KEY_CACHE_BUFFER_DURATION, 0);
//// 最大的缓存大小，单位是 ms
//// 默认值是：2000，若设置值小于 KEY_CACHE_BUFFER_DURATION 则不会生效
//        options.setInteger(AVOptions.KEY_MAX_CACHE_BUFFER_DURATION, 3000);
//// 是否开启直播优化，1 为开启，0 为关闭。若开启，视频暂停后再次开始播放时会触发追帧机制
//// 默认为 0
//        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
//// 设置拖动模式，1 位精准模式，即会拖动到时间戳的那一秒；0 为普通模式，会拖动到时间戳最近的关键帧。默认为 0
////        options.setInteger(AVOptions.KEY_SEEK_MODE);
//// 设置 DRM 密钥
////        byte[] key = {xxx, xxx, xxx, xxx, xxx ……};
////        options.setByteArray(AVOptions.KEY_DRM_KEY, key);
//// 设置偏好的视频格式，设置后会加快对应格式视频流的打开速度，但播放其他格式会出错
//// m3u8 = 1, mp4 = 2, flv = 3
//        options.setInteger(AVOptions.KEY_PREFER_FORMAT, 3);
//// 开启解码后的视频数据回调
//// 默认值为 0，设置为 1 则开启
//        options.setInteger(AVOptions.KEY_VIDEO_DATA_CALLBACK, 1);
//// 开启解码后的音频数据回调
//// 默认值为 0，设置为 1 则开启
//        options.setInteger(AVOptions.KEY_AUDIO_DATA_CALLBACK, 0);
//        return options;
//    }
//
//    @Override
//    public void startPlay(String url) {
//        mVideoView.setVideoPath(url);
//        start();
//    }
//
//    @Override
//    public void start() {
//        mVideoView.start();
//    }
//
//    @Override
//    public void stopPlay() {
//        mVideoView.stopPlayback();
//    }
//
//    @Override
//    public void pause() {
//        mVideoView.pause();
//    }
//
//    @Override
//    public void stopPlayback() {
//        mVideoView.stopPlayback();
//
//    }
//
//    @Override
//    public void capturePicture() {
//        isCaptured = true;
////        mVideoView.captureImage(0);
//    }
//
//    @Override
//    public boolean capturePicture(String path) {
//        isCaptured = true;
//
//        return true;
//    }
//
//
//    @Override
//    public void onResume() {
//        start();
//    }
//
//    @Override
//    public void onPause() {
//        pause();
//    }
//
//    @Override
//    public void onDestroy() {
//        if(mVideoView!=null) {
//            mVideoView.stopPlayback();
//        }
//        if (mCallBackHandler != null) {
//            mCallBackHandler.removeCallbacksAndMessages(null);
//        }
//    }
//
//    @Override
//    public void reStartPlay() {
//
//    }
//
//    private PLOnInfoListener mOnInfoListener = new PLOnInfoListener() {
//        @Override
//        public void onInfo(int what, int extra) {
//            callBack(what);
//            Log.i(TAG, "OnInfo, what = " + what + ", extra = " + extra);
//            switch (what) {
//                case PLOnInfoListener.MEDIA_INFO_BUFFERING_START:
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_BUFFERING_END:
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_RENDERING_START:
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START:
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_FRAME_RENDERING:
//                    Log.i(TAG, "video frame rendering, ts = " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_AUDIO_FRAME_RENDERING:
//                    Log.i(TAG, "audio frame rendering, ts = " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_GOP_TIME:
//                    Log.i(TAG, "Gop Time: " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_SWITCHING_SW_DECODE:
//                    Log.i(TAG, "Hardware decoding failure, switching software decoding!");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_METADATA:
//                    Log.i(TAG, mVideoView.getMetadata().toString());
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_BITRATE:
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_FPS:
////                    updateStatInfo();
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_CONNECTED:
//                    Log.i(TAG, "Connected !");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
//                    Log.i(TAG, "Rotation changed: " + extra);
//                default:
//                    break;
//            }
//        }
//    };
//
//    private PLOnErrorListener mOnErrorListener = new PLOnErrorListener() {
//        @Override
//        public boolean onError(int errorCode) {
//            callBack(errorCode);
//            switch (errorCode) {
//                case PLOnErrorListener.ERROR_CODE_IO_ERROR:
//                    return false;
//                case PLOnErrorListener.ERROR_CODE_OPEN_FAILED:
//                    break;
//                case PLOnErrorListener.ERROR_CODE_SEEK_FAILED:
//                    break;
//                default:
//                    break;
//            }
//            return true;
//        }
//    };
//
//    private void callBack(int code) {
//        if (mCallBackHandler != null) {
//            mCallBackHandler.sendEmptyMessage(code);
//        }
//    }
//
//    private PLOnCompletionListener mOnCompletionListener = new PLOnCompletionListener() {
//        @Override
//        public void onCompletion() {
//            Log.i(TAG, "Play Completed !");
//        }
//    };
//
//    private PLOnBufferingUpdateListener mOnBufferingUpdateListener = new PLOnBufferingUpdateListener() {
//        @Override
//        public void onBufferingUpdate(int precent) {
//            Log.i(TAG, "onBufferingUpdate: " + precent);
//        }
//    };
//
//    private PLOnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLOnVideoSizeChangedListener() {
//        @Override
//        public void onVideoSizeChanged(int width, int height) {
//            Log.i(TAG, "onVideoSizeChanged: width = " + width + ", height = " + height);
//        }
//    };
//
//    private PLOnVideoFrameListener mOnVideoFrameListener = new PLOnVideoFrameListener() {
//        @Override
//        public void onVideoFrameAvailable(byte[] data, int size, int width, int height, int format, long ts) {
//            if(isCaptured){
//                Log.e("fuckfuck","ddd="+data.length+"   "+size);
//                isCaptured = false;
//                if(mCallBackHandler!=null){
//                    Message message  = mCallBackHandler.obtainMessage();
//                    message.obj = data;
//                    message.arg1 = width;
//                    message.arg2 = height;
//                    message.what = 12341;
//                    mCallBackHandler.sendMessage(message);
//                }
//            }
//            Log.i(TAG, "onVideoFrameAvailable: " + size + ", " + width + " x " + height + ", " + format + ", " + ts);
//        }
//    };
//
//
//    private PLOnAudioFrameListener mOnAudioFrameListener = new PLOnAudioFrameListener() {
//        @Override
//        public void onAudioFrameAvailable(byte[] data, int size, int samplerate, int channels, int datawidth, long ts) {
//            Log.i(TAG, "onAudioFrameAvailable: " + size + ", " + samplerate + ", " + channels + ", " + datawidth + ", " + ts);
//        }
//    };
//
//    private PLOnImageCapturedListener mPLOnImageCapturedListener = new PLOnImageCapturedListener() {
//        @Override
//        public void onImageCaptured(byte[] bytes) {
//            if(mCallBackHandler!=null){
//                Message message  = mCallBackHandler.obtainMessage();
//                message.obj = bytes;
//                message.what = 12341;
//                mCallBackHandler.sendMessage(message);
//            }
//        }
//    };
//
//    @Override
//    public void setVolume(int left, int right) {
//        mVideoView.setVolume(left, right);
//    }
//}