package com.bwie.android.e_commerceproject.model.user;

import com.bwie.android.e_commerceproject.api.user.LoginApi;
import com.bwie.android.e_commerceproject.contract.user.LoginContract;
import com.bwie.android.lib_core.net.OkhttpCallback;
import com.bwie.android.lib_core.net.OkhttpUtil;

import java.util.HashMap;

public class LoginModel implements LoginContract.ILoginModel {
    @Override
    public void loginSuccess(HashMap<String, String> params, final LoginCallback loginCallback) {
        OkhttpUtil.getInstance().doPost(LoginApi.LOGIN_URL, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (loginCallback != null) {
                    loginCallback.failure(msg);
                }
            }

            @Override
            public void success(String result) {
                if (loginCallback != null) {
                    loginCallback.success(result);
                }
            }
        });
    }


    public interface LoginCallback {
        void success(String result);

        void failure(String msg);
    }

    private LoginCallback loginCallback;

    public void setLoginCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }
}
