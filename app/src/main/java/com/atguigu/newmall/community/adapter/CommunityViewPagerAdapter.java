package com.atguigu.newmall.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.newmall.base.BaseFragment;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/4 0004.
 * 功能:
 */

public class CommunityViewPagerAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> datas;

    public CommunityViewPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.datas = list;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
