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

public class MagicFashionAdapter extends RecyclerView.Adapter<MagicFashionAdapter.MagicViewHolder> {

    private Context context;
    private List<GoodsListBean.ResultBean.MlssBean.CommodityListBeanXX> list;

    public MagicFashionAdapter(Context context, List<GoodsListBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }
    private InfoCallback infoCallback;

    public void setInfoCallback(InfoCallback infoCallback) {
        this.infoCallback = infoCallback;
    }

    @NonNull
    @Override
    public MagicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_magic_fashion, parent, false);
        MagicViewHolder magicViewHolder = new MagicViewHolder(view);
        return magicViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MagicViewHolder holder, final int position) {
        String price = list.get(position).getPrice();
        String commodityName = list.get(position).getCommodityName();
        String masterPic = list.get(position).getMasterPic();
        holder.magin_name.setText(commodityName);
        holder.magin_price.setText("￥"+price);
        Glide.with(context).load(masterPic).into(holder.imageView);
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

    class MagicViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView magin_name;
        private TextView magin_price;

        public MagicViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_good);
            magin_name = itemView.findViewById(R.id.magin_name);
            magin_price = itemView.findViewById(R.id.magin_price);

        }
    }
}
