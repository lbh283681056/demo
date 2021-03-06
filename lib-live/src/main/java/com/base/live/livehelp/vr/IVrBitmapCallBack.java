package com.base.live.livehelp.vr;

import android.app.Activity;
import android.net.Uri;

import com.asha.vrlib.texture.MD360BitmapTexture;

/**
 * Created by linbinghuang on 2018/4/22.
 */

public interface IVrBitmapCallBack {
    /**
     * 加载图片
     * @param activity
     * @param uri
     * @param callback
     */
    void loadImage(Activity activity, Uri uri, MD360BitmapTexture.Callback callback);
}
