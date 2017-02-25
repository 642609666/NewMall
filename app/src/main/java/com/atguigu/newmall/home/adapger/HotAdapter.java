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
 * 功能:
 */

public class HotAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.HotInfoBean> datas;

    public HotAdapter(Context context, List<HomeBean.ResultBean.HotInfoBean> data) {
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
            convertView = View.inflate(context, R.layout.item_hot_adapter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomeBean.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        viewHolder.tvName.setText(hotInfoBean.getName());
        viewHolder.tvPrice.setText(hotInfoBean.getCover_price());

        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + datas.get(position).getFigure())
                .into(viewHolder.ivHot);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView ivHot;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
