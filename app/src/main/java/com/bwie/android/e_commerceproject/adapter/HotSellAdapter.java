package com.bwie.android.e_commerceproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.activity.CommodityInfoActivity;
import com.bwie.android.e_commerceproject.api.InfoCallback;
import com.bwie.android.e_commerceproject.bean.product.GoodsListBean;

import java.util.HashMap;
import java.util.List;

public class HotSellAdapter extends RecyclerView.Adapter<HotSellAdapter.HotSellViewHolder> {
    private Context context;
    private List<GoodsListBean.ResultBean.RxxpBean.CommodityListBean> list;
    private InfoCallback infoCallback;

    public void setInfoCallback(InfoCallback infoCallback) {
        this.infoCallback = infoCallback;
    }

    public HotSellAdapter(Context context, List<GoodsListBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HotSellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot_sell, parent, false);
        HotSellViewHolder holder = new HotSellViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull HotSellViewHolder holder, final int position) {
        holder.tv_name.setText(list.get(position).getCommodityName());
        holder.tv_price.setText("￥" + list.get(position).getPrice());
//        holder.imageView.getBackground().setAlpha(50);
        Glide.with(context)
                .load(list.get(position).getMasterPic())
                .into(holder.imageView);
        if (position == list.size() - 1) {
            holder.mView.setVisibility(View.GONE);
        } else {
            holder.mView.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commodityId = list.get(position).getCommodityId();
                Intent intent = new Intent(context, CommodityInfoActivity.class);
                intent.putExtra("commodityId", commodityId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class HotSellViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tv_name;
        private TextView tv_price;
        private View mView;

        public HotSellViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_phone);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            mView = itemView.findViewById(R.id.mView);

        }
    }
}
