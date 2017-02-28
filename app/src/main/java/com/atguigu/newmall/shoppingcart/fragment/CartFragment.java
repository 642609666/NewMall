package com.atguigu.newmall.shoppingcart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.newmall.R;
import com.atguigu.newmall.base.BaseFragment;
import com.atguigu.newmall.home.bean.GoodsBean;
import com.atguigu.newmall.shoppingcart.adapter.ShoppingCartAdapter;
import com.atguigu.newmall.utils.CartStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/22 0022.
 * 功能:购物fragment
 */

public class CartFragment extends BaseFragment {
    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;

    private ShoppingCartAdapter adapter;

    /**
     * Plugin
     * 初始化购物视图
     *
     * @return
     */
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 初始化购物数据
     */
    @Override
    protected void initData() {
        Log.e("TAG", "购物车的数据被初始化了");
        //得到数据
        List<GoodsBean> list = CartStorage.getInstance(mContext).getAllData();

        if (list != null && list.size() > 0) {
            //购物车有数据
            llEmptyShopcart.setVisibility(View.GONE);

            adapter = new ShoppingCartAdapter(mContext, list, tvShopcartTotal, checkboxAll, checkboxDeleteAll);

            //设置view 的适配器
            recyclerview.setAdapter(adapter);

            //布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

            //设置点击事件


        } else {
            //购物车没有
            llEmptyShopcart.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        for (int i = 0; i < CartStorage.getInstance(mContext).getAllData().size(); i++) {
            Log.e("TAG", "" + CartStorage.getInstance(mContext).getAllData().get(i).toString());
        }
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.ll_check_all, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                Toast.makeText(mContext, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
                Toast.makeText(mContext, "全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_check_out:
                Toast.makeText(mContext, "结算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_delete_all:
                Toast.makeText(mContext, "删除全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                Toast.makeText(mContext, "删除按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_collection:
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:
                Toast.makeText(mContext, "去逛逛", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
