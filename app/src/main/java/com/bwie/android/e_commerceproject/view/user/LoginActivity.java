package com.bwie.android.e_commerceproject.view.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.bean.user.LoginBean;
import com.bwie.android.e_commerceproject.contract.user.LoginContract;
import com.bwie.android.e_commerceproject.persenter.user.LoginPresenter;
import com.bwie.android.e_commerceproject.view.HomePageActivity;
import com.bwie.android.lib_core.base.mvp.BaseMvpActivity;
import com.bwie.android.lib_core.base.mvp.BasePersenter;
import com.bwie.android.lib_core.utils.SpUtils;

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
    @BindView(R.id.iv_eye)
    ImageView iv_eye;
    private static String SUCCESS_FLAG = "";
    private String phone;
    private String pwd;

    @Override
    public void loginSuccess(LoginBean resultData) {
        showToast("登录成功");
//        登录成功之后将userId与sessionId存起来
        SUCCESS_FLAG = resultData.message;
        if (SUCCESS_FLAG.equals("登录成功")) {
            SpUtils.getInstance().putSp("phone", phone);
            SpUtils.getInstance().putSp("pwd", pwd);
            LoginBean.ResultData result = resultData.result;
            SpUtils.getInstance().putSp("userid", result.userId);
            SpUtils.getInstance().putSp("sessionid", result.sessionId);
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
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initView() {
      /*  iv_eye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                    et_pwd.setVisibility(View.INVISIBLE);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    et_pwd.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });*/
//        登录
        btn_login.setOnClickListener(this);
//        注册
        tv_zhuce.setOnClickListener(this);
        //        判断是否记住密码
        if (SpUtils.getInstance().getBooleanSp("remember")) {
            String phone = SpUtils.getInstance().getSp("phone");
            String pwd = SpUtils.getInstance().getSp("pwd");
            et_phone.setText(phone);
            et_pwd.setText(pwd);
            cb_remember.setChecked(true);
        }
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
                    SpUtils.getInstance().putSp("remember", true);
                } else {
                    SpUtils.getInstance().putSp("remember", false);
                }


                break;
            case R.id.tv_zhuce:
                startActivity(RegisterActivity.class);
                break;

        }
    }
}

