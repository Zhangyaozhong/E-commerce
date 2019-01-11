package com.bwie.android.e_commerceproject.contract.user;

import com.bwie.android.e_commerceproject.model.user.RegModel;
import com.bwie.android.lib_core.base.mvp.BasePersenter;
import com.bwie.android.lib_core.base.mvp.IBaseModel;
import com.bwie.android.lib_core.base.mvp.IBaseView;

import java.util.HashMap;

public interface RegContract {
    abstract class RegPresenter extends BasePersenter<IRegModel, IRegView> {
        public abstract void register(HashMap<String, String> params);
    }

    interface IRegModel extends IBaseModel {
        void register(HashMap<String, String> params, RegModel.IRegCallback iRegCallback);
    }

    interface IRegView extends IBaseView {
        void regSuccess(String result);

        //        搜索关键字不能为空的回调接口
        void phoneError(String error);
    }
}
