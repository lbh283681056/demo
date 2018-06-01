package com.base.live.livehelp.qny;

import android.content.Context;
import android.media.AudioManager;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;


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
 * Created by jianglongzhuan on 2018/3/19.
 * 只播放音乐的播放器
 */

public class QnyAudioPlayModel extends BaseLiveHelp implements ILivePlayQnyModel {
    private static final String TAG = QnyAudioPlayModel.class.getSimpleName();
    private PLMediaPlayer mMediaPlayer;
    private String mAudioPath;
    private AVOptions mAVOptions;
    private boolean mIsStopped = false;
    private TelephonyManager mTelephonyManager;
    private PhoneStateListener mPhoneStateListener;
    private boolean loop = false;

    private Context mContext;
    public static final int PLAY_COMPELETE = 5003;
    public static final int PLAY_START = 5004;
    public static final int PLAY_ING = 5006;//播放中
    public static final int PLAY_ERROR = 5005;// 出错

    public QnyAudioPlayModel(Context context) {
        this.mContext = context;
    }
    @Override
    public long getCurrentPosition() {
        return mMediaPlayer == null ? 0 : mMediaPlayer.getCurrentPosition();
    }

    @Override
    public void start(String url) {
        if (mIsStopped) {
            prepare();
        } else {
            try {
                mMediaPlayer.setDataSource(url);
                mMediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isPlaying() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        } else {
            return false;
        }

    }

    @Override
    public void setLoop(boolean loop) {
        this.loop = loop;
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
    public void reStar(String url) {

    }

    @Override
    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }


    @Override
    public void seekTo(long seek) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(seek);
        }
    }

    @Override
    public void capturePicture(String path) {

    }

    @Override
    public void setAudioEnable(boolean enable) {

    }

    @Override
    public void setVideoEnable(boolean enable) {

    }


    @Override
    public void setQnyCallBack(IQnyCallBack mQnyCallBack) {

    }

    @Override
    public void bindData(String path) {
        mAudioPath = path;
        mAVOptions = new AVOptions();
        // the unit of timeout is ms
        mAVOptions.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        // 1 -> hw codec enable, 0 -> disable [recommended]
        int codec = AVOptions.MEDIA_CODEC_SW_DECODE;
        mAVOptions.setInteger(AVOptions.KEY_MEDIACODEC, codec);
        mAVOptions.setInteger(AVOptions.KEY_SEEK_MODE, 1);

        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        prepare();
        startTelephonyListener();
    }

    @Override
    public void onCreate(View playView) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTelephonyListener();
        release();
        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        audioManager.abandonAudioFocus(null);
    }

    /**
     * 重新开始
     */
    @Override
    public void onResume() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }

    /**
     * 暂停
     */
    @Override
    public void onPause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }

    /**
     * 释放资源
     */
    private void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    /**
     * 准备
     */
    private void prepare() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new PLMediaPlayer(mContext.getApplicationContext(), mAVOptions);
            mMediaPlayer.setLooping(loop);
            mMediaPlayer.setOnPreparedListener(mOnPreparedListener);
            mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            mMediaPlayer.setOnErrorListener(mOnErrorListener);
            mMediaPlayer.setOnInfoListener(mOnInfoListener);
            mMediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
            mMediaPlayer.setWakeMode(mContext.getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        }
        try {
            mMediaPlayer.setDataSource(mAudioPath);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PLOnPreparedListener mOnPreparedListener = new PLOnPreparedListener() {
        @Override
        public void onPrepared(int preparedTime) {
            Log.i(TAG, "On Prepared !");
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
                if (mCallBackHandler != null) {
                    mCallBackHandler.sendEmptyMessage(PLAY_START);
                }
                mIsStopped = false;
            }
        }
    };

    private PLOnInfoListener mOnInfoListener = new PLOnInfoListener() {
        @Override
        public void onInfo(int what, int extra) {
            Log.i(TAG, "OnInfo, what = " + what + ", extra = " + extra);
            switch (what) {
                case PLOnInfoListener.MEDIA_INFO_BUFFERING_START:

                    break;
                case PLOnInfoListener.MEDIA_INFO_BUFFERING_END:
                case PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START:
                    if (mCallBackHandler != null) {
                        mCallBackHandler.sendEmptyMessage(PLAY_START);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private PLOnBufferingUpdateListener mOnBufferingUpdateListener = new PLOnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(int percent) {
            Log.d(TAG, "onBufferingUpdate: " + percent + "%");
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
            Log.d(TAG, "Play Completed !");
            if (mCallBackHandler != null) {
                mCallBackHandler.sendEmptyMessage(PLAY_COMPELETE);
            }
        }
    };

    private PLOnErrorListener mOnErrorListener = new PLOnErrorListener() {
        @Override
        public boolean onError(int errorCode) {
            Log.e(TAG, "Error happened, errorCode = " + errorCode);
            if (mCallBackHandler != null) {
                mCallBackHandler.sendEmptyMessage(PLAY_ERROR);
            }
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

    // Listen to the telephone
    private void startTelephonyListener() {
        mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager == null) {
            Log.e(TAG, "Failed to initialize TelephonyManager!!!");
            return;
        }

        mPhoneStateListener = new PhoneStateListener() {

            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.d(TAG, "PhoneStateListener: CALL_STATE_IDLE");
                        if (mMediaPlayer != null) {
                            mMediaPlayer.start();
                        }
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Log.d(TAG, "PhoneStateListener: CALL_STATE_OFFHOOK");
                        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                            mMediaPlayer.pause();
                        }
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Log.d(TAG, "PhoneStateListener: CALL_STATE_RINGING: " + incomingNumber);
                        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                            mMediaPlayer.pause();
                        }
                        break;
                }
            }
        };

        try {
            mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取时长
     *
     * @return
     */
    public long getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        }
        return 0;

    }

    private void stopTelephonyListener() {
        if (mTelephonyManager != null && mPhoneStateListener != null) {
            mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
            mTelephonyManager = null;
            mPhoneStateListener = null;
        }
    }
}
