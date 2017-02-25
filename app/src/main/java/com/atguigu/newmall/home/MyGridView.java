package com.atguigu.newmall.home;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/25 0025.
 * 功能:自定义gridview
 */

public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重新加载高度
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
