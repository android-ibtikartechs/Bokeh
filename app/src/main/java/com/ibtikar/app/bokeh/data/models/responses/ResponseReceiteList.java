package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;

import java.util.List;

public class ResponseReceiteList {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Orders")
    @Expose
    private List<ModelReciptList> orders = null;
    @SerializedName("GrandTtoal")
    @Expose
    private Integer grandTtoal;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseReceiteList() {
    }

    /**
     *
     * @param status
     * @param orders
     * @param grandTtoal
     */
    public ResponseReceiteList(Boolean status, List<ModelReciptList> orders, Integer grandTtoal) {
        super();
        this.status = status;
        this.orders = orders;
        this.grandTtoal = grandTtoal;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ModelReciptList> getOrders() {
        return orders;
    }

    public void setOrders(List<ModelReciptList> orders) {
        this.orders = orders;
    }

    public Integer getGrandTtoal() {
        return grandTtoal;
    }

    public void setGrandTtoal(Integer grandTtoal) {
        this.grandTtoal = grandTtoal;
    }
}
