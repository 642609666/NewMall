package com.atguigu.newmall.type.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.newmall.R;
import com.atguigu.newmall.base.BaseFragment;
import com.atguigu.newmall.type.adapter.TagGridViewAdapter;
import com.atguigu.newmall.type.bean.TagBean;
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
 * QQ/微信: 642609666} on 3/3 0003.
 * 功能:
 */

public class TabFragment extends BaseFragment {
    @BindView(R.id.gv_tag)
    GridView gvTag;

    private TagGridViewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tag, null);
        Log.e("TAG", "tab碎片视图加载成功");
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        Log.e("TAG", "tab碎片数据加载成功");
        getDataFromNet();
    }

    public void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constants.TAG_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "tag联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "tag联网成功");
                        processData(response);
                    }
                });

    }

    private void processData(String json) {
        TagBean tagBean = JSON.parseObject(json, TagBean.class);
        final List<TagBean.ResultEntity> result = tagBean.getResult();
        if (result.size() > 0 && result != null) {
            //设置适配器
            adapter = new TagGridViewAdapter(mContext, result);
            gvTag.setAdapter(adapter);
            //设置点击事件
            gvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TagBean.ResultEntity bean = result.get(position);
                    Toast.makeText(mContext, "" + bean.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
