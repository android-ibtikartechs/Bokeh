package com.ibtikar.app.bokeh.data.models;

public class ModelShopItem {
    private String id;
    private String title;
    private String imgUrl;

    public ModelShopItem(String id, String title, String imgUrl) {
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
