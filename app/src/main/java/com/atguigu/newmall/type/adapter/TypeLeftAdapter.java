package com.atguigu.newmall.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.newmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/3 0003.
 * 功能:
 */

public class TypeLeftAdapter extends BaseAdapter {
    private final Context context;
    private final String[] datas;
    /**
     * 点击的位置
     */
    private int clickPosition;

    public TypeLeftAdapter(Context context, String[] titles) {
        this.context = context;
        this.datas = titles;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int position) {
        return datas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.type_left_adapter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(datas[position]);

        if (clickPosition == position) {
            //是当前点击的位置
            viewHolder.tvName.setBackgroundResource(R.drawable.type_item_background_selector);
            viewHolder.tvName.setTextColor(Color.parseColor("#FF0000"));
        } else {
            //不是点击的位置
            viewHolder.tvName.setTextColor(Color.parseColor("#000000"));
            viewHolder.tvName.setBackgroundResource(R.drawable.bg2);
        }
        return convertView;
    }

    public void changeSelected(int position) {
        this.clickPosition = position;
    }

    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
