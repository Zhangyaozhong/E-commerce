package com.bwie.android.e_commerceproject.persenter.user;

import com.bwie.android.e_commerceproject.bean.user.LoginBean;
import com.bwie.android.e_commerceproject.contract.user.LoginContract;
import com.bwie.android.e_commerceproject.model.user.LoginModel;
import com.bwie.android.lib_core.utils.ValidatorUtil;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginPresenter extends LoginContract.LoginPresenter {
    @Override
    public void login(HashMap<String, String> params) {
        //正则表达式校验
        String phone = params.get("phone");
        if (!ValidatorUtil.isMobileExact(phone)) {
            if (view != null) {
                view.phoneIsRight("您输入的手机号有误，请重新输入");
            }
            return;
        }
        if (model != null) {
            model.loginSuccess(params, new LoginModel.LoginCallback() {
                @Override
                public void success(String result) {
                    Gson gson = new Gson();
                    LoginBean loginBean = gson.fromJson(result, LoginBean.class);

                    if (view != null) {
                        view.loginSuccess(loginBean);
                    }
                }

                @Override
                public void failure(String msg) {
                    if (view != null) {
                        view.failure(msg);
                    }
                }
            });
        }
    }


}
