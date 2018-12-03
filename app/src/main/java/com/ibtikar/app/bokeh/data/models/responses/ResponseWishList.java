package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;

import java.util.List;

public class ResponseWishList {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("List")
    @Expose
    private List<ModelProductItem> list = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseWishList() {
    }

    /**
     *
     * @param status
     * @param list
     */
    public ResponseWishList(Boolean status, List<ModelProductItem> list) {
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

    public List<ModelProductItem> getList() {
        return list;
    }

    public void setList(List<ModelProductItem> list) {
        this.list = list;
    }

}
