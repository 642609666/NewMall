package com.atguigu.newmall.home.adapger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.newmall.R;
import com.atguigu.newmall.home.bean.GoodsBean;
import com.atguigu.newmall.home.bean.TypeListBean;
import com.atguigu.newmall.home.view.SpaceItemDecoration;
import com.atguigu.newmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class GoodsListActivity extends AppCompatActivity {

    @BindView(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @BindView(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @BindView(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @BindView(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @BindView(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @BindView(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @BindView(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @BindView(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @BindView(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.dl_left)
    DrawerLayout dlLeft;

    private GoodsListAdapter adapter;

    private int position;

    private int click_count;
    /**
     * 请求网络
     */
    private String[] urls = new String[]{
            Constants.CLOSE_STORE,
            Constants.GAME_STORE,
            Constants.COMIC_STORE,
            Constants.COSPLAY_STORE,
            Constants.GUFENG_STORE,
            Constants.STICK_STORE,
            Constants.WENJU_STORE,
            Constants.FOOD_STORE,
            Constants.SHOUSHI_STORE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
        getData();
    }

    @OnClick({R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price
            , R.id.tv_goods_list_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.tv_goods_list_search:
                Toast.makeText(GoodsListActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_goods_list_home:
                Toast.makeText(GoodsListActivity.this, "主页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_sort:
//                Toast.makeText(GoodsListActivity.this, "综合排序", Toast.LENGTH_SHORT).show();

                click_count = 0;

                //设置综合排序高亮显示
                tvGoodsListSort.setTextColor(Color.parseColor("#ed4141"));
                //价格设置默认
                tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
                //筛选设置默认
                tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
                break;
            case R.id.tv_goods_list_price:
//                Toast.makeText(GoodsListActivity.this, "价格", Toast.LENGTH_SHORT).show();


                click_count++;

                if (click_count % 2 == 1) {
                    //箭头向下红
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_desc);
                } else {
                    //箭头向上红
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_asc);
                }

                //设置综合排序高亮显示
                tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
                //价格设置默认
                tvGoodsListPrice.setTextColor(Color.parseColor("#ed4141"));
                //筛选设置默认
                tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
                break;
            case R.id.tv_goods_list_select:

//                Toast.makeText(GoodsListActivity.this, "涮选", Toast.LENGTH_SHORT).show();

                click_count = 0;

                //设置综合排序高亮显示
                tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
                //价格设置默认
                tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
                //筛选设置默认
                tvGoodsListSelect.setTextColor(Color.parseColor("#ed4141"));
                break;
        }
    }

    public void getData() {
        position = getIntent().getIntExtra("position", 0);

        getDataFromNet(urls[position]);

        initView();
    }

    private void initView() {
        //设置综合排序高亮显示
        tvGoodsListSort.setTextColor(Color.parseColor("#ed4141"));
        //价格设置默认
        tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
        //筛选设置默认
        tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "分类联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "分类联网成功");
                        processData(response);
                    }
                });
    }

    private void processData(String response) {
        TypeListBean typeListBean = JSON.parseObject(response, TypeListBean.class);

        List<TypeListBean.ResultBean.PageDataBean> datas =
                typeListBean.getResult().getPage_data();

        if (datas.size() > 0 && datas != null) {
            adapter = new GoodsListAdapter(GoodsListActivity.this, datas);

            recyclerview.setAdapter(adapter);

            //设置布局管理器

            recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

            //添加物品装饰
            recyclerview.addItemDecoration(new SpaceItemDecoration(10));

            adapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(TypeListBean.ResultBean.PageDataBean data) {
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(data.getProduct_id());
                    goodsBean.setName(data.getName());
                    goodsBean.setCover_price(data.getCover_price());
                    goodsBean.setFigure(data.getFigure());

                    Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                    intent.putExtra(HomeAdapger.GOOD_BEAN, goodsBean);
                    startActivity(intent);
                }
            });
        }
    }
}
