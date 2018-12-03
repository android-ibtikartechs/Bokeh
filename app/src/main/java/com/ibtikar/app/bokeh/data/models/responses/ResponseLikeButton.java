package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLikeButton {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("isLiked")
    @Expose
    private Boolean isLiked;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseLikeButton() {
    }

    /**
     *
     * @param status
     * @param isLiked
     */
    public ResponseLikeButton(Boolean status, Boolean isLiked) {
        super();
        this.status = status;
        this.isLiked = isLiked;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }
}
