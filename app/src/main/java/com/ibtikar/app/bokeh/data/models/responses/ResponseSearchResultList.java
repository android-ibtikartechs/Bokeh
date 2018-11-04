package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelSearchResultItem;

public class ResponseSearchResultList {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("List")
    @Expose
    private java.util.List<ModelSearchResultItem> list = null;

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
    public ResponseSearchResultList(Boolean status, java.util.List<ModelSearchResultItem> list) {
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

    public java.util.List<ModelSearchResultItem> getList() {
        return list;
    }

    public void setList(java.util.List<ModelSearchResultItem> list) {
        this.list = list;
    }
}
