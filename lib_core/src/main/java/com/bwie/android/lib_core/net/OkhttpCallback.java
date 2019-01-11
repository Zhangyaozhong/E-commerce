package com.bwie.android.lib_core.net;

public interface OkhttpCallback {
    void failure(String msg);
    void success(String result);
}
