package com.ibtikar.app.bokeh.data.models;

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
    public DeliveryCartItemModel(String date, Integer time, Integer type, Integer quantity) {
        super();
        this.date = date;
        this.time = time;
        this.type = type;
        this.quantity = quantity;
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
