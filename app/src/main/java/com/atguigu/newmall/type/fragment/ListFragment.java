package com.atguigu.newmall.type.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atguigu.newmall.R;
import com.atguigu.newmall.base.BaseFragment;
import com.atguigu.newmall.type.adapter.TypeLeftAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/3 0003.
 * 功能:
 */

public class ListFragment extends BaseFragment {
    @BindView(R.id.lv_left)
    ListView lvListview;
    @BindView(R.id.rv_right)
    RecyclerView recyclerviewType;
    private TypeLeftAdapter mLeftAdapter;

    //网络请求得到数据
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        Log.e("TAG", "list碎片视图加载成功");
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {

        mLeftAdapter = new TypeLeftAdapter(mContext, titles);

        lvListview.setAdapter(mLeftAdapter);


        lvListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                //传入被点击的位置
                mLeftAdapter.changeSelected(position);
                //刷新适配器
                mLeftAdapter.notifyDataSetChanged();
            }
        });

    }
}
