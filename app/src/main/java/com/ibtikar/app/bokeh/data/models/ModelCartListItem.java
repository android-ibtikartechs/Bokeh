package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelCartListItem {
    @SerializedName("Delivary")
    @Expose
    private DeliveryCartItemModel delivary;
    @SerializedName("ProductInfo")
    @Expose
    private ModelProductItem modelProductItem;

    /**
     * No args constructor for use in serialization
     *
     */
    public ModelCartListItem() {
    }

    /**
     *
     * @param delivary
     * @param modelProductItem
     */
    public ModelCartListItem(DeliveryCartItemModel delivary, ModelProductItem modelProductItem) {
        super();
        this.delivary = delivary;
        this.modelProductItem = modelProductItem;
    }

    public DeliveryCartItemModel getDelivary() {
        return delivary;
    }

    public void setDelivary(DeliveryCartItemModel delivary) {
        this.delivary = delivary;
    }

    public ModelProductItem getProductInfo() {
        return modelProductItem;
    }

    public void setProductInfo(ModelProductItem productInfo) {
        this.modelProductItem = productInfo;
    }
}
