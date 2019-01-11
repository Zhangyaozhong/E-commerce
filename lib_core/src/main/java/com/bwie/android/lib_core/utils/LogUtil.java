package com.bwie.android.lib_core.utils;

import android.util.Log;

import com.bwie.android.lib_core.common.Constants;

/**
 * 日志工具类
 */
public class LogUtil {
    //得到类的简写名称
    public static String TAG = LogUtil.class.getSimpleName();

    public static void d(String msg) {
        //判断是否是正式上线环境
        if (!Constants.IS_RELEASE) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (!Constants.IS_RELEASE) {
            Log.i(TAG, msg);
        }
    }

}
