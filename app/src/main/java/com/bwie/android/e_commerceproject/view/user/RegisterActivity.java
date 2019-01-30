package com.bwie.android.e_commerceproject.view.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.contract.user.RegContract;
import com.bwie.android.e_commerceproject.persenter.ProductPresenter;
import com.bwie.android.e_commerceproject.persenter.user.RegPresenter;
import com.bwie.android.lib_core.base.mvp.BaseMvpActivity;
import com.bwie.android.lib_core.base.mvp.BasePersenter;

import java.util.HashMap;

import butterknife.BindView;

public class RegisterActivity extends BaseMvpActivity<RegContract.IRegModel, RegContract.RegPresenter> implements RegContract.IRegView, View.OnClickListener {

    @BindView(R.id.btn_regester)
    Button btn_regester;
    @BindView(R.id.reg_phone)
    EditText reg_phone;
    @BindView(R.id.reg_code)
    EditText reg_code;
    @BindView(R.id.reg_pwd)
    EditText reg_pwd;
    @BindView(R.id.tv_obtain)
    TextView tv_obtain;
    @BindView(R.id.iv_eye)
    ImageView iv_eye;
    @BindView(R.id.tv_login)
    TextView tv_login;


    @Override
    protected void initView() {
//        注册
        btn_regester.setOnClickListener(this);
//        获取验证码
        tv_obtain.setOnClickListener(this);
//        显示隐藏密码
        iv_eye.setOnClickListener(this);
//        已有账号立即登录
        tv_login.setOnClickListener(this);
    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void regSuccess(String result) {
        showToast("注册成功");
    }

    @Override
    public void phoneError(String error) {
        showToast(error);
    }

    @Override
    public BasePersenter initPeresnter() {
        return new RegPresenter();
    }

    @Override
    public void failure(String msg) {
        showToast(msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_regester:
                HashMap<String, String> map = new HashMap<>();
                String phone = reg_phone.getText().toString().trim();
                String pwd = reg_pwd.getText().toString().trim();
                map.put("phone", phone);
                map.put("pwd", pwd);
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
                    persenter.register(map);
                }
                break;
            case R.id.tv_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
}
