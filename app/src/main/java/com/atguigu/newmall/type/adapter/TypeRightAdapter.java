package com.atguigu.newmall.type.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.newmall.R;
import com.atguigu.newmall.home.adapger.GoodsInfoActivity;
import com.atguigu.newmall.home.bean.GoodsBean;
import com.atguigu.newmall.type.bean.TypeBean;
import com.atguigu.newmall.utils.Constants;
import com.atguigu.newmall.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.atguigu.newmall.home.adapger.HomeAdapger.GOOD_BEAN;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/3 0003.
 * 功能:
 */
public class TypeRightAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<TypeBean.ResultEntity.ChildEntity> mChild;
    private final List<TypeBean.ResultEntity.HotProductListEntity> mHot_product_list;
    /**
     * 热卖推荐
     */
    private static final int HOT = 0;
    /**
     * 常用分类
     */
    private static final int COMMON = 1;
    private int currentType = HOT;
    private LayoutInflater inflater;

    public TypeRightAdapter(Context context, List<TypeBean.ResultEntity> result) {
        this.context = context;
        //常用数据集合
        mChild = result.get(0).getChild();
        //热卖数据集合
        mHot_product_list = result.get(0).getHot_product_list();
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemCount() {
        return 1 + mChild.size();
    }

    /**
     * 根据位置得到不同的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == HOT) {
            currentType = HOT;
        } else if (position == COMMON) {
            currentType = COMMON;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOT) {
            return new HotViewHolder(inflater.inflate(R.layout.item_hot_right, null));
        } else if (viewType == COMMON) {
            return new CommonViewHolder(inflater.inflate(R.layout.item_common_right, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(mHot_product_list);
        } else if (getItemViewType(position) == COMMON) {
            CommonViewHolder viewHolder = (CommonViewHolder) holder;
            int realPostion = position - 1;
            viewHolder.setData(mChild.get(realPostion));
        }

    }

    class CommonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_ordinary_right)
        ImageView ivOrdinaryRight;
        @BindView(R.id.tv_ordinary_right)
        TextView tvOrdinaryRight;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;

        public CommonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setData(final TypeBean.ResultEntity.ChildEntity childEntity) {
            //1.请求图片
            //请求图片
            Glide.with(context)
                    .load(Constants.BASE_URL_IMAGE + childEntity.getPic())
                    .placeholder(R.drawable.new_img_loading_2)
                    .into(ivOrdinaryRight);

            //2.设置文本
            tvOrdinaryRight.setText(childEntity.getName());


            llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "" + childEntity.getName(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_hot_right)
        LinearLayout llHotRight;
        @BindView(R.id.hsl_hot_right)
        HorizontalScrollView hslHotRight;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<TypeBean.ResultEntity.HotProductListEntity> hot_product_list) {

            for (int i = 0; i < hot_product_list.size(); i++) {

                TypeBean.ResultEntity.HotProductListEntity bean = hot_product_list.get(i);

                //外面的线性布局
                LinearLayout layout = new LinearLayout(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
                //设置左右边距
                params.setMargins(DensityUtil.dip2px(context, 5), 0, DensityUtil.dip2px(context, 5), 0);

                layout.setGravity(Gravity.CENTER);//设置布局居中
                layout.setOrientation(LinearLayout.VERTICAL);//设置竖直方向

                //创建图片
                ImageView imageView = new ImageView(context);

                //设置图片宽和高 80dp

                LinearLayout.LayoutParams ivparams = new LinearLayout.LayoutParams(DensityUtil.dip2px(context, 80), DensityUtil.dip2px(context, 80));

                //设置间距
                ivparams.setMargins(0, 0, 0, DensityUtil.dip2px(context, 10));


                //请求图片
                Glide.with(context)
                        .load(Constants.BASE_URL_IMAGE + bean.getFigure())
                        .into(imageView);

                //把图片添加到线性布局
                layout.addView(imageView, ivparams);

                //创建文本
                TextView textView = new TextView(context);
                //文字
                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                textView.setGravity(Gravity.CENTER);

                textView.setTextColor(Color.parseColor("#ed3f3f"));

                textView.setText("￥" + bean.getCover_price());

                //把文本添加到线性布局
                layout.addView(textView);

                //把每个线性布局添加到外部的线性布局中

                llHotRight.addView(layout, params);

                //设置item的点击事件
                layout.setTag(i);

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        String cover_price = hot_product_list.get(position).getCover_price();
                        String name = hot_product_list.get(position).getName();
                        String figure = hot_product_list.get(position).getFigure();
                        String product_id = hot_product_list.get(position).getProduct_id();

                        //创建商品Bean对象
                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setProduct_id(product_id);
                        goodsBean.setFigure(figure);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setName(name);

                        Intent intent = new Intent(context, GoodsInfoActivity.class);
                        intent.putExtra(GOOD_BEAN, goodsBean);
                        context.startActivity(intent);
                    }
                });


            }
        }
    }

}
