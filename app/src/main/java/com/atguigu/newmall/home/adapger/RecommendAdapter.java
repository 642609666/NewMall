package com.atguigu.newmall.home.adapger;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.home.bean.HomeBean;
import com.atguigu.newmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/25 0025.
 * 功能:推荐页面适配器
 */

public class RecommendAdapter extends BaseAdapter {

    private final Context context;
    private final List<HomeBean.ResultBean.RecommendInfoBean> datas;

    public RecommendAdapter(Context context, List<HomeBean.ResultBean.RecommendInfoBean> data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_recommend_grid_view, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);
        viewHolder.tvName.setText(recommendInfoBean.getName());
        viewHolder.tvPrice.setText(recommendInfoBean.getCover_price());
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + datas.get(position).getFigure())
                .into(viewHolder.ivRecommend);
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
