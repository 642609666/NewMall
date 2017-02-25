package com.atguigu.newmall.home.adapger;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.home.bean.HomeBean;
import com.atguigu.newmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/25 0025.
 * 功能:
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;


    public SeckillRecyclerViewAdapter(Context context, HomeBean.ResultBean.SeckillInfoBean data) {
        this.context = context;
        this.datas = data.getList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //根据位置得到相应数据
        HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);

        holder.tvCoverPrice.setText(listBean.getCover_price());
        holder.tvOriginPrice.setText(listBean.getOrigin_price());

        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + listBean.getFigure())
                .into(holder.ivFigure);
        holder.ivFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMySeckillInterface!= null) {
                    mMySeckillInterface.OnClickListener(holder,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFigure;

        TextView tvCoverPrice;

        TextView tvOriginPrice;

        LinearLayout llRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCoverPrice = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tvOriginPrice = (TextView) itemView.findViewById(R.id.tv_origin_price);
            ivFigure = (ImageView) itemView.findViewById(R.id.iv_figure);
        }
    }

    /**
     * 回调接口
     */
    public interface MySeckillInterface{
        void OnClickListener(ViewHolder v, int position);
    }
    private MySeckillInterface mMySeckillInterface;

    public void setMySeckillInterface(MySeckillInterface mySeckillInterface) {
        mMySeckillInterface = mySeckillInterface;
    }
}
