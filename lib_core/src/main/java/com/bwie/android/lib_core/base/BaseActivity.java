package com.bwie.android.lib_core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bwie.android.lib_core.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    /**
     * 是否沉浸状态栏
     */
    private boolean isSetStatusBar = true;
    /**
     * 是否允许全屏
     */
    private boolean isAllowFullScreen = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    /**
     * 绑定布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 显示toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 无参跳转
     *
     * @param aClass
     */
    public void startActivity(Class<?> aClass) {
        startActivity(new Intent(this, aClass));
    }

    /**
     * 有参跳转
     *
     * @param bundle
     * @param aClass
     */
    public void startActivity(Bundle bundle, Class<?> aClass) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 沉浸式状态栏
     *
     * @param isSetStatusBar
     */
    public void setSetStatusBar(boolean isSetStatusBar) {
        if (isSetStatusBar) {

        }
    }

    /**
     * 全屏
     *
     * @param isAllowFullScreen
     */
    public void setAllowFullScreen(boolean isAllowFullScreen) {
        if (isAllowFullScreen) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i("onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy");
//        解绑butterKiife
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i("onPause");
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("onResume");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        LogUtil.i("onRestart");
    }
}
