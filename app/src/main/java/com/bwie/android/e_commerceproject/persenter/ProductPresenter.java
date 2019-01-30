package com.bwie.android.e_commerceproject.persenter;

import com.bwie.android.e_commerceproject.bean.product.BannerBean;
import com.bwie.android.e_commerceproject.bean.product.FirstCategoryBean;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;
import com.bwie.android.e_commerceproject.bean.product.SecondCategoryBean;
import com.bwie.android.e_commerceproject.contract.product.ProductContract;
import com.bwie.android.e_commerceproject.model.product.ProductModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class ProductPresenter extends ProductContract.ProductPresenter {
    private ProductContract.IProductView iProductView;
    private ProductModel productModel;

    public ProductPresenter(ProductContract.IProductView iProductView) {
        this.iProductView = iProductView;
        productModel = new ProductModel();
    }

    @Override
    public void getProductList(HashMap<String, String> params) {
        if (productModel != null) {
            productModel.bannerData(params, new ProductModel.IProductCallback() {
                @Override
                public void failure(String msg) {
                    if (iProductView != null) {
                        iProductView.failure(msg);
                    }
                }

                @Override
                public void successful(String result) {
                    Gson gson = new Gson();
                    BannerBean bannerBean = gson.fromJson(result, BannerBean.class);
                    List<BannerBean.ResultBean> list = bannerBean.result;
                    if (iProductView != null) {
                        iProductView.bannerSuccess(list);
                    }
                }
            });
        }
    }

    @Override
    public void getGoodsList(HashMap<String, String> params) {
        if (productModel != null) {
            productModel.goodsData(params, new ProductModel.IProductCallback() {
                @Override
                public void failure(String msg) {
                    if (iProductView != null) {
                        iProductView.failure(msg);
                    }
                }

                @Override
                public void successful(String result) {
                    Gson gson = new Gson();

                    GoodsListBean goodsListBean = gson.fromJson(result, GoodsListBean.class);
                    GoodsListBean.ResultBean result1 = goodsListBean.getResult();

                    if (iProductView != null) {
                        iProductView.goodsSuccess(result1);
                    }
                }
            });
        }
    }

    /**
     * 一级类目
     * @param params
     */
    @Override
    public void getFirstCategory(HashMap<String, String> params) {
        if (productModel != null) {
            productModel.firstCategory(params, new ProductModel.IProductCallback() {
                @Override
                public void failure(String msg) {
                    if (iProductView != null) {
                        iProductView.failure(msg);
                    }
                }

                @Override
                public void successful(String result) {
                    Gson gson = new Gson();

                    FirstCategoryBean firstCategoryBean = gson.fromJson(result, FirstCategoryBean.class);
                    List<FirstCategoryBean.ResultBean> firstCategoryBeanResult = firstCategoryBean.getResult();
                    if (iProductView != null) {
                        iProductView.firstCategorySuccess(firstCategoryBeanResult);
                    }
                }
            });
        }
    }

    @Override
    public void getSecondCategory(HashMap<String, String> params) {
        if (productModel != null) {
            productModel.secondCategory(params, new ProductModel.IProductCallback() {
                @Override
                public void failure(String msg) {
                    if (iProductView != null) {
                        iProductView.failure(msg);
                    }
                }

                @Override
                public void successful(String result) {
                    Gson gson = new Gson();
                    SecondCategoryBean secondCategoryBean = gson.fromJson(result, SecondCategoryBean.class);
                    List<SecondCategoryBean.ResultBean> secondCategoryBeanResult = secondCategoryBean.getResult();
                    if (iProductView != null) {
                        iProductView.secondCategorySuccess(secondCategoryBeanResult);
                    }
                }
            });
        }
    }
}
