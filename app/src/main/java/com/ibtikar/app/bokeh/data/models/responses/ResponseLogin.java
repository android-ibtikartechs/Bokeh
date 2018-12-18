package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.InfoDataRegistiration;

public class ResponseLogin {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("Info")
    @Expose
    private InfoDataRegistiration info;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseLogin() {
    }

    /**
     *
     * @param status
     * @param code
     * @param msg
     * @param info
     */
    public ResponseLogin(Boolean status, Integer code, String msg, InfoDataRegistiration info) {
        super();
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InfoDataRegistiration getInfo() {
        return info;
    }

    public void setInfo(InfoDataRegistiration info) {
        this.info = info;
    }
}
