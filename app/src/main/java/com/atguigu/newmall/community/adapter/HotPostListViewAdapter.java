package com.atguigu.newmall.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.community.bean.HotPostBean;
import com.atguigu.newmall.utils.Constants;
import com.atguigu.newmall.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/4 0004.
 * 功能:
 */
public class HotPostListViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<HotPostBean.ResultEntity> datas;

    public HotPostListViewAdapter(Context context, List<HotPostBean.ResultEntity> result) {
        this.context = context;
        this.datas = result;
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
            convertView = View.inflate(context, R.layout.item_hotpost_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HotPostBean.ResultEntity bean = datas.get(position);
        //设置头像
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + bean.getAvatar())
                .into(viewHolder.ivNewPostAvatar);
        viewHolder.tvHotUsername.setText(bean.getUsername());
        //图像
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + bean.getFigure())
                .into(viewHolder.ivHotFigure);

        viewHolder.tvHotSaying.setText(bean.getSaying());
        viewHolder.tvHotLikes.setText(bean.getLikes());

        viewHolder.tvHotComments.setText(bean.getComments());

//设置置顶
        String is_top = bean.getIs_top();
        if ("1".equals(is_top)) {

            //显示置顶
            TextView hotTextView = new TextView(context);
            hotTextView.setText("置顶");
            hotTextView.setGravity(Gravity.CENTER);
            //白色
            hotTextView.setTextColor(Color.WHITE);

            //设置背景
            hotTextView.setBackgroundResource(R.drawable.is_top_shape);
            //padding都是5
            hotTextView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            //先移除
            viewHolder.llHotPost.removeAllViews();


            //参数
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLp.setMargins(DensityUtil.dip2px(context, 5), 0, 0, 0);

            viewHolder.llHotPost.addView(hotTextView, textViewLp);

        }
        //热门
        String is_hot = bean.getIs_hot();
        if ("1".equals(is_hot)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(context);
            textViewLp.setMargins(DensityUtil.dip2px(context, 5), 0, DensityUtil.dip2px(context, 5), 0);
            textView.setText("热门");
            textView.setGravity(Gravity.CENTER);
            //文字为白色
            textView.setTextColor(Color.WHITE);
            //设置padding
            textView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            //设置橙色北京
            textView.setBackgroundResource(R.drawable.is_hot_shape);
            viewHolder.llHotPost.addView(textView, textViewLp);
        }


        //精华
        String is_essence = bean.getIs_essence();
        if ("1".equals(is_essence)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //距离右边
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(context, 5), 0);
            TextView textView = new TextView(context);
            textView.setText("精华");
            textView.setGravity(Gravity.CENTER);
            //文字白色
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            //设置背景为亮橙色
            textView.setBackgroundResource(R.drawable.is_essence_shape);
            viewHolder.llHotPost.addView(textView, textViewLp);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_hot_username)
        TextView tvHotUsername;
        @BindView(R.id.tv_hot_addtime)
        TextView tvHotAddtime;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.iv_new_post_avatar)
        ImageView ivNewPostAvatar;
        @BindView(R.id.iv_hot_figure)
        ImageView ivHotFigure;
        @BindView(R.id.ll_hot_post)
        LinearLayout llHotPost;
        @BindView(R.id.tv_hot_saying)
        TextView tvHotSaying;
        @BindView(R.id.tv_hot_likes)
        TextView tvHotLikes;
        @BindView(R.id.tv_hot_comments)
        TextView tvHotComments;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
