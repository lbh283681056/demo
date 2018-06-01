package com.base.login;

import android.content.Context;
import android.content.Intent;

/**
 * Created by linbinghuang on 2018/5/13.
 */

public interface IBaseLoginModel {

    /**
     * 初始化
     * @param context  上下文对象
     */
    void init(Context context);

    /**
     * 登入
     */
    void login();

    /**
     * 登出
     */
    void loginOut();

    /**
     * activity回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);

    /**
     * 设置登陆回调
     * @param callBack
     */
    void setCallBack(ILoginCallback callBack);
}
