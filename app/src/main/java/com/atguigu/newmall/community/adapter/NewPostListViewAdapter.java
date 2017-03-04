package com.atguigu.newmall.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.community.bean.NewPostBean;
import com.atguigu.newmall.utils.Constants;
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

public class NewPostListViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<NewPostBean.ResultEntity> datas;

    public NewPostListViewAdapter(Context context, List<NewPostBean.ResultEntity> result) {
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
            convertView = View.inflate(context, R.layout.item_listview_newpost, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewPostBean.ResultEntity resultEntity = datas.get(position);
        //设置图像
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + resultEntity.getAvatar())
                .into(viewHolder.ibNewPostAvatar);

        //设置名字
        viewHolder.tvCommunityUsername.setText(resultEntity.getUsername());

        //设置图像
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + resultEntity.getFigure())
                .into(viewHolder.ivCommunityFigure);

        viewHolder.tvCommunityAddtime.setText(resultEntity.getAdd_time());
        viewHolder.tvCommunityComments.setText(resultEntity.getComments());
        viewHolder.tvCommunityLikes.setText(resultEntity.getIs_like());
        viewHolder.tvCommunitySaying.setText(resultEntity.getSaying());


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_community_username)
        TextView tvCommunityUsername;
        @BindView(R.id.tv_community_addtime)
        TextView tvCommunityAddtime;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.ib_new_post_avatar)
        ImageButton ibNewPostAvatar;
        @BindView(R.id.iv_community_figure)
        ImageView ivCommunityFigure;
        @BindView(R.id.tv_community_saying)
        TextView tvCommunitySaying;
        @BindView(R.id.tv_community_likes)
        TextView tvCommunityLikes;
        @BindView(R.id.tv_community_comments)
        TextView tvCommunityComments;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
