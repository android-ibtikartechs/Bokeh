package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.am.app.bouqeh.data.models.ModelShopItem;

import java.util.List;

public class ResponseShopsList {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("List")
    @Expose
    private List<ModelShopItem> list = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseShopsList() {
    }

    /**
     *
     * @param status
     * @param list
     */
    public ResponseShopsList(Boolean status, List<ModelShopItem> list) {
        super();
        this.status = status;
        this.list = list;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ModelShopItem> getList() {
        return list;
    }

    public void setList(List<ModelShopItem> list) {
        this.list = list;
    }
}
