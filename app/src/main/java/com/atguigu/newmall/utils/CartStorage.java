package com.atguigu.newmall.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.newmall.home.bean.GoodsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/27 0027.
 * 功能:
 */

public class CartStorage {
    public static final String JSON_CART = "json_cart";
    private static CartStorage instace;
    private final Context mContext;
    //SparseArray 替代 HashMap
    private SparseArray<GoodsBean> mSparseArray;
    private List<GoodsBean> mLocalData;

    public CartStorage(Context context) {
        mContext = context;
        mSparseArray = new SparseArray<>();
        //从本地获取数据
        listToSparseArray();
    }

    /**
     * 把list的数据转换成SparseArray
     */
    private void listToSparseArray() {
        List<GoodsBean> beanList = getAllData();
        //循环起来,把数据转存到sparseArray
        for (int i = 0; i < beanList.size(); i++) {
            GoodsBean goodsBean = beanList.get(i);
            mSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }
    }

    /**
     * 得到所有的数据
     *
     * @return
     */
    public List<GoodsBean> getAllData() {

        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     *
     * @return
     */
    public List<GoodsBean> getLocalData() {
        List<GoodsBean> goodsBeen = new ArrayList<>();
        //从本地获取数据
        String json = CacheUtils.getString(mContext, JSON_CART);//保持的json数据
        if (!TextUtils.isEmpty(json)) {
            //把它转换成列表
            goodsBeen = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
            }.getType());

        }
        //把json数据解析成list数据
        return goodsBeen;
    }

    public static CartStorage getInstance(Context context) {
        if (instace == null) {
            //同步锁
            synchronized (CartStorage.class) {
                if (instace == null) {
                    instace = new CartStorage(context);
                }
            }
        }
        return instace;
    }

    /**
     * 添加数据
     *
     * @param goodsBean
     */
    public void addData(GoodsBean goodsBean) {
        //1.数据添加到sparseArray
        GoodsBean tempGoodBean = mSparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        //已经保存过
        if (tempGoodBean != null) {
            tempGoodBean.setNumber(tempGoodBean.getNumber() + goodsBean.getNumber());
        } else {
            //没有添加过
            tempGoodBean = goodsBean;
        }

        //添加到集合中
        mSparseArray.put(Integer.parseInt(tempGoodBean.getProduct_id()), tempGoodBean);

        //2.保存到本地
        saveLocal();
    }

    /**
     * 删除数据
     *
     * @param goodsBean
     */
    public void deleteData(GoodsBean goodsBean) {
        //1.删除数据
        mSparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        //2.保存到本地
        saveLocal();
    }

    /**
     * 修改数据
     *
     * @param goodsBean
     */
    public void updateData(GoodsBean goodsBean) {
        //1.删除数据
        mSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        //2.保存到本地
        saveLocal();
    }

    /**
     * 保存到本地
     */
    private void saveLocal() {
        //1.把sparseArray转成List
        List<GoodsBean> goodsBeanList = sparseArrayToList();
        //2.使用Gson把List转json的String类型数据
        String savaJson = new Gson().toJson(goodsBeanList);
        //3.使用CacheUtils缓存数据
        CacheUtils.setString(mContext, JSON_CART, savaJson);
    }

    /**
     * 把sparseArray 转成list
     *
     * @return
     */
    private List<GoodsBean> sparseArrayToList() {
        //列表数据
        List<GoodsBean> goodsBeanList = new ArrayList<>();

        for (int i = 0; i < mSparseArray.size(); i++) {
            GoodsBean goodsBean = mSparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }

        return goodsBeanList;
    }

}
