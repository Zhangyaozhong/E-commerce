package com.bwie.android.e_commerceproject.contract;

import com.bwie.android.e_commerceproject.bean.product.BannerBean;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;
import com.bwie.android.e_commerceproject.model.ProductModel;
import com.bwie.android.lib_core.base.mvp.IBaseView;

import java.util.HashMap;
import java.util.List;

public interface ProductContract {
    abstract class ProductPresenter {

        public abstract void getProductList(HashMap<String, String> params);
        public abstract void getGoodsList(HashMap<String, String> params);

    }

    interface IProductModel  {
        void bannerData(HashMap<String, String> params, ProductModel.IProductCallback iProductCallback);
        void goodsData(HashMap<String, String> params, ProductModel.IProductCallback iProductCallback);
    }

    interface IProductView extends IBaseView {
        void bannerSuccess(List<BannerBean.ResultBean> resultBeanList);

        void goodsSuccess(GoodsListBean.ResultBean result1);
    }
}
