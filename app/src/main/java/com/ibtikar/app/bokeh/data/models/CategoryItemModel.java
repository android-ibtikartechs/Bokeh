package com.ibtikar.app.bokeh.data.models;

import android.graphics.Bitmap;

public class CategoryItemModel {
    private String id;
    private String imUrl;
    private Bitmap imBitmab;
    private String title;

    public CategoryItemModel(String id, String imUrl, String title) {
        this.id = id;
        this.imUrl = imUrl;
        this.title = title;
    }

    public CategoryItemModel(String id, Bitmap imBitmab, String title) {
        this.id = id;
        this.imBitmab = imBitmab;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getImUrl() {
        return imUrl;
    }

    public Bitmap getImBitmab() {
        return imBitmab;
    }

    public String getTitle() {
        return title;
    }
}
