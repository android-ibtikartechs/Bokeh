package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelReciptList  {
    @SerializedName("OrderInfo")
    @Expose
    private OrderInfo orderInfo;

    @SerializedName("OrderItems")
    @Expose
    private List<ModelProductItemReciptList> products = null;

    public ModelReciptList() {
    }

    public ModelReciptList(List<ModelProductItemReciptList> products, OrderInfo orderInfo) {
        this.products = products;
        this.orderInfo = orderInfo;
    }




    public void setProducts(List<ModelProductItemReciptList> products) {
        this.products = products;
    }

    public List<ModelProductItemReciptList> getProducts() {
        return products;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
