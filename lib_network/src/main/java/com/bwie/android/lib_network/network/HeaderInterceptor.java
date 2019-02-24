package com.bwie.android.lib_network.network;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
//        获得原始的请求对象
        Request build = chain.request().newBuilder()
                .addHeader("userId", SPUtils.getInstance().getString("userId"))
                .addHeader("sessionId", SPUtils.getInstance().getString("sessionId"))
                .build();
        Response proceed = chain.proceed(build);
        return proceed;

    }
}
