package com.bwie.android.e_commerceproject.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bwie.android.e_commerceproject.R;

public class AdderView extends FrameLayout implements View.OnClickListener {

    private TextView minus;
    private TextView add;



    private EditText num;
    public AdderView(Context context) {
        this(context,null);
    }

    public AdderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public AdderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //加载自己定义的布局
        View view = View.inflate(context, R.layout.adder, this);
        //获取减按钮并设置点击事件
        minus = view.findViewById(R.id.minus);
        //设置减的监听  点击事件
        minus.setOnClickListener(this);
        //获取EditText的控件
        num = view.findViewById(R.id.num);
        //获取加按钮并设置点击事件
        add = view.findViewById(R.id.add);
        //设置加的监听  点击事件
        add.setOnClickListener(this);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //减的方法
            case R.id.minus:
                //获取EditText得值
                int i = get();
                //判断i是否为小于或等于0
                if (i <= 0) {
                    //如果是小于或等于0就设置按钮不可用
                    minus.setEnabled(false);
                } else {
                    //如果不是小于或等于0就设置按钮可用
                    minus.setEnabled(true);
                    //EditText的值  向--
                    i--;
                    //判断i是否为小于或等于0
                    if (i <= 0) {
                        //如果是小于或等于0就设置EditText的值=1
                        i = 1;
                    }
                }
                //给EditText赋值
                num.setText(i + "");
                break;
            //加的方法
            case R.id.add:
                //获取EditText得值
                int i1 = get();
                //判断i是否为小于或等于0
                if (i1 <= 0) {
                    //如果是小于或等于0就设置按钮不可用
                    add.setEnabled(false);
                } else {
                    //如果不是小于或等于0就设置按钮可用
                    add.setEnabled(true);
                    //EditText的值  向--
                    i1++;
                }
                //给EditText赋值
                num.setText(i1 + "");
                break;
        }
    }

    //获取EditText的值的方法
    public int get(){
        int d = 0;
        //获取EditText的值
        String  s = num.getText().toString();
        if (TextUtils.isEmpty(s)){
            d = 1;
        }
        return Integer.valueOf(s);
    }
}
