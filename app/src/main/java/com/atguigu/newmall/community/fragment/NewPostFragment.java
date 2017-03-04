package com.atguigu.newmall.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.newmall.R;
import com.atguigu.newmall.base.BaseFragment;
import com.atguigu.newmall.community.adapter.NewPostListViewAdapter;
import com.atguigu.newmall.community.bean.NewPostBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.atguigu.newmall.utils.Constants.NEW_POST_URL;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/4 0004.
 * 功能:
 */

public class NewPostFragment extends BaseFragment {

    @BindView(R.id.lv_new_post)
    ListView lvNewPost;
    private List<NewPostBean.ResultEntity> mResult;

    private NewPostListViewAdapter adapter;
    /**
     * 初始新帖视图
     *
     * @return
     */
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_new, null);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 初始化个人数据
     */
    @Override
    protected void initData() {
        Log.e("TAG", "UserFragment initData()++新帖加载");

        //联网获取数据
        getDataFromNet();
    }

    public void getDataFromNet() {
        OkHttpUtils.get()
                .url(NEW_POST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "新帖页面解析失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "新帖页面解析成功" );

                        //解析数据
                        disposeData(response);
                    }
                });

    }

    private void disposeData(String json) {
        NewPostBean newPostBean = JSON.parseObject(json, NewPostBean.class);
        mResult = newPostBean.getResult();

        if(mResult.size()>0 && mResult != null) {
            adapter = new NewPostListViewAdapter(mContext,mResult);

            lvNewPost.setAdapter(adapter);
        }
    }
}
