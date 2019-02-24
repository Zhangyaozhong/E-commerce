package com.bwie.android.lib_core.base.mvp;

public interface IBaseView {
    //初始化presenter
    BasePersenter initPeresnter();
    void failure(String msg);//请求失败
//    void showLoading();//显示加载框
//    void hideLoading();//隐藏加载框

}
