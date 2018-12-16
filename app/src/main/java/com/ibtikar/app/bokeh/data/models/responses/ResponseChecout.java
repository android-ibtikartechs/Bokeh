package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseChecout {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("orderid")
    @Expose
    private Integer orderid;
    @SerializedName("ordertotal")
    @Expose
    private Integer ordertotal;
    @SerializedName("paytype")
    @Expose
    private Integer paytype;
    @SerializedName("payed")
    @Expose
    private String payed;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseChecout() {
    }

    /**
     *
     * @param status
     * @param payed
     * @param paytype
     * @param ordertotal
     * @param orderid
     */
    public ResponseChecout(Boolean status, Integer orderid, Integer ordertotal, Integer paytype, String payed) {
        super();
        this.status = status;
        this.orderid = orderid;
        this.ordertotal = ordertotal;
        this.paytype = paytype;
        this.payed = payed;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(Integer ordertotal) {
        this.ordertotal = ordertotal;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getPayed() {
        return payed;
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }
}
