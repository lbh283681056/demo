
package com.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;


/**
 * SharePre工具类
 * @ClassName: SharepreferenceUtil
 * @Description: SharePre工具类
 * @author Comsys-linbinghuang
 * @date 2014-10-14 上午10:58:31
 */
public class SharepreferenceUtil {
    
    /**
     * 用SharePreferences保存集合
     * @param context
     * @param fileName
     * @param nodeName
     * @param list
     */
    public static <T> void saveArray(Context context, String fileName, String nodeName, List<T> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            String listBase64 = new String(Base64.encode(byteArrayOutputStream.toByteArray(),
                    Base64.DEFAULT));
            objectOutputStream.close();
            putSharePreStr(context, fileName, nodeName, listBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    /**
     * json串转成集合
     * @param listString
     * @return
     */
    public static <T> List<T> jsonToList(String listString) {
        try {
            byte[] mBytes = Base64.decode(listString.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            List<T> list = (List<T>) objectInputStream.readObject();
            objectInputStream.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取SharedPreferences对象
     * @param context
     * @param spName
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context, String spName) {
        SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName,
                Activity.MODE_PRIVATE);
        return sp;
    }

   
    /**
     * 保存整型
     * @param context
     * @param spName
     * @param nodeName
     * @param value
     */
    public static void putSharePreInt(Context context, String spName, String nodeName,int value) {
        Editor editor = getSharedPreferences(context, spName).edit();
        editor.putInt(nodeName, value).commit();
    }

    /**
      * 
      * @Description: 
      * @param @param context
      * @param @param spName
      * @param @param nodeName
      * @param @param value   
      * @return void    
      * @throws
      */
    /**
     * 保存布尔值
     * @param context
     * @param spName
     * @param nodeName
     * @param value
     */
    public static void putSharePreBoolean(Context context, String spName, String nodeName, boolean value) {
        Editor editor = getSharedPreferences(context, spName).edit();
        editor.putBoolean(nodeName, value).commit();
       
    }
   
    /**
     * 保存字符串
     * @param context
     * @param spName
     * @param nodeName
     * @param value
     */
    public static void putSharePreStr(Context context, String spName, String nodeName, String value) {
        Editor editor = getSharedPreferences(context, spName).edit();
        editor.putString(nodeName, value).commit();
    }

   
    /**
     * 获取字符串
     * @param context
     * @param spName
     * @param nodeName
     * @param value
     * @return
     */
    public static String getSharePreString(Context context, String spName, String nodeName,
            String value) {
        return getSharedPreferences(context, spName).getString(nodeName, value);
    }

    
    /**
     * 获取布尔值
     * @param context
     * @param spName
     * @param nodeName
     * @param defult
     * @return
     */
    public static boolean getSharePreBoolean(Context context, String spName, String nodeName,
            boolean defult) {
        return getSharedPreferences(context, spName).getBoolean(nodeName, defult);
    }

   
   
    /**
     * 获取整型
     * @param context
     * @param spName
     * @param nodeName
     * @param defult
     * @return
     */
    public static int getSharePreInt(Context context, String spName, String nodeName, int defult) {
        return getSharedPreferences(context, spName).getInt(nodeName, defult);
    }

}
