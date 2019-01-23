package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.am.app.bouqeh.data.models.ModelReciptList;

import java.util.List;

public class ResponseIncreaseCartItemQuantity {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("increased")
    @Expose
    private Boolean increased;

    @SerializedName("product")
    @Expose
    private String productName;

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
    public ResponseIncreaseCartItemQuantity() {
    }

    /**
     *
     * @param increased
     * @param status
     * @param maximum
     * @param orders
     * @param grandTtoal
     */
    public ResponseIncreaseCartItemQuantity(Boolean status, Boolean increased, String productName, Integer maximum, List<ModelReciptList> orders, Integer grandTtoal) {
        super();
        this.status = status;
        this.increased = increased;
        this.maximum = maximum;
        this.orders = orders;
        this.grandTtoal = grandTtoal;
        this.productName = productName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIncreased() {
        return increased;
    }

    public void setIncreased(Boolean increased) {
        this.increased = increased;
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


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
