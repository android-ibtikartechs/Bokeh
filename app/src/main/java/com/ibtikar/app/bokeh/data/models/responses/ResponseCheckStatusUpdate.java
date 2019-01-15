package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCheckStatusUpdate {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("updated")
    @Expose
    private Boolean updated;
    @SerializedName("forceupdate")
    @Expose
    private Boolean forceupdate;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseCheckStatusUpdate() {
    }

    /**
     *
     * @param updated
     * @param status
     * @param forceupdate
     */
    public ResponseCheckStatusUpdate(Boolean status, String token, Boolean updated, Boolean forceupdate) {
        super();
        this.status = status;
        this.updated = updated;
        this.forceupdate = forceupdate;
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public Boolean getForceupdate() {
        return forceupdate;
    }

    public void setForceupdate(Boolean forceupdate) {
        this.forceupdate = forceupdate;
    }
}
