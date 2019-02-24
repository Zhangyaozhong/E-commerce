package com.bwie.android.e_commerceproject.activity;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.bean.user.InformationBean;
import com.bwie.android.e_commerceproject.contract.user.PersonContract;
import com.bwie.android.e_commerceproject.persenter.user.PersonPresenter;
import com.bwie.android.lib_core.base.mvp.BaseMvpActivity;
import com.bwie.android.lib_core.base.mvp.BasePersenter;
import com.bwie.android.lib_core.utils.SpUtils;

import java.util.HashMap;

public class InformationActivity extends BaseMvpActivity<PersonContract.IPersonModel, PersonContract.PersonPresenter> implements PersonContract.IPersonView {


    private TextView mNmae;
    private TextView mPassword;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        String userid = SPUtils.getInstance().getString("userId");
        String sessionid = SPUtils.getInstance().getString("sessionId");
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userid);
        map.put("sessionId", sessionid);
        persenter.Person(map);
    }

    @Override
    protected void initView() {
        mNmae = findViewById(R.id.tv_name);
        mPassword = findViewById(R.id.tv_pwd);
    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_information;
    }

    /**
     * 请求成功的回调
     *
     * @param informationBean
     */
    @Override
    public void PersonSuccess(InformationBean informationBean) {
        InformationBean.ResultBean result = informationBean.result;
        mNmae.setText(result.nickName);
        mPassword.setText(result.password);
    }

    @Override
    public BasePersenter initPeresnter() {
        return new PersonPresenter();
    }

    @Override
    public void failure(String msg) {

    }

}
