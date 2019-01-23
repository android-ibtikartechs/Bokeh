package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.am.app.bouqeh.data.models.ModelProductItem;

import java.util.List;

public class ResponseProductList {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Products")
    @Expose
    private List<ModelProductItem> products = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseProductList() {
    }

    /**
     *
     * @param status
     * @param products
     */
    public ResponseProductList(Boolean status, List<ModelProductItem> products) {
        super();
        this.status = status;
        this.products = products;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ModelProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ModelProductItem> products) {
        this.products = products;
    }
}
