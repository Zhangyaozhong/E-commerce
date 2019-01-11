package com.bwie.android.lib_core.net;

import android.os.Handler;

import com.bwie.android.lib_core.interceptor.HeaderInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtil {
    //    私有属性
    private static OkhttpUtil okhttpUtil;
    private final OkHttpClient okHttpClient;
    Handler handler = new Handler();


    private OkhttpUtil() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
//                添加日志拦截器
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(loggingInterceptor)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 双重检验锁,单例模式进行实例化
     *
     * @return
     */
    public static OkhttpUtil getInstance() {
        if (okhttpUtil == null) {
            synchronized (OkhttpUtil.class) {
                if (okhttpUtil == null) {
                    okhttpUtil = new OkhttpUtil();
                }
            }
        }
        return okhttpUtil;
    }

    /**
     * get请求方式
     */
    public void doGet(String url, HashMap<String, String> parmas, final OkhttpCallback requestCallback) {

        StringBuilder p = new StringBuilder();
        if (parmas != null && parmas.size() > 0) {
            for (Map.Entry<String, String> map : parmas.entrySet()) {

                p.append(map.getKey()).append("=").append(map.getValue()).append("&");
            }

            System.out.println("ppppppp====" + p.toString());
        }
        Request request = new Request.Builder().url(url + "?" + p.toString())
                .get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.failure("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();
                if (requestCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.success(result);

                        }
                    });
                }
            }
        });


    }


    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public void doPost(String url, HashMap<String, String> params, final OkhttpCallback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.failure("网络异常,请稍后再试");
                        }
                    });

                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (callback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.success(result);
                        }
                    });
                }
            }
        });
    }
}
