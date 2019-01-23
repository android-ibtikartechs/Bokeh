package com.am.app.bouqeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProductItemReciptList {
    @SerializedName("productid")
    @Expose
    private Integer productid;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("totalprice")
    @Expose
    private Integer totalprice;

    public ModelProductItemReciptList() {
    }

    public ModelProductItemReciptList(Integer productid, String productname, Integer quantity, Integer price, Integer totalprice) {
        this.productid = productid;
        this.productname = productname;
        this.quantity = quantity;
        this.price = price;
        this.totalprice = totalprice;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }
}
