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
import com.bwie.android.e_commerceproject.bean.product.BannerBean;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_HOT_SELL = 1;
    public static final int TYPE_MAGIC_FASHION = 2;
    public static final int TYPE_QUALITY_LIFE = 3;
    private List<String> imgList;
    private List<String> titleList;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            imgList = new ArrayList<>();
            titleList = new ArrayList<>();
            for (BannerBean.ResultBean resultBean : bannerList) {
                String imageUrl = resultBean.imageUrl;
                String title = resultBean.title;
                imgList.add(imageUrl);
                titleList.add(title);
            }
            ((BannerViewHolder) holder).mXBanner.setData(imgList, null);
            ((BannerViewHolder) holder).mXBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context)
                            .load(imgList.get(position))
                            .into((ImageView) view);
                }
            });
            ((BannerViewHolder) holder).mXBanner.setPageTransformer(Transformer.Default);//横向移动
        } else if (holder instanceof HotSellViewHolder) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((HotSellViewHolder) holder).mHotSellRv.setLayoutManager(linearLayoutManager);
//            设置适配器
            ((HotSellViewHolder) holder).mHotSellRv.setAdapter(new HotSellAdapter(context, resultBean.getRxxp().get(0).getCommodityList()));
            ((HotSellViewHolder) holder).tv_hot_sell.setText(resultBean.getRxxp().get(0).getName());
        } else if (holder instanceof MagicFashionViewHolder) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((MagicFashionViewHolder) holder).mMagicFashionRv.setLayoutManager(linearLayoutManager);
            ((MagicFashionViewHolder) holder).mMagicFashionRv.setAdapter(new MagicFashionAdapter(context, resultBean.getMlss().get(0).getCommodityList()));
            ((MagicFashionViewHolder) holder).tv_magic_fashion.setText(resultBean.getMlss().get(0).getName());
        } else {
            ((HigeQualityViewHolder) holder).mHigeLifeRv.setLayoutManager(new GridLayoutManager(context, 2));
            ((HigeQualityViewHolder) holder).mHigeLifeRv.setAdapter(new HighLifeAdapter(context, resultBean.getPzsh().get(0).getCommodityList()));
            ((HigeQualityViewHolder) holder).tv_high_life.setText(resultBean.getPzsh().get(0).getName());
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
        private XBanner mXBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mXBanner = itemView.findViewById(R.id.xbanner);
        }
    }

    class HotSellViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mHotSellRv;
        private TextView tv_hot_sell;
        private ImageView iv_more;

        public HotSellViewHolder(View itemView) {
            super(itemView);
            mHotSellRv = itemView.findViewById(R.id.rv_hot_sell);
            tv_hot_sell = itemView.findViewById(R.id.tv_hot_sell);
            iv_more = itemView.findViewById(R.id.iv_more);
        }
    }

    class MagicFashionViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mMagicFashionRv;
        private TextView tv_magic_fashion;

        public MagicFashionViewHolder(View itemView) {
            super(itemView);
            mMagicFashionRv = itemView.findViewById(R.id.rv_magic_fashion);
            tv_magic_fashion = itemView.findViewById(R.id.tv_magic_fashion);
        }
    }

    class HigeQualityViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mHigeLifeRv;
        private TextView tv_high_life;

        public HigeQualityViewHolder(View itemView) {
            super(itemView);
            mHigeLifeRv = itemView.findViewById(R.id.rv_high_life);
            tv_high_life = itemView.findViewById(R.id.tv_high_life);
        }
    }

}