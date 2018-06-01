package com.base.login;

/**
 * Created by linbinghuang on 2018/5/16.
 */

public interface ILoginCallback {

    /**
     * 登陆成功
     * @param resultInfo
     */
    void loginSuccess(LoginResultInfo resultInfo);

    /**
     * 登出
     */
    void loginOut();

}
