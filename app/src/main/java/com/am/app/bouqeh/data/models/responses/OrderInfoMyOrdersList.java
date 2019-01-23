package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderInfoMyOrdersList {

    @SerializedName("ordertotal")
    @Expose
    private Integer ordertotal;
    @SerializedName("totalitems")
    @Expose
    private Integer totalitems;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderInfoMyOrdersList() {
    }

    /**
     *
     * @param ordertotal
     * @param totalitems
     */
    public OrderInfoMyOrdersList(Integer ordertotal, Integer totalitems) {
        super();
        this.ordertotal = ordertotal;
        this.totalitems = totalitems;
    }

    public Integer getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(Integer ordertotal) {
        this.ordertotal = ordertotal;
    }

    public Integer getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(Integer totalitems) {
        this.totalitems = totalitems;
    }

}
