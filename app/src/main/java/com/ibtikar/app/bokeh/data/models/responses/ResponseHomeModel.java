package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.ModelShopItem;
import com.ibtikar.app.bokeh.data.models.OccasionItemModel;

import java.util.List;

public class ResponseHomeModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Specialoffers")
    @Expose
    private List<ModelProductItem> specialoffers = null;
    @SerializedName("Occasions")
    @Expose
    private List<OccasionItemModel> occasions = null;
    @SerializedName("Shops")
    @Expose
    private List<ModelShopItem> shops = null;
    @SerializedName("Items")
    @Expose
    private List<ModelProductItem> items = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseHomeModel() {
    }

    /**
     *
     * @param items
     * @param shops
     * @param status
     * @param specialoffers
     * @param occasions
     */
    public ResponseHomeModel(Boolean status, List<ModelProductItem> specialoffers, List<OccasionItemModel> occasions, List<ModelShopItem> shops, List<ModelProductItem> items) {
        super();
        this.status = status;
        this.specialoffers = specialoffers;
        this.occasions = occasions;
        this.shops = shops;
        this.items = items;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ModelProductItem> getSpecialoffers() {
        return specialoffers;
    }

    public void setSpecialoffers(List<ModelProductItem> specialoffers) {
        this.specialoffers = specialoffers;
    }

    public List<OccasionItemModel> getOccasions() {
        return occasions;
    }

    public void setOccasions(List<OccasionItemModel> occasions) {
        this.occasions = occasions;
    }

    public List<ModelShopItem> getShops() {
        return shops;
    }

    public void setShops(List<ModelShopItem> shops) {
        this.shops = shops;
    }

    public List<ModelProductItem> getItems() {
        return items;
    }

    public void setItems(List<ModelProductItem> items) {
        this.items = items;
    }


}
