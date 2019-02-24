package com.bwie.android.e_commerceproject.activity;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.bean.user.LoginBean;
import com.bwie.android.e_commerceproject.contract.user.LoginContract;
import com.bwie.android.e_commerceproject.persenter.user.LoginPresenter;
import com.bwie.android.lib_core.base.mvp.BaseMvpActivity;
import com.bwie.android.lib_core.base.mvp.BasePersenter;
import com.bwie.android.lib_network.network.HttpContants;

import java.util.HashMap;

import butterknife.BindView;


public class LoginActivity extends BaseMvpActivity<LoginContract.ILoginModel, LoginContract.LoginPresenter> implements LoginContract.ILoginView, View.OnClickListener {

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_zhuce)
    TextView tv_zhuce;
    @BindView(R.id.cb_remember)
    CheckBox cb_remember;
    @BindView(R.id.togglePwd)
    ToggleButton togglePwd;
    private static String SUCCESS_FLAG = "";
    private String phone;
    private String pwd;

    @Override
    public void loginSuccess(LoginBean resultData) {
        ToastUtils.showShort(resultData.message);
//        登录成功之后将userId与sessionId存起来
        SUCCESS_FLAG = resultData.status;
        if (SUCCESS_FLAG.equals(HttpContants.SUCCESS)) {
            //保存账号，密码
            SPUtils.getInstance().put("phone", phone);
            SPUtils.getInstance().put("pwd", pwd);
            LoginBean.ResultData result = resultData.result;

            SPUtils.getInstance().put("userId", result.userId);
            SPUtils.getInstance().put("sessionId", result.sessionId);
            //                登录成功调转到首页
            startActivity(HomePageActivity.class);
            finish();

        }


    }


    @Override
    public void phoneIsRight(String msg) {
        showToast(msg);
    }


    @Override
    protected void initView() {

//        登录
        btn_login.setOnClickListener(this);
//        注册
        tv_zhuce.setOnClickListener(this);
        //        判断是否记住密码
        if (SPUtils.getInstance().getBoolean("remember")) {
            String phone = SPUtils.getInstance().getString("phone");
            String pwd = SPUtils.getInstance().getString("pwd");
            et_phone.setText(phone);
            et_pwd.setText(pwd);
            cb_remember.setChecked(true);
        }
//        显示隐藏密码
        togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public BasePersenter initPeresnter() {
        return new LoginPresenter();
    }

    @Override
    public void failure(String msg) {
        showToast(msg);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //        点击登录
            case R.id.btn_login:

                HashMap<String, String> params = new HashMap<>();
                phone = et_phone.getText().toString();
                pwd = et_pwd.getText().toString();

                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {

                    params.put("phone", phone);
                    params.put("pwd", pwd);
                    persenter.login(params);
                } else {
                    showToast("账号或密码不能为空");
                }
                //是否记住密码
                if (cb_remember.isChecked()) {
                    SPUtils.getInstance().put("remember", true);
                } else {
                    SPUtils.getInstance().put("remember", false);
                }


                break;
            case R.id.tv_zhuce:
                startActivity(RegisterActivity.class);

                break;

        }
    }
}

