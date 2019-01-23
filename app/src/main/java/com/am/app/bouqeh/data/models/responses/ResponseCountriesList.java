package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.am.app.bouqeh.data.models.ModelCountry;

import java.util.List;

public class ResponseCountriesList {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("List")
    @Expose
    private List<ModelCountry> list = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseCountriesList() {
    }

    /**
     *
     * @param status
     * @param list
     */
    public ResponseCountriesList(Boolean status, List<ModelCountry> list) {
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

    public List<ModelCountry> getList() {
        return list;
    }

    public void setList(List<ModelCountry> list) {
        this.list = list;
    }

}
