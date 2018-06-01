package com.base.login.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.base.login.IBaseLoginModel;
import com.base.login.ILoginCallback;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


import org.json.JSONObject;


/**
 * qq登陆业务
 * Created by linbinghuang on 2018/5/13.
 */

public class QQLoginModel implements IBaseLoginModel {

    /**
     * qq登陆操作类
     */
    private Tencent mTencent;
    /**
     * 上下文
     */
    private Activity mContext;
    /**
     * 授权实体
     */
    private UserInfo mInfo;
    /**
     * 回调
     */
    private ILoginCallback mCallBack;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    if(mCallBack!=null){
                        mCallBack.loginSuccess(null);
                    }
                }
            }else if(msg.what == 1){
            }
        }

    };
    @Override
    public void init(Context context) {
        mContext = (Activity) context;
    }

    @Override
    public void login() {
        mTencent = Tencent.createInstance("key", mContext.getApplicationContext());
        if (!mTencent.isSessionValid())
        {
            mTencent.login(mContext, "all", loginListener);
        }
    }

    @Override
    public void loginOut() {
        mTencent.logout(mContext);
        if(mCallBack!=null){
            mCallBack.loginOut();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);
    }

    @Override
    public void setCallBack(ILoginCallback callBack) {
        this.mCallBack = callBack;
    }

    /**
     * 用token去请求用户信息
     */
    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                    new Thread(){

                        @Override
                        public void run() {
                            JSONObject json = (JSONObject)response;
                            if(json.has("figureurl")){
                                Message msg = new Message();
                                msg.what = 1;
                                mHandler.sendMessage(msg);
                            }
                        }

                    }.start();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(mContext, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        }
    }

    /**
     * 解析授权信息
     * @param jsonObject
     */
    private  void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }
    /**
     * 授权回调
     */
  private  IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
        }
    };

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                ToastUtil.showToast(mContext,"登陆失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                ToastUtil.showToast(mContext,"登陆失败");
                return;
            }
            ToastUtil.showToast(mContext,"登录成功");
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {


        }

        @Override
        public void onCancel() {

        }
    }
}
