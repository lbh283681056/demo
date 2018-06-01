package com.base.live.livehelp.qny;


import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.pili.pldroid.player.PLOnAudioFrameListener;
import com.pili.pldroid.player.PLOnVideoFrameListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;

/**
 * Created by linbinghuang on 2018/3/5.
 * 七牛云播放器业务
 */

public class QnyMediaPlayModel extends QnyBasePlayModel implements ILivePlayQnyModel {
    private SurfaceView mSurfaceView;
    private int mSurfaceWidth = 0;
    private int mSurfaceHeight = 0;
    private boolean isCaptured;
    @Override
    public void onCreate(View playView) {
        mSurfaceView = (SurfaceView) playView;
        mSurfaceView.getHolder().addCallback(mCallback);

    }
    @Override
    public void capturePicture(String path) {
        isCaptured = true;
    }
    public void releaseWithoutStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.setDisplay(null);
        }
    }
    @Override
    protected void prepare() {
        try {
            if (mMediaPlayer != null) {
                return;
            }
            super.prepare();
            mMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
            mMediaPlayer.setOnAudioFrameListener(mOnAudioFrameListener);
            mMediaPlayer.setOnVideoFrameListener(mOnVideoFrameListener);
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
            releaseWithoutStop();
        }
    };
    private PLOnVideoFrameListener mOnVideoFrameListener = new PLOnVideoFrameListener() {
        @Override
        public void onVideoFrameAvailable(byte[] data, int size, int width, int height, int format, long ts) {
            if (mQnyCallBack == null) {
                return;
            }
            if (isCaptured) {
                isCaptured = false;
                mQnyCallBack.capturepic(data,  size,  width,  height,  format,  ts);
            }
            mQnyCallBack.onVideoFrameAvailable(data,  size,  width,  height,  format,  ts);
            Log.i(TAG, "onVideoFrameAvailable: " + size + ", " + width + ", " + height + ", " + format + ", " + ts);

        }

    };


    private PLOnAudioFrameListener mOnAudioFrameListener = new PLOnAudioFrameListener() {
        @Override
        public void onAudioFrameAvailable(byte[] data, int size, int samplerate, int channels, int datawidth, long ts) {
            if (mQnyCallBack == null) {
                return;
            }
            mQnyCallBack.onAudioFrameAvailable(data,  size,  samplerate,  channels,  datawidth,  ts);
            Log.i(TAG, "onAudioFrameAvailable: " + size + ", " + samplerate + ", " + channels + ", " + datawidth + ", " + ts);
        }
    };
    private PLOnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLOnVideoSizeChangedListener() {
        public void onVideoSizeChanged(int width, int height) {
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

    @Override
    public void bindData(String path) {

    }


}