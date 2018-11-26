package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAddToCart {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("added")
    @Expose
    private Boolean added;

    @SerializedName("exists")
    @Expose
    private Boolean existed;


    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseAddToCart() {
    }

    /**
     *
     * @param added
     * @param status
     */
    public ResponseAddToCart(Boolean status, Boolean added, Boolean existed) {
        super();
        this.status = status;
        this.added = added;
        this.existed = existed;
    }

    public Boolean getExisted() {
        return existed;
    }

    public void setExisted(Boolean existed) {
        this.existed = existed;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getAdded() {
        return added;
    }

    public void setAdded(Boolean added) {
        this.added = added;
    }
}
