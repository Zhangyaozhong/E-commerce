package com.bwie.android.e_commerceproject.model.product;

import com.bwie.android.e_commerceproject.api.product.AllApi;
import com.bwie.android.e_commerceproject.api.product.ProductorApiService;
import com.bwie.android.e_commerceproject.bean.product.LabelListBean;
import com.bwie.android.e_commerceproject.contract.product.ProductContract;
import com.bwie.android.lib_core.net.OkhttpCallback;
import com.bwie.android.lib_core.net.OkhttpUtil;
import com.bwie.android.lib_network.bean.BaseResponse;
import com.bwie.android.lib_network.network.ResponseTransformer;
import com.bwie.android.lib_network.network.RetrofitUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Consumer;

public class ProductModel implements ProductContract.IProductModel {

    public static final String ERROR_HINT = "网络异常，请稍后再试";

    @Override
    public void bannerData(HashMap<String, String> params, final IProductCallback iProductCallback) {
        OkhttpUtil.getInstance().doGet(AllApi.BANNER_URL, params, new OkhttpCallback() {
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

    /**
     * 首页
     *
     * @param params
     * @param iProductCallback
     */
    @Override
    public void goodsData(HashMap<String, String> params, final IProductCallback iProductCallback) {
        OkhttpUtil.getInstance().doGet(AllApi.HOMEGOODS_URL, params, new OkhttpCallback() {
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
    public void firstCategory(HashMap<String, String> params, final IProductCallback iProductCallback) {
        OkhttpUtil.getInstance().doGet(AllApi.FIRSTCATEGORY_URL, params, new OkhttpCallback() {
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
    public void secondCategory(HashMap<String, String> params, final IProductCallback iProductCallback) {
        OkhttpUtil.getInstance().doGet(AllApi.SECONDCATEGORY_URL, params, new OkhttpCallback() {
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
    public void getLabelList(HashMap<String, String> params, final ModelCallback modelCallback) {
        RetrofitUtils.getInstance().create(ProductorApiService.class)
                .label(params)
                .compose(ResponseTransformer.scheduler())
                .subscribe(new Consumer<BaseResponse<List<LabelListBean>>>() {
                    @Override
                    public void accept(BaseResponse<List<LabelListBean>> listResponse) throws Exception {
                        if (modelCallback != null) {
                            modelCallback.success(listResponse.result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (modelCallback != null) {
                            modelCallback.error(ERROR_HINT);
                        }
                    }
                });
    }

    public interface IProductCallback {
        void failure(String msg);

        void successful(String result);
    }

    public interface ModelCallback<T> {
        void error(String msg);

        void success(List<T> list);


    }

    private ModelCallback modelCallback;

    public void setModelCallback(ModelCallback modelCallback) {
        this.modelCallback = modelCallback;
    }

    private IProductCallback iProductCallback;

    public void setiProductCallback(IProductCallback iProductCallback) {
        this.iProductCallback = iProductCallback;
    }
}
