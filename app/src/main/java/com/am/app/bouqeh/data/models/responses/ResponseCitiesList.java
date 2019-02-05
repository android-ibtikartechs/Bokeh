package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.am.app.bouqeh.data.models.ModelCity;

import java.util.List;

public class ResponseCitiesList {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("List")
    @Expose
    private List<ModelCity> list = null;
    @SerializedName("token")
    @Expose
    private String token;

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
    public ResponseCitiesList(Boolean status, List list, String token) {
        super();
        this.status = status;
        this.list = list;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
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
