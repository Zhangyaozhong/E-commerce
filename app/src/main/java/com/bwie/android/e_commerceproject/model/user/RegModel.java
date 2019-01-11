package com.bwie.android.e_commerceproject.model.user;

import com.bwie.android.e_commerceproject.api.user.RegisterApi;
import com.bwie.android.e_commerceproject.contract.user.RegContract;
import com.bwie.android.lib_core.net.OkhttpCallback;
import com.bwie.android.lib_core.net.OkhttpUtil;

import java.util.HashMap;

public class RegModel implements RegContract.IRegModel {

    @Override
    public void register(HashMap<String, String> params, final IRegCallback iRegCallback) {
        OkhttpUtil.getInstance().doPost(RegisterApi.REGISTER_URL, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (iRegCallback != null) {
                    iRegCallback.failure(msg);
                }
            }

            @Override
            public void success(String result) {
                if (iRegCallback != null) {
                    iRegCallback.success(result);
                }
            }
        });
    }

    public interface IRegCallback {
        void failure(String msg);

        void success(String result);
    }

    private IRegCallback iRegCallback;

    public void setiRegCallback(IRegCallback iRegCallback) {
        this.iRegCallback = iRegCallback;
    }
}
