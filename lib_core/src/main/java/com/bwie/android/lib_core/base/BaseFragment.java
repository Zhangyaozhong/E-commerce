package com.bwie.android.lib_core.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private View mContentView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);

        unbinder = ButterKnife.bind(this, mContentView);

        setUpView();
        setUpData();
        return mContentView;
    }


    /**
     * 一些Data的相关操作
     */
    protected abstract void setUpData();

    /**
     * 一些View的相关操作
     */
    protected abstract void setUpView();


    protected abstract int setLayoutResourceID();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
