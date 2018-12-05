package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelLastOrdersListItem;

import java.util.List;

public class ResponseOrdersHistory {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("List")
    @Expose
    private List<ModelLastOrdersListItem> list = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseOrdersHistory() {
    }

    /**
     *
     * @param status
     * @param list
     */
    public ResponseOrdersHistory(Boolean status, List<ModelLastOrdersListItem> list) {
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

    public List<ModelLastOrdersListItem> getList() {
        return list;
    }

    public void setList(List<ModelLastOrdersListItem> list) {
        this.list = list;
    }

}
