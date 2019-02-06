package com.am.app.bouqeh.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAboutApplication {

    @SerializedName("content")
    @Expose
    private String content;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseAboutApplication() {
    }

    /**
     *
     * @param content
     */
    public ResponseAboutApplication(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
