package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;

import java.util.List;

public class ResponseDeleteCartItem {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
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
    public ResponseDeleteCartItem() {
    }

    /**
     *
     * @param status
     * @param orders
     * @param deleted
     * @param grandTtoal
     */
    public ResponseDeleteCartItem(Boolean status, Boolean deleted, List<ModelReciptList> orders, Integer grandTtoal) {
        super();
        this.status = status;
        this.deleted = deleted;
        this.orders = orders;
        this.grandTtoal = grandTtoal;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
