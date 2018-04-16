package com.larry.present.common.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by Larry-sea on 2016/10/26.
 * <p>
 * sharedpreference util
 */

public class SharedPreferenceUtil {

    private static SharedPreferenceUtil Instance;  //单例实例

    private SharedPreferenceUtil() {

    }

    /**
     * 获取实例
     */
    public static SharedPreferenceUtil getInstance() {
        if (Instance == null) {
            synchronized (SharedPreferenceUtil.class) {
                if (Instance == null) {
                    Instance = new SharedPreferenceUtil();
                }
            }

        }
        return Instance;

    }

    /**
     * 存放多个键值对
     *
     * @param context  设备上下文
     * @param fileName sharedpreference文件名
     * @param arrayMap 存放键值对的map
     */
    @SuppressLint("NewApi")
    public void putMoreThanOneKeyPair(Context context, String fileName, ArrayMap<String, String> arrayMap) {
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).edit();
        for (Map.Entry<String, String> entry : arrayMap.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue()).commit();
        }
//        for (int position = 0; position < arrayMap.size(); position++) {
//            editor.putString((String) arrayMap.keyAt(position), (String) arrayMap.valueAt(position));
//        }
//        editor.commit();
    }


    /**
     * 获取值
     *
     * @param fileName
     * @param key      键
     */
    public String getStringSharedValue(Context context, String fileName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }


    /**
     * 获取boolean类型的值
     *
     * @param context
     * @param fileName 文件名称
     * @param key      存储时的key
     * @return
     */
    public boolean getBooleanSharedValue(Context context, String fileName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }


    /**
     * 存放单一的键值
     *
     * @param fileName  文件名
     * @param value,key 以覆盖的方式存值
     */
    public void putStringValue(Context context, String fileName, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * putShareValue的原始函数
     */
    public void putBooleanValue(Context context, String fileName, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


}
