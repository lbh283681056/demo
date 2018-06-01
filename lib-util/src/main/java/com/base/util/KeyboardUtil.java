
package com.base.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;





/**
 * 键盘工具类
 * @author linbinghuang
 *
 */
public class KeyboardUtil {

  
    
    /**
     * @param context
     * @Description: 键盘隐藏
     */
    public static void keyboardHide(Context context) {
        Activity activity = (Activity) context;
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView()
                .getWindowToken(), 0);
    }

    /**
     * @param context
     * @Description: 键盘显示
     */
    public static void keyboardShow(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }
    /**
     * @param mContext
     * @param v
     * @Description: 隐藏键盘
     */
    public static void keyboardHide(Context mContext,View v) {
        InputMethodManager imm = (InputMethodManager) mContext
          .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
       }
}
