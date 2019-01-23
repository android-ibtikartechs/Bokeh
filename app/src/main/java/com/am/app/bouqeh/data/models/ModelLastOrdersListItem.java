package com.am.app.bouqeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.am.app.bouqeh.data.models.responses.OrderInfoMyOrdersList;

import java.util.List;

public class ModelLastOrdersListItem {
    @SerializedName("orderdate")
    @Expose
    private String orderdate;

    @SerializedName("orderinfo")
    @Expose
    private OrderInfoMyOrdersList orderinfo;

    @SerializedName("List")
    @Expose
    private List<ModelCartListItem> itemsList = null;


    public ModelLastOrdersListItem() {
    }

    /**
     *
     * @param orderdate
     * @param orderinfo
     * @param list
     */
    public ModelLastOrdersListItem(String orderdate, List<ModelCartListItem> list, OrderInfoMyOrdersList orderinfo) {
        super();
        this.orderdate = orderdate;
        this.itemsList = list;
        this.orderinfo = orderinfo;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public List<ModelCartListItem> getList() {
        return itemsList;
    }

    public void setList(List<ModelCartListItem> list) {
        this.itemsList = list;
    }

    public OrderInfoMyOrdersList getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(OrderInfoMyOrdersList orderinfo) {
        this.orderinfo = orderinfo;
    }

}
