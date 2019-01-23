package com.am.app.bouqeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryCartItemModel {

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Time")
    @Expose
    private Integer time;
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;

    @SerializedName("cartitemid")
    @Expose
    private Integer cartItemId;


    /**
     * No args constructor for use in serialization
     *
     */
    public DeliveryCartItemModel() {
    }

    /**
     *
     * @param time
     * @param quantity
     * @param type
     * @param date
     */
    public DeliveryCartItemModel(String date, Integer time, Integer type, Integer quantity, Integer cartItemId) {
        super();
        this.date = date;
        this.time = time;
        this.type = type;
        this.quantity = quantity;
        this.cartItemId = cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
