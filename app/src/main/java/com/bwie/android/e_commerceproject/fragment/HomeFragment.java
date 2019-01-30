package com.bwie.android.e_commerceproject.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.adapter.HomeListAdapter;
import com.bwie.android.e_commerceproject.adapter.PopAdapter;
import com.bwie.android.e_commerceproject.bean.product.BannerBean;
import com.bwie.android.e_commerceproject.bean.product.FirstCategoryBean;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;
import com.bwie.android.e_commerceproject.bean.product.PopBean;
import com.bwie.android.e_commerceproject.bean.product.SecondCategoryBean;
import com.bwie.android.e_commerceproject.contract.product.ProductContract;
import com.bwie.android.e_commerceproject.persenter.ProductPresenter;
import com.bwie.android.lib_core.base.BaseFragment;
import com.bwie.android.lib_core.base.mvp.BasePersenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment implements ProductContract.IProductView, View.OnClickListener {
    private ProductPresenter productPresenter;
    private List<BannerBean.ResultBean> bannerList = new ArrayList<>();
    private List<FirstCategoryBean.ResultBean> firstList;
    private List<PopBean> popList;
    @BindView(R.id.mXRecycleView)
    XRecyclerView mXRecycleView;
    @BindView(R.id.iv_more)
    ImageView iv_more;
    private List<FirstCategoryBean.ResultBean> data;
    private int[] imgs;

    @Override
    protected void setUpData() {
        imgs = new int[]{R.drawable.top_dadimaoyi_syf, R.drawable.top_kuzhuang_syf,
                R.drawable.top_qunzhuang_syf,
                R.drawable.top_waitao_syf,
                R.drawable.top_weiyi_syf};


        productPresenter = new ProductPresenter(this);
        productPresenter.getProductList(new HashMap<String, String>());
        productPresenter.getGoodsList(new HashMap<String, String>());
        //一级类目
        productPresenter.getFirstCategory(new HashMap<String, String>());

    }

    /**
     * 一些View的相关操作
     */
    @Override
    protected void setUpView() {
        //更多点击监听
        iv_more.setOnClickListener(this);
        //XrecycleView设置布局以及方向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecycleView.setLayoutManager(linearLayoutManager);
//        支持上拉刷新下拉加载
        mXRecycleView.setPullRefreshEnabled(true);
        mXRecycleView.setLoadingMoreEnabled(true);

        mXRecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                setUpData();
                mXRecycleView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXRecycleView.refreshComplete();
            }
        });
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    /**
     * 轮播图的回调方法
     *
     * @param resultBeanList
     */
    @Override
    public void bannerSuccess(final List<BannerBean.ResultBean> resultBeanList) {
        bannerList = resultBeanList;

    }

    /**
     * 首页成功的回调方法
     *
     * @param result1
     */
    @Override
    public void goodsSuccess(GoodsListBean.ResultBean result1) {
        mXRecycleView.setAdapter(new HomeListAdapter(getActivity(), bannerList, result1));
//        mXRecycleView.addItemDecoration(new SpacesItemDecoration(10));

    }

    /**
     * 一级类目成功的回调方法
     *
     * @param resultBean
     */
    @Override
    public void firstCategorySuccess(List<FirstCategoryBean.ResultBean> resultBean) {
        data = new ArrayList<>();
        data = resultBean;

        firstList = new ArrayList<>();
        for (FirstCategoryBean.ResultBean bean : resultBean) {
            firstList.add(bean);
        }
        //二级类目
//        productPresenter.getSecondCategory(secondMap);

    }

    /**
     * 二级类目成功的回调方法
     *
     * @param resultBean
     */
    @Override
    public void secondCategorySuccess(List<SecondCategoryBean.ResultBean> resultBean) {
        popList = new ArrayList<>();
        for (SecondCategoryBean.ResultBean bean : resultBean) {
            if (bean != null) {
                String name = bean.getName();
                popList.add(new PopBean(name, imgs));
            }
        }
    }


    @Override
    public BasePersenter initPeresnter() {
        return null;
    }

    @Override
    public void failure(String msg) {

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
            //更多的点击事件
            case R.id.iv_more:
                View popView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout, null, false);
                LinearLayout title_line = popView.findViewById(R.id.title_line);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                动态设置间距
                params.leftMargin = 24;
                for (int i = 0; i < firstList.size(); i++) {
                    String id = firstList.get(i).getId();
                    String name = firstList.get(i).getName();
                    final TextView textView = new TextView(getActivity());
//                    设置标题跟id
                    textView.setText(name);
                    textView.setId(Integer.parseInt(id));
                    //设置字体大小颜色
                    textView.setTextSize(16);
                    textView.setTextColor(0xffffffff);
                    title_line.addView(textView, params);
                    //标题点击事件
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getActivity(), textView.getId() + "", Toast.LENGTH_SHORT).show();
//                            请求二级类目
                            HashMap<String, String> map = new HashMap<>();
                            map.put("firstCategoryId", textView.getId() + "");
                            productPresenter.getSecondCategory(map);
                            //设置标题点击弹窗
                            showChildPopWindow(textView);
                        }
                    });
                }
                PopupWindow window = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                // 设置PopupWindow是否能响应外部点击事件
                window.setOutsideTouchable(true);
                // 设置PopupWindow是否能响应点击事件
                window.setTouchable(true);
                // 设置PopupWindow的背景
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                window.showAsDropDown(view, 0, 16);

                break;
        }
    }

    private void showChildPopWindow(TextView textView) {
        View childPopView = LayoutInflater.from(getActivity()).inflate(R.layout.child_pop_layout, null, false);
        //找控件
        RecyclerView pop_rv = childPopView.findViewById(R.id.pop_rv);
        pop_rv.setAdapter(new PopAdapter(getActivity(), popList));
//                            设置布局管理器
        pop_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        PopupWindow pop = new PopupWindow(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new ColorDrawable(Color.RED));
        pop.showAsDropDown(textView);
    }
}
