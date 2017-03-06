package com.atguigu.newmall.home.adapger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
    @BindView(R.id.ib_drawer_layout_back)
    ImageButton ibDrawerLayoutBack;
    @BindView(R.id.tv_ib_drawer_layout_title)
    TextView tvIbDrawerLayoutTitle;
    @BindView(R.id.ib_drawer_layout_confirm)
    TextView ibDrawerLayoutConfirm;
    @BindView(R.id.rb_select_hot)
    RadioButton rbSelectHot;
    @BindView(R.id.rb_select_new)
    RadioButton rbSelectNew;
    @BindView(R.id.rg_select)
    RadioGroup rgSelect;
    @BindView(R.id.tv_drawer_price)
    TextView tvDrawerPrice;
    @BindView(R.id.tv_drawer_recommend)
    TextView tvDrawerRecommend;
    @BindView(R.id.rl_select_recommend_theme)
    RelativeLayout rlSelectRecommendTheme;
    @BindView(R.id.tv_drawer_type)
    TextView tvDrawerType;
    @BindView(R.id.rl_select_type)
    RelativeLayout rlSelectType;
    @BindView(R.id.btn_select_all)
    Button btnSelectAll;
    @BindView(R.id.ll_select_root)
    LinearLayout llSelectRoot;
    @BindView(R.id.btn_drawer_layout_cancel)
    Button btnDrawerLayoutCancel;
    @BindView(R.id.btn_drawer_layout_confirm)
    Button btnDrawerLayoutConfirm;
    @BindView(R.id.iv_price_no_limit)
    ImageView ivPriceNoLimit;
    @BindView(R.id.rl_price_nolimit)
    RelativeLayout rlPriceNolimit;
    @BindView(R.id.iv_price_0_15)
    ImageView ivPrice015;
    @BindView(R.id.rl_price_0_15)
    RelativeLayout rlPrice015;
    @BindView(R.id.iv_price_15_30)
    ImageView ivPrice1530;
    @BindView(R.id.rl_price_15_30)
    RelativeLayout rlPrice1530;
    @BindView(R.id.iv_price_30_50)
    ImageView ivPrice3050;
    @BindView(R.id.rl_price_30_50)
    RelativeLayout rlPrice3050;
    @BindView(R.id.iv_price_50_70)
    ImageView ivPrice5070;
    @BindView(R.id.rl_price_50_70)
    RelativeLayout rlPrice5070;
    @BindView(R.id.iv_price_70_100)
    ImageView ivPrice70100;
    @BindView(R.id.rl_price_70_100)
    RelativeLayout rlPrice70100;
    @BindView(R.id.iv_price_100)
    ImageView ivPrice100;
    @BindView(R.id.rl_price_100)
    RelativeLayout rlPrice100;
    @BindView(R.id.et_price_start)
    EditText etPriceStart;
    @BindView(R.id.v_price_line)
    View vPriceLine;
    @BindView(R.id.et_price_end)
    EditText etPriceEnd;
    @BindView(R.id.rl_select_price)
    RelativeLayout rlSelectPrice;
    @BindView(R.id.ll_price_root)
    LinearLayout llPriceRoot;
    @BindView(R.id.btn_drawer_theme_cancel)
    Button btnDrawerThemeCancel;
    @BindView(R.id.btn_drawer_theme_confirm)
    Button btnDrawerThemeConfirm;
    @BindView(R.id.iv_theme_all)
    ImageView ivThemeAll;
    @BindView(R.id.rl_theme_all)
    RelativeLayout rlThemeAll;
    @BindView(R.id.iv_theme_note)
    ImageView ivThemeNote;
    @BindView(R.id.rl_theme_note)
    RelativeLayout rlThemeNote;
    @BindView(R.id.iv_theme_funko)
    ImageView ivThemeFunko;
    @BindView(R.id.rl_theme_funko)
    RelativeLayout rlThemeFunko;
    @BindView(R.id.iv_theme_gsc)
    ImageView ivThemeGsc;
    @BindView(R.id.rl_theme_gsc)
    RelativeLayout rlThemeGsc;
    @BindView(R.id.iv_theme_origin)
    ImageView ivThemeOrigin;
    @BindView(R.id.rl_theme_origin)
    RelativeLayout rlThemeOrigin;
    @BindView(R.id.iv_theme_sword)
    ImageView ivThemeSword;
    @BindView(R.id.rl_theme_sword)
    RelativeLayout rlThemeSword;
    @BindView(R.id.iv_theme_food)
    ImageView ivThemeFood;
    @BindView(R.id.rl_theme_food)
    RelativeLayout rlThemeFood;
    @BindView(R.id.iv_theme_moon)
    ImageView ivThemeMoon;
    @BindView(R.id.rl_theme_moon)
    RelativeLayout rlThemeMoon;
    @BindView(R.id.iv_theme_quanzhi)
    ImageView ivThemeQuanzhi;
    @BindView(R.id.rl_theme_quanzhi)
    RelativeLayout rlThemeQuanzhi;
    @BindView(R.id.iv_theme_gress)
    ImageView ivThemeGress;
    @BindView(R.id.rl_theme_gress)
    RelativeLayout rlThemeGress;
    @BindView(R.id.ll_theme_root)
    LinearLayout llThemeRoot;
    @BindView(R.id.btn_drawer_type_cancel)
    Button btnDrawerTypeCancel;
    @BindView(R.id.btn_drawer_type_confirm)
    Button btnDrawerTypeConfirm;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.ll_type_root)
    LinearLayout llTypeRoot;
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

    @OnClick({R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select, R.id.ib_drawer_layout_back, R.id.ib_drawer_layout_confirm, R.id.rl_select_price, R.id.rl_select_recommend_theme, R.id.rl_select_type, R.id.btn_drawer_layout_cancel, R.id.btn_drawer_layout_confirm, R.id.btn_drawer_theme_cancel,
            R.id.btn_drawer_theme_confirm, R.id.btn_drawer_type_cancel, R.id.btn_drawer_type_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back://返回
                finish();
                break;
            case R.id.tv_goods_list_search://搜索
                Toast.makeText(GoodsListActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_goods_list_home://主页
                Toast.makeText(GoodsListActivity.this, "主页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_sort://综合排序
//                Toast.makeText(GoodsListActivity.this, "综合排序", Toast.LENGTH_SHORT).show();

                click_count = 0;

                //设置综合排序高亮显示
                tvGoodsListSort.setTextColor(Color.parseColor("#ed4141"));
                //价格设置默认
                tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
                //筛选设置默认
                tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
                break;
            case R.id.tv_goods_list_price://价格
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
            case R.id.tv_goods_list_select://涮选

//                Toast.makeText(GoodsListActivity.this, "涮选", Toast.LENGTH_SHORT).show();

                click_count = 0;

                //设置综合排序高亮显示
                tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
                //价格设置默认
                tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
                //筛选设置默认
                tvGoodsListSelect.setTextColor(Color.parseColor("#ed4141"));

                //打开Drawlayout 右侧菜单
                dlLeft.openDrawer(Gravity.RIGHT);


                break;
            case R.id.ib_drawer_layout_back://取消

                //关闭DrawLayout
                dlLeft.closeDrawers();

                break;
            case R.id.ib_drawer_layout_confirm://涮选---确定
                Toast.makeText(this, "筛选-确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_select_price://显示-价格
                llPriceRoot.setVisibility(View.VISIBLE);
                showPriceLayout();

                break;
            case R.id.rl_select_recommend_theme://主题
                llThemeRoot.setVisibility(View.VISIBLE);
                showThemeLayout();
                break;
            case R.id.rl_select_type://类别
                llTypeRoot.setVisibility(View.VISIBLE);
                showTypeLayout();
                break;
            case R.id.btn_drawer_layout_cancel:
                llSelectRoot.setVisibility(View.VISIBLE);
                showSelectorLayout();
                break;
            case R.id.btn_drawer_layout_confirm:
                Toast.makeText(this, "价格-确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_drawer_theme_cancel:
                llSelectRoot.setVisibility(View.VISIBLE);
                showSelectorLayout();
                break;
            case R.id.btn_drawer_theme_confirm:
                Toast.makeText(this, "主题-确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_drawer_type_cancel:
                llSelectRoot.setVisibility(View.VISIBLE);
                showSelectorLayout();
                break;
            case R.id.btn_drawer_type_confirm:
                Toast.makeText(this, "类别-确定", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showSelectorLayout() {
        llPriceRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
    }

    private void showTypeLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llPriceRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
    }

    private void showThemeLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llPriceRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
    }

    private void showPriceLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
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

        //默认显示涮选页面
        showSelectorLayout();
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
