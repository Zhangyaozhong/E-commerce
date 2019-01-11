package com.bwie.android.e_commerceproject.contract.user;

import com.bwie.android.e_commerceproject.bean.user.LoginBean;
import com.bwie.android.e_commerceproject.model.user.LoginModel;
import com.bwie.android.lib_core.base.mvp.BasePersenter;
import com.bwie.android.lib_core.base.mvp.IBaseModel;
import com.bwie.android.lib_core.base.mvp.IBaseView;

import java.util.HashMap;

public interface LoginContract {
    abstract class LoginPresenter extends BasePersenter<ILoginModel, ILoginView> {
        @Override
        public ILoginModel getModel() {
            return new LoginModel();
        }

        public abstract void login(HashMap<String, String> params);
    }

    interface ILoginModel extends IBaseModel {
        void loginSuccess(HashMap<String, String> params, LoginModel.LoginCallback loginCallback);
    }

    interface ILoginView extends IBaseView {
        void loginSuccess(LoginBean resultData);



        void phoneIsRight(String msg);
    }
}
