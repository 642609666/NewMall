package com.atguigu.newmall.home.adapger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.home.bean.HomeBean;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/23 0023.
 * 功能:
 */

public class HomeAdapger extends RecyclerView.Adapter {
    /**
     * 六种类型
     */
    /**
     * 横幅广告-要从0开
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    //上下文
    private final Context mContext;
    /**
     * json解析后的数据
     */
    private final HomeBean.ResultBean mResult;
    /**
     * 用他来加载布局
     */
    private final LayoutInflater inflater;
    /**
     * 当前类型
     */
    public int currentType = BANNER;

    public HomeAdapger(Context context, HomeBean.ResultBean result) {
        this.mContext = context;
        this.mResult = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 根据位置得到相应的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * 当前对应的类型
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                View view = inflater.inflate(R.layout.banner_viewpager, null);
                return new BannerViewHolder(mContext, view);
            case CHANNEL:
                break;
            case ACT:
                break;
            case SECKILL:
                break;
            case RECOMMEND:
                break;
            case HOT:
                break;
        }
        return null;
    }


    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(mResult.getBanner_info());
                break;
            case CHANNEL:
                break;
            case ACT:
                break;
            case SECKILL:
                break;
            case RECOMMEND:
                break;
            case HOT:
                break;
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private TextView tvTilte;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            tvTilte = (TextView) itemView.findViewById(R.id.tv_tilte);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> data) {
            tvTilte.setText("我是布局");
        }
    }
}
