package com.bwie.android.lib_core.base.mvp;

import java.lang.ref.WeakReference;

public abstract class BasePersenter<M, V> {
    public M model;
    public V view;
    private WeakReference<V> vWeakReference;

    public abstract M getModel();

    //绑定
    public void attach(M model, V view) {
        this.model = model;
//        this.view = view;
        vWeakReference = new WeakReference<>(view);
        this.view = vWeakReference.get();
    }

    /**
     * 解绑
     */
    public void dettach() {
        if (vWeakReference != null) {
            //清空对象
            vWeakReference.clear();
            vWeakReference = null;
            this.view = null;
        }
    }
}
