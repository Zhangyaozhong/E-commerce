package com.bwie.android.e_commerceproject.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

public class SimpleToolbar extends Toolbar {
    public SimpleToolbar(Context context) {
        this(context,null);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }
}
