package com.atguigu.newmall.home.adapger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.newmall.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/6 0006.
 * 功能:
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private final ArrayList<String> group;
    private final ArrayList<List<String>> child;
    private final Context context;
    private int childP;
    private int groupP;

    public ExpandableListViewAdapter(Context context, ArrayList<String> group, ArrayList<List<String>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }

    /**
     * 返回多少个组
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return group.size();
    }

    /**
     * 得到某组的孩子的长度
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    /**
     * 得到具体孩子
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.group_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        //设置组名
        holder.textView.setText(group.get(groupPosition));
        holder.textView.setPadding(0, 10, 0, 10);


        //设置展开和非展开的状态

        if (isExpanded) {
            holder.imageView.setImageResource(R.drawable.filter_list_selected);
        } else {
            holder.imageView.setImageResource(R.drawable.filter_list_unselected);
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_list_item, null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        //设置文本
        if (groupPosition != 0) {
            holder.textView.setText(child.get(groupPosition).get(childPosition));
        }


        //被点击的孩子
        if (childP == childPosition && groupP == groupPosition) {
            //把被点击的孩子的图片-显示
            holder.childImageView.setVisibility(View.VISIBLE);
            //            notifyDataSetChanged();
        } else {
            //把被点击的孩子的图片-隐藏
            holder.childImageView.setVisibility(View.GONE);
            //            notifyDataSetChanged();
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        //接收位置
        childP = childPosition;
        groupP = groupPosition;
        return true;
    }


    static class ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textView)
        TextView textView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.childImageView)
        ImageView childImageView;
        @BindView(R.id.ll_child_root)
        LinearLayout llChildRoot;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
