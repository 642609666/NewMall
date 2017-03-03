package com.atguigu.newmall.type.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.atguigu.newmall.base.BaseFragment;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/3 0003.
 * 功能:
 */

public class TabFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("我是tab");
        Log.e("TAG", "tab碎片视图加载成功");
        return textView;
    }

    @Override
    protected void initData() {

        Log.e("TAG", "tab碎片数据加载成功");
    }
}