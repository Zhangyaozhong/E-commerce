package com.bwie.android.lib_core.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);

        ButterKnife.bind(this, mContentView);
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


}
