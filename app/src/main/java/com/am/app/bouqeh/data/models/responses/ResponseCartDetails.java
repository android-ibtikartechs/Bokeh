package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.am.app.bouqeh.data.models.ModelCartListItem;
import com.am.app.bouqeh.data.models.ModelReciptList;

import java.util.List;

public class ResponseCartDetails {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("List")
    @Expose
    private List<ModelCartListItem> list = null;
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
    public ResponseCartDetails() {
    }

    /**
     *
     * @param status
     * @param orders
     * @param list
     * @param grandTtoal
     */
    public ResponseCartDetails(Boolean status, List<ModelCartListItem> list, List<ModelReciptList> orders, Integer grandTtoal) {
        super();
        this.status = status;
        this.list = list;
        this.orders = orders;
        this.grandTtoal = grandTtoal;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ModelCartListItem> getList() {
        return list;
    }

    public void setList(List<ModelCartListItem> list) {
        this.list = list;
    }

    public List<ModelReciptList>  getOrders() {
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
