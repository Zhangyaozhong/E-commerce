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

import java.util.List;

public class HighLifeAdapter extends RecyclerView.Adapter<HighLifeAdapter.LifeViewHolder> {

    private Context context;
    private List<GoodsListBean.ResultBean.PzshBean.CommodityListBeanX> list;

    public HighLifeAdapter(Context context, List<GoodsListBean.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LifeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hige_life, parent, false);
        LifeViewHolder LifeViewHolder = new LifeViewHolder(view);
        return LifeViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull LifeViewHolder holder, final int position) {
        String price = list.get(position).getPrice();
        String commodityName = list.get(position).getCommodityName();
        String masterPic = list.get(position).getMasterPic();
        holder.hige_name.setText(commodityName);
        holder.hige_price.setText("￥" + price);
        Glide.with(context).load(masterPic).into(holder.imageView);
        if (position == list.size() - 1) {
            holder.v.setVisibility(View.GONE);
        } else {
            holder.v.setVisibility(View.VISIBLE);
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

    class LifeViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView hige_name;
        private TextView hige_price;
        private View v;

        public LifeViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_high);
            hige_name = itemView.findViewById(R.id.hige_name);
            hige_price = itemView.findViewById(R.id.hige_price);
            v = itemView.findViewById(R.id.v);
        }
    }
}
