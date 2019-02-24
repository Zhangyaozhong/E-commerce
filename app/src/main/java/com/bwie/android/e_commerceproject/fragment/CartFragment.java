package com.bwie.android.e_commerceproject.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.adapter.SearchCartAdapter;
import com.bwie.android.e_commerceproject.bean.SearchCartBean;
import com.bwie.android.e_commerceproject.contract.CartContract;
import com.bwie.android.e_commerceproject.persenter.SearchCartPresenter;
import com.bwie.android.lib_core.base.BaseFragment;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CartFragment extends BaseFragment implements CartContract.ICartView {

    @BindView(R.id.rv_cart)
    RecyclerView rvCart;
    @BindView(R.id.settlement)
    TextView settlement;
    @BindView(R.id.countchange)
    CheckBox countchange;
    @BindView(R.id.qx)
    TextView qx;
    @BindView(R.id.heji)
    TextView heji;
    @BindView(R.id.countprice)
    TextView countprice;

    private SearchCartPresenter searchCartPresenter;

    /**
     * 一些Data的相关操作
     */
    @Override
    protected void setUpData() {
        searchCartPresenter = new SearchCartPresenter(this);
        searchCartPresenter.cart(new HashMap<String, String>());
    }

    /**
     * 一些View的相关操作
     */
    @Override
    protected void setUpView() {
        rvCart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_cart;
    }


    @Override
    public void CartSuccess(List<SearchCartBean> searchCartBeans) {
        SearchCartAdapter searchCartAdapter = new SearchCartAdapter(searchCartBeans, getActivity());
        rvCart.setAdapter(searchCartAdapter);
    }

}
