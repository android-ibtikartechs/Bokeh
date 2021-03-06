package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.am.app.bouqeh.data.models.ModelProductItem;

public class ResponseSearchResultList {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Result")
    @Expose
    private java.util.List<ModelProductItem> list = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseSearchResultList() {
    }
    /**
     *
     * @param status
     * @param list
     */
    public ResponseSearchResultList(Boolean status, java.util.List<ModelProductItem> list) {
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

    public java.util.List<ModelProductItem> getList() {
        return list;
    }

    public void setList(java.util.List<ModelProductItem> list) {
        this.list = list;
    }
}
