package com.bwie.android.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.api.InfoCallback;
import com.bwie.android.e_commerceproject.bean.product.BannerBean;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;
import com.bwie.android.lib_core.utils.LogUtil;
import com.bwie.android.lib_core.utils.SpUtils;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_HOT_SELL = 1;
    public static final int TYPE_MAGIC_FASHION = 2;
    public static final int TYPE_QUALITY_LIFE = 3;

    public static boolean isClick = false;
    private List<String> imgList;
    private Context context;
    private List<BannerBean.ResultBean> bannerList;
    private GoodsListBean.ResultBean resultBean;

    public HomeListAdapter(Context context, List<BannerBean.ResultBean> bannerList, GoodsListBean.ResultBean resultBean) {
        this.context = context;
        this.bannerList = bannerList;
        this.resultBean = resultBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.banner_layout, parent, false);
                BannerViewHolder bannerViewHolder = new BannerViewHolder(view);
                return bannerViewHolder;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.hot_sell_layout, parent, false);
                HotSellViewHolder hotSellViewHolder = new HotSellViewHolder(view);
                return hotSellViewHolder;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.magic_layout, parent, false);
                MagicFashionViewHolder magicFashionViewHolder = new MagicFashionViewHolder(view);
                return magicFashionViewHolder;
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.high_life_layout, parent, false);
                HigeQualityViewHolder higeQualityViewHolder = new HigeQualityViewHolder(view);
                return higeQualityViewHolder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BannerViewHolder) {
            imgList = new ArrayList<>();
            for (BannerBean.ResultBean resultBean : bannerList) {
                String imageUrl = resultBean.imageUrl;
                imgList.add(imageUrl);
            }
            ((BannerViewHolder) holder).xbanner.setData(imgList, null);
            ((BannerViewHolder) holder).xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context)
                            .load(imgList.get(position))
                            .into((ImageView) view);
                }
            });
            ((BannerViewHolder) holder).xbanner.setPageTransformer(Transformer.Default);//横向移动
        } else if (holder instanceof HotSellViewHolder) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((HotSellViewHolder) holder).rvHotSell.setLayoutManager(linearLayoutManager);
//            设置适配器
            HotSellAdapter hotSellAdapter = new HotSellAdapter(context, resultBean.getRxxp().get(0).getCommodityList());

            ((HotSellViewHolder) holder).rvHotSell.setAdapter(hotSellAdapter);
            ((HotSellViewHolder) holder).tvHotSell.setText(resultBean.getRxxp().get(0).getName());
            //3个小圆点的点击事件
            ((HotSellViewHolder) holder).ivMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickCallback != null) {
                        clickCallback.moreBtnClick(position, v);
                    }

                }
            });
        } else if (holder instanceof MagicFashionViewHolder) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((MagicFashionViewHolder) holder).rvMagicFashion.setLayoutManager(linearLayoutManager);
            ((MagicFashionViewHolder) holder).rvMagicFashion.setAdapter(new MagicFashionAdapter(context, resultBean.getMlss().get(0).getCommodityList()));
            ((MagicFashionViewHolder) holder).tvMagicFashion.setText(resultBean.getMlss().get(0).getName());
        } else {
            ((HigeQualityViewHolder) holder).rvHighLife.setLayoutManager(new GridLayoutManager(context, 2));
            ((HigeQualityViewHolder) holder).rvHighLife.setAdapter(new HighLifeAdapter(context, resultBean.getPzsh().get(0).getCommodityList()));
            ((HigeQualityViewHolder) holder).tvHighLife.setText(resultBean.getPzsh().get(0).getName());
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_HOT_SELL;
        } else if (position == 2) {
            return TYPE_MAGIC_FASHION;
        } else {
            return TYPE_QUALITY_LIFE;
        }

    }


    @Override
    public int getItemCount() {
        return 4;
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.xbanner)
        XBanner xbanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    class HotSellViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_hot_sell)
        TextView tvHotSell;
        @BindView(R.id.iv_more)
        ImageView ivMore;
        @BindView(R.id.rv_hot_sell)
        RecyclerView rvHotSell;

        public HotSellViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MagicFashionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_magic_fashion)
        TextView tvMagicFashion;
        @BindView(R.id.rv_magic_fashion)
        RecyclerView rvMagicFashion;

        public MagicFashionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HigeQualityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_high_life)
        TextView tvHighLife;
        @BindView(R.id.rv_high_life)
        RecyclerView rvHighLife;

        public HigeQualityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ClickCallback {
        void moreBtnClick(int pos, View view);
    }

    private ClickCallback clickCallback;

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }
}