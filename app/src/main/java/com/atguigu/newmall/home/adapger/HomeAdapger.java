package com.atguigu.newmall.home.adapger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.newmall.R;
import com.atguigu.newmall.home.MyGridView;
import com.atguigu.newmall.home.bean.GoodsBean;
import com.atguigu.newmall.home.bean.HomeBean;
import com.atguigu.newmall.home.bean.WebViewBean;
import com.atguigu.newmall.utils.Constants;
import com.atguigu.newmall.utils.DensityUtil;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

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
    public static final String GOOD_BEAN = "good_bean";
    public static final String WEBVIEW_BEAN = "webview_bean";
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

    private BannerViewHolder mBannerViewHolder;

    public HomeAdapger(Context context, HomeBean.ResultBean result) {
        this.mContext = context;
        this.mResult = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return 6;
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
                View view1 = inflater.inflate(R.layout.channel_item, null);
                return new ChannelViewHolder(mContext, view1);
            case ACT:
                View view2 = inflater.inflate(R.layout.act_viewpager, null);
                return new ActViewHolder(mContext, view2);
            case SECKILL:
                View view3 = inflater.inflate(R.layout.seckill_item, null);
                return new SeckillViewHolder(mContext, view3);
            case RECOMMEND:
                View view4 = inflater.inflate(R.layout.recommend_item, null);
                return new RecommendViewHolder(mContext, view4);
            case HOT:
                View view5 = inflater.inflate(R.layout.hot_item, null);
                return new HotViewHolder(mContext, view5);
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
                BannerViewHolder mBannerViewHolder = (BannerViewHolder) holder;
                //绑定数据
                mBannerViewHolder.setData(mResult.getBanner_info());
                break;
            case CHANNEL:
                ChannelViewHolder viewHolder = (ChannelViewHolder) holder;
                //绑定数据
                viewHolder.setData(mResult.getChannel_info());
                break;
            case ACT:
                ActViewHolder actViewHolder = (ActViewHolder) holder;
                //绑定数据
                actViewHolder.setData(mResult.getAct_info());
                break;
            case SECKILL:
                SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
                //绑定数据
                seckillViewHolder.setData(mResult.getSeckill_info());
                break;
            case RECOMMEND:
                RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
                recommendViewHolder.setData(mResult.getRecommend_info());
                break;
            case HOT:
                HotViewHolder hotViewHolder = (HotViewHolder) holder;
                hotViewHolder.setData(mResult.getHot_info());
                break;
        }
    }

    /**
     * 广告条
     */
    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            //1.得到数据
            //2.设置Banner的数据
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }

            //简单使用
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    })
                    .start();

            //设置样式
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            //3.设置Banner的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                                           @Override
                                           public void OnBannerClick(int position) {
                                               int realPostion = position;
                                               if (realPostion < banner_info.size()) {
                                                   String product_id = "";
                                                   String name = "";
                                                   String cover_price = "";
                                                   String image = "";
                                                   if (realPostion == 0) {
                                                       product_id = "627";
                                                       cover_price = "32.00";
                                                       name = "剑三T恤批发";
                                                   } else if (realPostion == 1) {
                                                       product_id = "21";
                                                       cover_price = "8.00";
                                                       name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                                                   } else {
                                                       product_id = "1341";
                                                       cover_price = "50.00";
                                                       name = "【蓝诺】《天下吾双》 剑网3同人本";
                                                   }
                                                   image = banner_info.get(position).getImage();
                                                   GoodsBean goodsBean = new GoodsBean();
                                                   goodsBean.setProduct_id(product_id);
                                                   goodsBean.setCover_price(cover_price);
                                                   goodsBean.setFigure(image);
                                                   goodsBean.setName(name);

                                                   Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                                                   intent.putExtra(GOOD_BEAN, goodsBean);
                                                   mContext.startActivity(intent);
                                               }
                                           }
                                       }
            );
        }
    }

    /**
     * gridview 效果
     */
    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private ChannelAdapger channelAdapter;
        private GridView mGridView;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            mGridView = (GridView) itemView.findViewById(R.id.gv_channel);
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            //设置GridView的适配器
            channelAdapter = new ChannelAdapger(mContext, channel_info);
            mGridView.setAdapter(channelAdapter);

            //设置item的点击事件
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    /**
     * viewpager图片滑动
     */
    private class ActViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private ViewPager viewpagerHome;
        ActPagerAdapter adapter;

        public ActViewHolder(Context context, View view2) {
            super(view2);
            ButterKnife.bind(this, view2);
            this.context = context;
            viewpagerHome = (ViewPager) view2.findViewById(R.id.viewpager_home);
        }

        public void setData(final List<HomeBean.ResultBean.ActInfoBean> data) {

            adapter = new ActPagerAdapter(mContext, data);

            //美化ViewPager库
            viewpagerHome.setPageMargin(DensityUtil.dip2px(mContext, 20));//设置page间间距，自行根据需求设置
            viewpagerHome.setOffscreenPageLimit(3);//>=3
            viewpagerHome.setAdapter(adapter);
            //setPageTransformer 决定动画效果
            viewpagerHome.setPageTransformer(true, new
                    RotateYTransformer());

            //设置点击事件

            adapter.setMyActPagerInterface(new ActPagerAdapter.MyActPagerInterface() {
                @Override
                public void OnItemClickListener(View v, int position) {
                    HomeBean.ResultBean.ActInfoBean actInfoBean = data.get(position);

                    WebViewBean webViewBean = new WebViewBean();
                    webViewBean.setName(actInfoBean.getName());
                    webViewBean.setUrl(actInfoBean.getUrl());
                    webViewBean.setIcon_url(actInfoBean.getIcon_url());
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra(WEBVIEW_BEAN,webViewBean);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    /**
     * 秒杀热卖
     */
    private class SeckillViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private CountdownView countdownview;

        TextView tvMoreSeckill;

        RecyclerView rvSeckill;

        SeckillRecyclerViewAdapter mAdapter;

        public SeckillViewHolder(Context context, View view3) {
            super(view3);
            countdownview = (CountdownView) view3.findViewById(R.id.countdownview);
            tvMoreSeckill = (TextView) view3.findViewById(R.id.tv_more_seckill);
            rvSeckill = (RecyclerView) view3.findViewById(R.id.rv_seckill);

            this.context = context;

        }

        public void setData(HomeBean.ResultBean.SeckillInfoBean data) {
            mAdapter = new SeckillRecyclerViewAdapter(context, data);

            rvSeckill.setAdapter(mAdapter);

            //设置布局管理器
            rvSeckill.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


            //设置秒杀时间

            countdownview.setTag("test1");
            long duration = Long.parseLong(data.getEnd_time()) - Long.parseLong(data.getStart_time());
            countdownview.start(duration);

            //设置点击事件

            mAdapter.setMySeckillInterface(new SeckillRecyclerViewAdapter.MySeckillInterface() {
                @Override
                public void OnClickListener(SeckillRecyclerViewAdapter.ViewHolder v, int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 推荐页面
     */
    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        TextView tvMoreRecommend;
        GridView gvRecommend;
        RecommendAdapter mAdapter;

        public RecommendViewHolder(Context context, View view4) {
            super(view4);
            tvMoreRecommend = (TextView) view4.findViewById(R.id.tv_more_recommend);
            gvRecommend = (GridView) view4.findViewById(R.id.gv_recommend);
            this.context = context;
        }

        public void setData(final List<HomeBean.ResultBean.RecommendInfoBean> data) {
            mAdapter = new RecommendAdapter(mContext, data);
            gvRecommend.setAdapter(mAdapter);

            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //随带数据
                    GoodsBean infoBean = new GoodsBean();
                    HomeBean.ResultBean.RecommendInfoBean bean = data.get(position);

                    infoBean.setName(bean.getName());
                    infoBean.setCover_price(bean.getCover_price());
                    infoBean.setFigure(bean.getFigure());
                    infoBean.setProduct_id(bean.getProduct_id());
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    //Toast.makeText(mContext, ""+infoBean.toString(), Toast.LENGTH_SHORT).show();
                    intent.putExtra(GOOD_BEAN, infoBean);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    /**
     * 热卖
     */
    private class HotViewHolder extends RecyclerView.ViewHolder {
        TextView tvMoreHot;
        MyGridView gvHot;
        HotAdapter mAdapter;

        public HotViewHolder(Context context, View view5) {
            super(view5);
            tvMoreHot = (TextView) view5.findViewById(R.id.tv_more_hot);
            gvHot = (MyGridView) view5.findViewById(R.id.gv_hot);
        }

        public void setData(List<HomeBean.ResultBean.HotInfoBean> data) {
            mAdapter = new HotAdapter(mContext, data);
            gvHot.setAdapter(mAdapter);

            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
