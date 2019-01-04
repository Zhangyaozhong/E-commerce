package com.bwie.android.e_commerceproject.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkhttpUtil {
    private OkhttpUtil okhttpUtil;

    private OkhttpUtil() {
        new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

    }
}
