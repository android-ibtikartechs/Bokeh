package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseForgetPassword {

    @SerializedName("status")
    @Expose
    private Boolean status;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseForgetPassword() {
    }

    /**
     *
     * @param status
     */
    public ResponseForgetPassword(Boolean status) {
        super();
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
