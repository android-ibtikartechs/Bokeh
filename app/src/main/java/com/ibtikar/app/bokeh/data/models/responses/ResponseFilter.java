package com.ibtikar.app.bokeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;

import java.util.List;

public class ResponseFilter {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Result")
    @Expose
    private List<ModelProductItem> result = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseFilter() {
    }

    /**
     *
     * @param result
     * @param status
     */
    public ResponseFilter(Boolean status, List<ModelProductItem> result) {
        super();
        this.status = status;
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ModelProductItem> getResult() {
        return result;
    }

    public void setResult(List<ModelProductItem> result) {
        this.result = result;
    }
}
