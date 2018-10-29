package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.OccasionItemModel;

import java.util.List;

public class ResponseCategoriesModel {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Occasions")
    @Expose
    private List<OccasionItemModel> occasions = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseCategoriesModel() {
    }

    /**
     *
     * @param status
     * @param occasions
     */
    public ResponseCategoriesModel(Boolean status, List<OccasionItemModel> occasions) {
        super();
        this.status = status;
        this.occasions = occasions;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<OccasionItemModel> getOccasions() {
        return occasions;
    }

    public void setOccasions(List<OccasionItemModel> occasions) {
        this.occasions = occasions;
    }
}
