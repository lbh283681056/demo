package com.base.live.livehelp.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by linbinghuang on 2018/3/6.
 */

public abstract class BaseLiveHelp implements IBaseLive {
    //上下文
    protected Activity mActivity;
    //入参
    protected Bundle mBundle;
    //回调
    protected Handler mCallBackHandler;
    @Override
    public void bindData(Activity activity, Bundle bundle) {
        mActivity = activity;
        mBundle = bundle;
    }
    @Override
    public void setCallBack(Handler callBackHandler) {
        mCallBackHandler = callBackHandler;
    }
    @Override
    public void onDestroy() {
        if (mCallBackHandler != null) {
            mCallBackHandler.removeCallbacksAndMessages(null);
            mCallBackHandler = null;
        }
    }
}
