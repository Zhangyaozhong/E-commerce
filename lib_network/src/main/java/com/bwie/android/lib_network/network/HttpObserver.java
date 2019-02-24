package com.bwie.android.lib_network.network;

import android.app.Activity;
import android.net.ParseException;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bwie.android.lib_network.R;
import com.bwie.android.lib_network.bean.BaseResponse;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class HttpObserver<T extends BaseResponse> implements Observer<T> {
    private Activity activity;
    //Activity是否在执行onStop是取消订阅
    private boolean isAddInStop = false;

    public HttpObserver(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        if (HttpContants.SUCCESS.equals(t.status)) {
            onSuccess(t);
        }else {
            onFailure(t);
        }
    }
    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public   void onFailure(T response){
        //toast提示消息
        ToastUtils.showShort(response.message);
    };
    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    public abstract void onSuccess(T response);

    @Override
    public void onError(Throwable e) {

        LogUtils.e(e.getMessage());
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {

    }
    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtils.showShort(R.string.connect_error);
                break;

            case CONNECT_TIMEOUT:
                ToastUtils.showShort(R.string.connect_timeout);
                break;

            case BAD_NETWORK:
                ToastUtils.showShort(R.string.bad_network);
                break;

            case PARSE_ERROR:
                ToastUtils.showShort(R.string.parse_error);
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtils.showShort(R.string.unknown_error);
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
