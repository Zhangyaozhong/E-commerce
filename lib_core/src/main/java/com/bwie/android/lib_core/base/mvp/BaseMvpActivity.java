package com.bwie.android.lib_core.base.mvp;

import com.bwie.android.lib_core.base.BaseActivity;

public abstract class BaseMvpActivity<M extends IBaseModel, P extends BasePersenter> extends BaseActivity implements IBaseView {
    public P persenter;
    public M model;

    @Override
    protected void initData() {
        persenter = (P) initPeresnter();
        if (persenter != null) {
            model = (M) persenter.getModel();
            if (model != null) {
                persenter.attach(model, this);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (persenter != null) {
            persenter.dettach();
        }
    }
}
