package com.bwie.android.e_commerceproject.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.SpacesItemDecoration;
import com.bwie.android.e_commerceproject.adapter.HomeListAdapter;
import com.bwie.android.e_commerceproject.bean.product.BannerBean;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;
import com.bwie.android.e_commerceproject.contract.ProductContract;
import com.bwie.android.e_commerceproject.persenter.ProductPresenter;
import com.bwie.android.lib_core.base.BaseFragment;
import com.bwie.android.lib_core.base.mvp.BasePersenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment implements ProductContract.IProductView {
    private ProductPresenter productPresenter;
    private List<BannerBean.ResultBean> bannerList = new ArrayList<>();
    /**
     * 一些Data的相关操作
     */
    @BindView(R.id.mXRecycleView)
    XRecyclerView mXRecycleView;

    @Override
    protected void setUpData() {
        productPresenter = new ProductPresenter(this);
        productPresenter.getProductList(new HashMap<String, String>());
        productPresenter.getGoodsList(new HashMap<String, String>());
    }

    /**
     * 一些View的相关操作
     */
    @Override
    protected void setUpView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecycleView.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    public void bannerSuccess(final List<BannerBean.ResultBean> resultBeanList) {
        bannerList = resultBeanList;

    }

    @Override
    public void goodsSuccess(GoodsListBean.ResultBean result1) {
        mXRecycleView.setAdapter(new HomeListAdapter(getActivity(), bannerList, result1));
        mXRecycleView.addItemDecoration(new SpacesItemDecoration(10));

    }


    @Override
    public BasePersenter initPeresnter() {
        return null;
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
