package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSendMessageToAdmin {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseSendMessageToAdmin() {
    }

    /**
     *
     * @param status
     * @param msg
     */
    public ResponseSendMessageToAdmin(Boolean status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
