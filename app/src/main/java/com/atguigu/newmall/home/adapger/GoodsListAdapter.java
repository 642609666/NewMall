package com.atguigu.newmall.home.adapger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.home.bean.TypeListBean;
import com.atguigu.newmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/6 0006.
 * 功能:
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<TypeListBean.ResultBean.PageDataBean> datas;

    private LayoutInflater mInflater;

    public GoodsListAdapter(Context mContext, List<TypeListBean.ResultBean.PageDataBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_goods_list, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //根据位置得到数据
        TypeListBean.ResultBean.PageDataBean bean = datas.get(position);
        //绑定数据
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE + bean.getFigure())
                .placeholder(R.drawable.new_img_loading_1)
                .into(holder.ivHot);

        holder.tvName.setText(bean.getName());
        holder.tvPrice.setText("￥" + bean.getCover_price());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView ivHot;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        //得到布局位置传递过去
                        listener.onItemClick(datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    /**
     * 点击item的接口
     */
    public interface OnItemClickListener {
        public void onItemClick(TypeListBean.ResultBean.PageDataBean data);
    }

    private OnItemClickListener listener;

    /**
     * 设置item的点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
