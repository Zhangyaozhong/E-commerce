package com.bwie.android.e_commerceproject.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.activity.AddressActivity;
import com.bwie.android.e_commerceproject.activity.CircleActivity;
import com.bwie.android.e_commerceproject.activity.FootActivity;
import com.bwie.android.e_commerceproject.activity.InformationActivity;
import com.bwie.android.e_commerceproject.activity.WalletActivity;
import com.bwie.android.lib_core.base.BaseFragment;

import butterknife.BindView;

public class MyFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_person_information)
    TextView mInformation;
    @BindView(R.id.tv_person_circle)
    TextView mCircle;
    @BindView(R.id.tv_person_foot)
    TextView mFoot;
    @BindView(R.id.tv_person_wallet)
    TextView mWallet;
    @BindView(R.id.tv_person_address)
    TextView mAddress;
    @BindView(R.id.tv_person_name)
    TextView mName;

    /**
     * 一些Data的相关操作
     */
    @Override
    protected void setUpData() {

    }

    /**
     * 一些View的相关操作
     */
    @Override
    protected void setUpView() {
        mInformation.setOnClickListener(this);
        mCircle.setOnClickListener(this);
        mFoot.setOnClickListener(this);
        mWallet.setOnClickListener(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_my;
    }

    /**
     * 点击事件的监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_person_information:
                startActivity(new Intent(getActivity(), InformationActivity.class));
                break;
            case R.id.tv_person_circle:
                startActivity(new Intent(getActivity(), CircleActivity.class));
                break;
            case R.id.tv_person_foot:
                startActivity(new Intent(getActivity(), FootActivity.class));
                break;
            case R.id.tv_person_wallet:
                startActivity(new Intent(getActivity(), WalletActivity.class));
                break;
            case R.id.tv_person_address:
                startActivity(new Intent(getActivity(), AddressActivity.class));
                break;
        }
    }
}
