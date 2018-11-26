package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProductItemReciptList {
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("name")
    @Expose
    private String name;

    public ModelProductItemReciptList() {
    }

    public ModelProductItemReciptList(Integer price, String name) {
        this.price = price;
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
