package com.bwie.android.e_commerceproject.view;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.fragment.CartFragment;
import com.bwie.android.e_commerceproject.fragment.CircleFragmennt;
import com.bwie.android.e_commerceproject.fragment.HomeFragment;
import com.bwie.android.e_commerceproject.fragment.ListFragment;
import com.bwie.android.e_commerceproject.fragment.MyFragment;
import com.bwie.android.lib_core.base.BaseActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

public class HomeActivity extends BaseActivity  {

    @BindView(R.id.fl)
    FrameLayout fl;
  /*  @BindView(R.id.iv_home)
    ImageView iv_home;
    @BindView(R.id.iv_circle)
    ImageView iv_circle;
    @BindView(R.id.iv_cart)
    ImageView iv_cart;
    @BindView(R.id.iv_list)
    ImageView iv_list;
    @BindView(R.id.iv_my)
    ImageView iv_my;*/


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
       /* iv_home.setOnClickListener(this);
        iv_circle.setOnClickListener(this);
        iv_cart.setOnClickListener(this);
        iv_list.setOnClickListener(this);
        iv_my.setOnClickListener(this);*/

    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


  /*  @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_home:
                ob = new HomeFragment();
                break;
            case R.id.iv_circle:
                ob = new HomeFragment();
                break;
            case R.id.iv_cart:
                ob = new HomeFragment();
                break;
            case R.id.iv_list:
                ob = new HomeFragment();
                break;
            case R.id.iv_my:
                ob = new HomeFragment();
                break;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl, (Fragment) ob).commit();
    }*/
}
