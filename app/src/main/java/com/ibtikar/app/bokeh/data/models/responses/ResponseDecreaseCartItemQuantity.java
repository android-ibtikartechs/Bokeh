package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;

import java.util.List;

public class ResponseDecreaseCartItemQuantity {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("decreased")
    @Expose
    private Boolean decreased;
    @SerializedName("maximum")
    @Expose
    private Integer maximum;
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
    public ResponseDecreaseCartItemQuantity() {
    }

    /**
     *
     * @param increased
     * @param status
     * @param maximum
     * @param orders
     * @param grandTtoal
     */
    public ResponseDecreaseCartItemQuantity(Boolean status, Boolean increased, Integer maximum, List<ModelReciptList> orders, Integer grandTtoal) {
        super();
        this.status = status;
        this.decreased = increased;
        this.maximum = maximum;
        this.orders = orders;
        this.grandTtoal = grandTtoal;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDecreased() {
        return decreased;
    }

    public void setDecreased(Boolean decreased) {
        this.decreased = decreased;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
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
