package com.atguigu.newmall.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.home.bean.GoodsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/28 0028.
 * 功能:
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHoler> {

    private final Context context;
    private final List<GoodsBean> datas;


    public ShoppingCartAdapter(Context context, List<GoodsBean> list) {
        this.context = context;
        this.datas = list;
    }

    /**
     * 创建视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHoler(View.inflate(context, R.layout.item_shop_cart, null));

    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageView ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;

        public MyViewHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
