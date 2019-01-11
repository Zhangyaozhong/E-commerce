package com.bwie.android.e_commerceproject.model;

import com.bwie.android.e_commerceproject.api.product.ProductApi;
import com.bwie.android.e_commerceproject.contract.ProductContract;
import com.bwie.android.lib_core.net.OkhttpCallback;
import com.bwie.android.lib_core.net.OkhttpUtil;

import java.util.HashMap;

public class ProductModel implements ProductContract.IProductModel {


    @Override
    public void bannerData(HashMap<String, String> params, final IProductCallback iProductCallback) {
        OkhttpUtil.getInstance().doGet(ProductApi.BANNER_URL, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (iProductCallback != null) {
                    iProductCallback.failure(msg);
                }
            }

            @Override
            public void success(String result) {
                if (iProductCallback != null) {
                    iProductCallback.successful(result);
                }
            }
        });
    }

    @Override
    public void goodsData(HashMap<String, String> params, final IProductCallback iProductCallback) {
        OkhttpUtil.getInstance().doGet(ProductApi.HOMEGOODS_URL, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (iProductCallback != null) {
                    iProductCallback.failure(msg);
                }
            }

            @Override
            public void success(String result) {
                if (iProductCallback != null) {
                    iProductCallback.successful(result);
                }
            }
        });
    }

    public interface IProductCallback {
        void failure(String msg);

        void successful(String result);
    }

    private IProductCallback iProductCallback;

    public void setiProductCallback(IProductCallback iProductCallback) {
        this.iProductCallback = iProductCallback;
    }
}
