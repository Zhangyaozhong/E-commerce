package com.bwie.android.e_commerceproject.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.fragment.CartFragment;
import com.bwie.android.e_commerceproject.fragment.CircleFragmennt;
import com.bwie.android.e_commerceproject.fragment.HomeFragment;
import com.bwie.android.e_commerceproject.fragment.ListFragment;
import com.bwie.android.e_commerceproject.fragment.MyFragment;
import com.bwie.android.lib_core.base.BaseActivity;

import butterknife.BindView;

public class HomePageActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        navigation.setOnNavigationItemSelectedListener(this);
    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_page;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Object ob = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                ob = new HomeFragment();
                break;
            case R.id.navigation_circle:
                ob = new CircleFragmennt();
                break;
            case R.id.navigation_cart:
                ob = new CartFragment();
                break;
            case R.id.navigation_list:
                ob = new ListFragment();
                break;
            case R.id.navigation_my:
                ob = new MyFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, (Fragment) ob).commit();
        return false;
    }
}
