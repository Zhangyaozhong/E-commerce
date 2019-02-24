package com.bwie.android.e_commerceproject.contract.product;

import com.bwie.android.e_commerceproject.bean.product.BannerBean;
import com.bwie.android.e_commerceproject.bean.product.FirstCategoryBean;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;
import com.bwie.android.e_commerceproject.bean.product.LabelListBean;
import com.bwie.android.e_commerceproject.bean.product.SearchBean;
import com.bwie.android.e_commerceproject.bean.product.SecondCategoryBean;
import com.bwie.android.e_commerceproject.model.product.ProductModel;
import com.bwie.android.lib_core.base.mvp.IBaseView;
import com.bwie.android.lib_network.bean.BaseResponse;

import java.util.HashMap;
import java.util.List;

public interface ProductContract {
    abstract class ProductPresenter {

        public abstract void getProductList(HashMap<String, String> params);

        public abstract void getGoodsList(HashMap<String, String> params);

        public abstract void getFirstCategory(HashMap<String, String> params);

        public abstract void getSecondCategory(HashMap<String, String> params);
        //标签商品列表
        public abstract void getLabelList(HashMap<String, String> params);

    }

    interface IProductModel {
        void bannerData(HashMap<String, String> params, ProductModel.IProductCallback iProductCallback);

        void goodsData(HashMap<String, String> params, ProductModel.IProductCallback iProductCallback);

        //    一级类目
        void firstCategory(HashMap<String, String> params, ProductModel.IProductCallback iProductCallback);

        //    二级类目
        void secondCategory(HashMap<String, String> params, ProductModel.IProductCallback iProductCallback);

        void getLabelList(HashMap<String, String> params, ProductModel.ModelCallback modelCallback);
    }

    interface IProductView  {
        //轮播图
        void bannerSuccess(List<BannerBean.ResultBean> resultBeanList);

        //首页
        void goodsSuccess(GoodsListBean.ResultBean result1);

        //    一级类目
        void firstCategorySuccess(List<FirstCategoryBean.ResultBean> resultBean);

        //    二级类目
        void secondCategorySuccess(List<SecondCategoryBean.ResultBean> resultBean);


        //            标签商品列表
        void labelSuccess(List<LabelListBean> response);

    }
}
