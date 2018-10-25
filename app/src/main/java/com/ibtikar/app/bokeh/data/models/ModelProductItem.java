package com.ibtikar.app.bokeh.data.models;

public class ModelProductItem {
    private String id;
    private String title;
    private String imUrl;
    private Integer price;
    private boolean isSameDayDelivery;
    private String sellerName;
    private boolean isLiked;
    private String description;

    public ModelProductItem(String id, String title, String imUrl, Integer price, boolean isSameDayDelivery, String sellerName, boolean isLiked, String description) {
        this.id = id;
        this.title = title;
        this.imUrl = imUrl;
        this.price = price;
        this.isSameDayDelivery = isSameDayDelivery;
        this.sellerName = sellerName;
        this.isLiked = isLiked;
        this.description = description;
    }

    public ModelProductItem(String id, String title, String imUrl) {
        this.id = id;
        this.title = title;
        this.imUrl = imUrl;
    }

    public ModelProductItem(String id, String title, String imUrl, Integer price, boolean isSameDayDelivery) {
        this.id = id;
        this.title = title;
        this.imUrl = imUrl;
        this.price = price;
        this.isSameDayDelivery = isSameDayDelivery;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImUrl() {
        return imUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public boolean isSameDayDelivery() {
        return isSameDayDelivery;
    }

    public String getSellerName() {
        return sellerName;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public String getDescription() {
        return description;
    }
}
