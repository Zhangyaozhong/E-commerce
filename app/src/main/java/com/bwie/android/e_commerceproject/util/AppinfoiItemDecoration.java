package com.bwie.android.e_commerceproject.util;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class AppinfoiItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //不是第一个的格子都设一个左边和底部的间距
        int pos = parent.getChildAdapterPosition(view);
        outRect.left = 25;
        if (pos != 0) {

            if (pos % 2 == 0) {  //下面一行
                outRect.bottom = 30;
                outRect.top = 5;
            } else { //上面一行
                outRect.top = 30;
                outRect.bottom = 5;
            }

        }
    }
}
