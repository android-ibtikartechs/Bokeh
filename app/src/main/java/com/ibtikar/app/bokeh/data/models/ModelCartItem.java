package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelCartItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("sellerid")
    @Expose
    private Integer sellerid;
    @SerializedName("sellername")
    @Expose
    private String sellername;
    @SerializedName("categoryid")
    @Expose
    private Integer categoryid;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("hasoffer")
    @Expose
    private Boolean hasoffer;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("oprice")
    @Expose
    private Integer oprice;
    @SerializedName("offer_start_date")
    @Expose
    private String offerStartDate;
    @SerializedName("offer_start_time")
    @Expose
    private String offerStartTime;
    @SerializedName("offer_end_date")
    @Expose
    private String offerEndDate;
    @SerializedName("offer_end_time")
    @Expose
    private String offerEndTime;
    @SerializedName("discount_percentage")
    @Expose
    private String discountPercentage;
    @SerializedName("isSameDayDelivery")
    @Expose
    private Boolean isSameDayDelivery;
    @SerializedName("isLiked")
    @Expose
    private Boolean isLiked;
    @SerializedName("Gallery")
    @Expose
    private List<GalleryProductImage> gallery = null;
    @SerializedName("booking_date")
    @Expose
    private String bookingDate;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;


    public ModelCartItem(Integer id, String name, String image, Integer sellerid, String sellername, Integer categoryid, String category, String details, Boolean hasoffer, Integer price, Integer oprice, String offerStartDate, String offerStartTime, String offerEndDate, String offerEndTime, String discountPercentage, Boolean isSameDayDelivery, Boolean isLiked, List<GalleryProductImage> gallery, String bookingDate, String deliveryTime, Integer quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.sellerid = sellerid;
        this.sellername = sellername;
        this.categoryid = categoryid;
        this.category = category;
        this.details = details;
        this.hasoffer = hasoffer;
        this.price = price;
        this.oprice = oprice;
        this.offerStartDate = offerStartDate;
        this.offerStartTime = offerStartTime;
        this.offerEndDate = offerEndDate;
        this.offerEndTime = offerEndTime;
        this.discountPercentage = discountPercentage;
        this.isSameDayDelivery = isSameDayDelivery;
        this.isLiked = isLiked;
        this.gallery = gallery;
        this.bookingDate = bookingDate;
        this.deliveryTime = deliveryTime;
        this.quantity = quantity;
    }


    public ModelCartItem() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public String getCategory() {
        return category;
    }

    public String getDetails() {
        return details;
    }

    public Boolean getHasoffer() {
        return hasoffer;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getOprice() {
        return oprice;
    }

    public String getOfferStartDate() {
        return offerStartDate;
    }

    public String getOfferStartTime() {
        return offerStartTime;
    }

    public String getOfferEndDate() {
        return offerEndDate;
    }

    public String getOfferEndTime() {
        return offerEndTime;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public Boolean getSameDayDelivery() {
        return isSameDayDelivery;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public List<GalleryProductImage> getGallery() {
        return gallery;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
