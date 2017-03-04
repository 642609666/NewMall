package com.atguigu.newmall.community.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.newmall.base.BaseFragment;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/4 0004.
 * 功能:
 */

public class NewPostFragment extends BaseFragment {
    private TextView mTextView;

    /**
     * 初始化个人视图
     *
     * @return
     */
    @Override
    public View initView() {
        mTextView = new TextView(mContext);
        mTextView.setTextColor(Color.RED);
        mTextView.setTextSize(30);
        mTextView.setGravity(Gravity.CENTER);
        return mTextView;
    }

    /**
     * 初始化个人数据
     */
    @Override
    protected void initData() {
        Log.e("TAG", "UserFragment initData()++新的数据加载");
        mTextView.setText("新的数据");
    }
}
