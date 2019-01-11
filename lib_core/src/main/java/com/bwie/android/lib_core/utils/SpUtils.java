package com.bwie.android.lib_core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bwie.android.lib_core.application.BaseApp;

public class SpUtils {
    private final String SP_FLAG = "weidu";
    private static SpUtils spUtils;

    private SpUtils() {

    }

    /**
     * 双重检验锁
     */
    public static SpUtils getInstance() {
        if (spUtils == null) {
            synchronized (SpUtils.class) {
                if (spUtils == null) {
                    spUtils = new SpUtils();
                }
            }
        }
        return spUtils;
    }

    /**
     * 获得sp对象
     *
     * @return
     */
    private SharedPreferences obtainSp() {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(SP_FLAG, Context.MODE_PRIVATE);
        if (sp != null) {
            return sp;
        }
        return null;
    }

    /**
     * 存数据
     *
     * @param key
     * @param value
     */
    public void putSp(String key, String value) {
        obtainSp().edit().putString(key, value).commit();

    }

    public void putSp(String key, boolean b) {
        obtainSp().edit().putBoolean(key, b).commit();

    }

    /**
     * 取数据
     *
     * @param key
     */
    public String getSp(String key) {
        return obtainSp().getString(key, "");
    }

    public boolean getBooleanSp(String key) {
        return obtainSp().getBoolean(key, false);
    }
}
