package com.bwie.android.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.android.e_commerceproject.R;
import com.bwie.android.e_commerceproject.bean.product.PopBean;

import java.util.List;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.PopViewHolder> {
    private Context mContext;
    private List<PopBean> list;

    public PopAdapter(Context mContext, List<PopBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public PopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_item_layout, parent, false);
        PopViewHolder holder = new PopViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull PopViewHolder holder, int position) {
        holder.title.setText(list.get(position).title);
        int[] imgs = list.get(position).imgs;
        for (int i = 0; i < imgs.length; i++) {
            holder.iv_pop.setImageResource(imgs[i]);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class PopViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pop;
        private TextView title;

        public PopViewHolder(View itemView) {
            super(itemView);
            iv_pop = itemView.findViewById(R.id.iv_pop);
            title = itemView.findViewById(R.id.tv_popTitle);

        }
    }
}
