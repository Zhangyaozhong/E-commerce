package com.bwie.android.e_commerceproject.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.fragment.CartFragment;
import com.bwie.android.e_commerceproject.fragment.CircleFragmennt;
import com.bwie.android.e_commerceproject.fragment.HomeFragment;
import com.bwie.android.e_commerceproject.fragment.ListFragment;
import com.bwie.android.e_commerceproject.fragment.MyFragment;
import com.bwie.android.lib_core.base.BaseActivity;

import butterknife.BindView;

public class HomePageActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rg_tab)
    RadioGroup rg_tab;
    @BindView(R.id.send_rl)
    RelativeLayout mSendrl;
    private Fragment[] mFragments;
    private HomeFragment homeFragment;
    private CircleFragmennt circleFragmennt;
    private CartFragment cartFragment;
    private ListFragment listFragment;
    private MyFragment myFragment;

    @Override
    protected void initData() {
        homeFragment = new HomeFragment();
        circleFragmennt = new CircleFragmennt();
        cartFragment = new CartFragment();
        listFragment = new ListFragment();
        myFragment = new MyFragment();
        mFragments = new Fragment[5];
        mFragments[0] = homeFragment;
        mFragments[1] = circleFragmennt;
        mFragments[2] = cartFragment;
        mFragments[3] = listFragment;
        mFragments[4] = myFragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mFragments[0]).commit();

    }

    @Override
    protected void initView() {
        //默认展示首页
//        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
//        默认选中首页
        rg_tab.check(rg_tab.getChildAt(0).getId());

        rg_tab.setOnCheckedChangeListener(this);

        mSendrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg_tab.clearCheck();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, mFragments[2]);
                transaction.commit();
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
        return R.layout.activity_home_page;
    }




    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.home_rb:
                transaction.replace(R.id.frameLayout, mFragments[0]);
                break;
            case R.id.circle_rb:
                transaction.replace(R.id.frameLayout, mFragments[1]);
                break;
            case R.id.list_rb:
                transaction.replace(R.id.frameLayout, mFragments[3]);
                break;
            case R.id.person_rb:
                transaction.replace(R.id.frameLayout, mFragments[4]);
                break;
        }
        transaction.commit();
    }

}
