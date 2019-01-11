package com.bwie.android.e_commerceproject.persenter.user;

import com.bwie.android.e_commerceproject.contract.user.RegContract;
import com.bwie.android.e_commerceproject.model.user.RegModel;
import com.bwie.android.lib_core.utils.ValidatorUtil;

import java.util.HashMap;

public class RegPresenter extends RegContract.RegPresenter {
    @Override
    public void register(HashMap<String, String> params) {
        //正则表达式校验
        String phone = params.get("phone");
        if (!ValidatorUtil.isMobileExact(phone)) {
            if (view != null) {
                view.phoneError("您输入的手机号有误，请重新输入");
            }
            return;
        }
//        回调m层的数据
        if (model != null) {
            model.register(params, new RegModel.IRegCallback() {
                @Override
                public void failure(String msg) {
                    if (view != null) {
                        view.failure(msg);
                    }
                }

                @Override
                public void success(String result) {
                    if (view != null) {
                        view.regSuccess(result);
                    }
                }
            });
        }
    }

    @Override
    public RegContract.IRegModel getModel() {
        return new RegModel();
    }
}
