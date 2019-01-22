package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelCity;

import java.util.List;

public class ResponseCitiesList {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("List")
    @Expose
    private List<ModelCity> list = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseCitiesList() {
    }

    /**
     *
     * @param status
     * @param list
     */
    public ResponseCitiesList(Boolean status, List list) {
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

    public List<ModelCity> getList() {
        return list;
    }

    public void setList(List<ModelCity> list) {
        this.list = list;
    }

}
