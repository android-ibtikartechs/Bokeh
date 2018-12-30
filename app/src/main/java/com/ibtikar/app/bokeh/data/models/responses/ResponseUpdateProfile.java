package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.InfoDataRegistiration;

public class ResponseUpdateProfile  {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("user")
    @Expose
    private InfoDataRegistiration user;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseUpdateProfile() {
    }

    /**
     *
     * @param status
     * @param user
     */
    public ResponseUpdateProfile(Boolean status, InfoDataRegistiration user) {
        super();
        this.status = status;
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public InfoDataRegistiration getUser() {
        return user;
    }

    public void setUser(InfoDataRegistiration user) {
        this.user = user;
    }
}
