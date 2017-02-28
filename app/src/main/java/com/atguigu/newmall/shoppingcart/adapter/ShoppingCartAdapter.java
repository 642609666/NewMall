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
import com.atguigu.newmall.shoppingcart.view.AddSubView;
import com.atguigu.newmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/28 0028.
 * 功能:
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHoler> {

    private final Context context;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;


    public ShoppingCartAdapter(Context context, List<GoodsBean> list, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.context = context;
        this.datas = list;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;
        //显示价格
        showTotalPrice();
    }

    /**
     * 显示总价格
     */
    public void showTotalPrice() {
        tvShopcartTotal.setText("合计:" + getTotalPrice());
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

    /**
     * 效验是否全选
     */
    public void checkAll() {
        if (datas.size() > 0 && datas != null) {

            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isChecked()) {
                    //只要有一个不勾选
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);
                } else {
                    //勾选
                    number++;
                }

            }
            if (datas.size() == number) {
                //如果是全选
                checkboxAll.setChecked(true);
                checkboxDeleteAll.setChecked(true);
            }
        } else {
            //没有数据的时候默认
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    /**
     * 效验全选按钮的逻辑
     *
     * @param isChecked
     */
    public void checkAll_none(boolean isChecked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //设置是否勾选状态
                goodsBean.setChecked(isChecked);
                checkboxAll.setChecked(isChecked);
                checkboxDeleteAll.setChecked(isChecked);

                //更新视图
                notifyItemChanged(i);
            }
        }
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {
        //1.先得到数据
        GoodsBean goodsBean = datas.get(position);
        //2.绑定数据
        holder.cbGov.setChecked(goodsBean.isChecked());
        //图片
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + datas.get(position).getFigure())
                .into(holder.ivGov);
        //设置价格
        holder.tvPriceGov.setText("￥" + goodsBean.getCover_price());
        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());

        //设置库存,来自服务器
        holder.addSubView.setMinValue(1);
        holder.addSubView.setMaxValue(100);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 计算价格
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        if (datas.size() > 0 && datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isChecked()) {
                    totalPrice += Double.parseDouble(goodsBean.getCover_price()) * goodsBean.getNumber();
                }
            }
        }
        return totalPrice;
    }

    class MyViewHoler extends RecyclerView.ViewHolder {
        CheckBox cbGov;
        ImageView ivGov;
        TextView tvDescGov;
        TextView tvPriceGov;
        AddSubView addSubView;

        public MyViewHoler(final View itemView) {
            super(itemView);
            cbGov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            ivGov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tvDescGov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tvPriceGov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            addSubView = (AddSubView) itemView.findViewById(R.id.addSubView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        //得到布局的位置
                        itemClickListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    //回调点击事件的监听
    private OnItemClickListener itemClickListener;

    /**
     * 点击item的监听
     */
    public interface OnItemClickListener {
        public void onItemClickListener(View view, int position);
    }

    /**
     * 设置item的监听
     *
     * @param l
     */
    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }
}
