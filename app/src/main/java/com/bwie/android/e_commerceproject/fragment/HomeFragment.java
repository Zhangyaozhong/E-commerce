package com.bwie.android.e_commerceproject.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.activity.SearchActivity;
import com.bwie.android.e_commerceproject.adapter.HomeListAdapter;
import com.bwie.android.e_commerceproject.adapter.PopAdapter;
import com.bwie.android.e_commerceproject.adapter.RxxpLabelAdapter;
import com.bwie.android.e_commerceproject.bean.product.BannerBean;
import com.bwie.android.e_commerceproject.bean.product.FirstCategoryBean;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;
import com.bwie.android.e_commerceproject.bean.product.LabelListBean;
import com.bwie.android.e_commerceproject.bean.product.PopBean;
import com.bwie.android.e_commerceproject.bean.product.SecondCategoryBean;
import com.bwie.android.e_commerceproject.contract.product.ProductContract;
import com.bwie.android.e_commerceproject.persenter.ProductPresenter;
import com.bwie.android.lib_core.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment implements ProductContract.IProductView, View.OnClickListener, HomeListAdapter.ClickCallback {
    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.mXRecycleView)
    XRecyclerView mXRecycleView;
    @BindView(R.id.iv_more)
    ImageView iv_more;
    @BindView(R.id.rv_go)
    RecyclerView rvGo;
    private List<String> labelId = new ArrayList<>();
    //P层的对象
    private ProductPresenter productPresenter;
    private List<BannerBean.ResultBean> bannerList = new ArrayList<>();
    //    一级类目
    private List<FirstCategoryBean.ResultBean> firstList;
    private List<PopBean> popList;
    private List<FirstCategoryBean.ResultBean> data;
    private int[] imgs;
    private HomeListAdapter homeListAdapter;

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
       /* if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }*/
//        搜索框的点击事件
        etSearch.setOnClickListener(this);
/*//        搜索按钮的事件
        ivSearch.setOnClickListener(this);*/
        //更多点击监听
        iv_more.setOnClickListener(this);
        rvGo.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
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
        String rxxpId = result1.getRxxp().get(0).getId();
        String mlssId = result1.getMlss().get(0).getId();
        String pzshId = result1.getPzsh().get(0).getId();
        labelId.add(rxxpId);
        labelId.add(mlssId);
        labelId.add(pzshId);
        homeListAdapter = new HomeListAdapter(getActivity(), bannerList, result1);
        homeListAdapter.setClickCallback(this);
        mXRecycleView.setAdapter(homeListAdapter);
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
        if (resultBean != null && resultBean.size() > 0) {
            for (SecondCategoryBean.ResultBean bean : resultBean) {
                if (bean != null) {
                    String name = bean.getName();
                    popList.add(new PopBean(name, imgs));
                }
            }
        }

    }

    /**
     * 根据商品列表归属标签查询商品信息
     *
     * @param response
     */
    @Override
    public void labelSuccess(List<LabelListBean> response) {

        RxxpLabelAdapter rxxpLabelAdapter = new RxxpLabelAdapter(response, getActivity());
        rvGo.setAdapter(rxxpLabelAdapter);
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
//                搜索按钮的点击事件
            case R.id.et_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        productPresenter.destory();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void moreBtnClick(int pos, View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("labelId", labelId.get(pos));
        params.put("page", "1");
        params.put("count", "10");
        productPresenter.getLabelList(params);
        if (rvGo.getVisibility() == View.GONE) {
            rvGo.setVisibility(View.VISIBLE);
            mXRecycleView.setVisibility(View.GONE);

        } else {
            rvGo.setVisibility(View.GONE);
            mXRecycleView.setVisibility(View.VISIBLE);
        }
    }


}
