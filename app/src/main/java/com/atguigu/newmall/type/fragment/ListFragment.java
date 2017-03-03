package com.atguigu.newmall.type.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.newmall.R;
import com.atguigu.newmall.base.BaseFragment;
import com.atguigu.newmall.type.adapter.TypeLeftAdapter;
import com.atguigu.newmall.type.bean.TypeBean;
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

public class ListFragment extends BaseFragment {
    @BindView(R.id.lv_left)
    ListView lvListview;
    @BindView(R.id.rv_right)
    RecyclerView recyclerviewType;
    private TypeLeftAdapter mLeftAdapter;

    //网络请求得到数据
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};
    //联网的url的集合
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};
    private List<TypeBean.ResultEntity> mResult;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        Log.e("TAG", "list碎片视图加载成功");
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {

        mLeftAdapter = new TypeLeftAdapter(mContext, titles);

        lvListview.setAdapter(mLeftAdapter);


        lvListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                //传入被点击的位置
                mLeftAdapter.changeSelected(position);

                //刷新适配器
                mLeftAdapter.notifyDataSetChanged();
            }
        });
        //联网请求
        getDataFromNet(urls[0]);
    }

    private void getDataFromNet(final String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "list分页请求失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "list分页请求成功");
                        processData(response);
                    }


                });
        //TypeBean typeBean = JSON.parseObject(url, TypeBean.class);
        // List<TypeBean.ResultEntity> result = typeBean.getResult();
    }

    private void processData(String url) {
        TypeBean typeBean = JSON.parseObject(url, TypeBean.class);
        mResult = typeBean.getResult();
        Log.e("TAG", "点击的" + mResult.get(0).getName());
    }

}
