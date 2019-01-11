package com.bwie.android.lib_core.interceptor;

import com.bwie.android.lib_core.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private Request request;

    @Override
    public Response intercept(Chain chain) throws IOException {
//        获得原始的请求对象
        request = chain.request();
        Request.Builder builder = request.newBuilder();
//        添加新的参数
        String userid = SpUtils.getInstance().getSp("userid");
        String sessionid = SpUtils.getInstance().getSp("sessionid");
        builder.addHeader("userid", userid);
        builder.addHeader("sessionid", sessionid);
//        获得新的request对象
        request = builder.build();
//        根据新的请求对象获得新的相应对象
        Response response = chain.proceed(request);
        return response;
    }
}
