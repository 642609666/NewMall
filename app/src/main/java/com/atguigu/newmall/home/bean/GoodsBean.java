package com.atguigu.newmall.home.bean;

import java.io.Serializable;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 2/25 0025.
 * 功能:
 */

public class GoodsBean implements Serializable {
    /**
     * cover_price : 29.00
     * figure : /1452161899947.jpg
     * name : 【喵鹿酱】超萌 假透肉 拼接 踩脚过膝打底袜 裤袜-加绒保暖
     * product_id : 3831
     */

    private String cover_price;
    private String figure;
    private String name;
    private String product_id;

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
