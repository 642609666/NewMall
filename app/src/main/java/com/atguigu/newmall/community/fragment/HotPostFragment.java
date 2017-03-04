package com.atguigu.newmall.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.newmall.R;
import com.atguigu.newmall.base.BaseFragment;
import com.atguigu.newmall.community.adapter.HotPostListViewAdapter;
import com.atguigu.newmall.community.bean.HotPostBean;
import com.atguigu.newmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/4 0004.
 * 功能:
 */

public class HotPostFragment extends BaseFragment {
    @BindView(R.id.lv_hot_post)
    ListView lvHotPost;
    private HotPostListViewAdapter adapter;

    /**
     * 初始化个人视图
     *
     * @return
     */
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_hot_post, null);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 初始化个人数据
     */
    @Override
    protected void initData() {

        getDataFromNet();
        Log.e("TAG", "UserFragment initData()++火的数据加载");
    }

    public void getDataFromNet() {
        OkHttpUtils
                .get()
                //联网地址
                .url(Constants.HOT_POST_URL)
                .id(100)//http,
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "热帖联网成功==");
                        processData(response);

                    }
                });

    }

    private void processData(String json) {
        HotPostBean bean = JSON.parseObject(json, HotPostBean.class);
        List<HotPostBean.ResultEntity> result = bean.getResult();
        if (result != null && result.size() > 0) {

            //设置适配器
            adapter = new HotPostListViewAdapter(mContext, result);
            lvHotPost.setAdapter(adapter);
        }
    }
}
