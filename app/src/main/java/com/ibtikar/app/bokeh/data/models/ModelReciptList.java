package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelReciptList  {
    @SerializedName("delivery")
    @Expose
    private Integer delivery;
    @SerializedName("subtotal")
    @Expose
    private Integer subtotal;

    @SerializedName("Products")
    @Expose
    private List<ModelProductItemReciptList> products = null;

    public ModelReciptList() {
    }

    public ModelReciptList(Integer delivery, Integer subtotal, List<ModelProductItemReciptList> products) {
        this.delivery = delivery;
        this.subtotal = subtotal;
        this.products = products;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setProducts(List<ModelProductItemReciptList> products) {
        this.products = products;
    }

    public List<ModelProductItemReciptList> getProducts() {
        return products;
    }
}
