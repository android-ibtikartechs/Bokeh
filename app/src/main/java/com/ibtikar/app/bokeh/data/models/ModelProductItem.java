package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ModelProductItem {
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
    @SerializedName("Cities")
    @Expose
    private List<ModelArea> Cities = null;
    @SerializedName("hasoffer")
    @Expose
    private Boolean hasoffer;
    @SerializedName("offerimage")
    @Expose
    private String offerImage;
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

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public ModelProductItem() {
    }

    /**
     *
     * @param offerEndTime
     * @param hasoffer
     * @param image
     * @param oprice
     * @param isSameDayDelivery
     * @param sellerid
     * @param id
     * @param categoryid
     * @param category
     * @param price
     * @param details
     * @param offerStartDate
     * @param discountPercentage
     * @param name
     * @param offerEndDate
     * @param isLiked
     * @param sellername
     * @param offerStartTime
     */
    public ModelProductItem(Integer id, String name, String image, Integer sellerid, String sellername, Integer categoryid, String category, String details,List<ModelArea> Cities, Boolean hasoffer, String offerImage, Integer price, Integer oprice, String offerStartDate, String offerStartTime, String offerEndDate, String offerEndTime, String discountPercentage, Boolean isSameDayDelivery, Boolean isLiked, List<GalleryProductImage> gallery, String createdAt) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.sellerid = sellerid;
        this.sellername = sellername;
        this.categoryid = categoryid;
        this.category = category;
        this.details = details;
        this.hasoffer = hasoffer;
        this.offerImage = offerImage;
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
        this.createdAt = createdAt;
        this.Cities = Cities;
    }

    public List<ModelArea> getCities() {
        return Cities;
    }

    public void setCities(List<ModelArea> cities) {
        Cities = cities;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getHasoffer() {
        return hasoffer;
    }

    public void setHasoffer(Boolean hasoffer) {
        this.hasoffer = hasoffer;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOprice() {
        return oprice;
    }

    public void setOprice(Integer oprice) {
        this.oprice = oprice;
    }

    public String getOfferStartDate() {
        return offerStartDate;
    }

    public void setOfferStartDate(String offerStartDate) {
        this.offerStartDate = offerStartDate;
    }

    public String getOfferStartTime() {
        return offerStartTime;
    }

    public void setOfferStartTime(String offerStartTime) {
        this.offerStartTime = offerStartTime;
    }

    public String getOfferEndDate() {
        return offerEndDate;
    }

    public void setOfferEndDate(String offerEndDate) {
        this.offerEndDate = offerEndDate;
    }

    public String getOfferEndTime() {
        return offerEndTime;
    }

    public void setOfferEndTime(String offerEndTime) {
        this.offerEndTime = offerEndTime;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getIsSameDayDelivery() {
        return isSameDayDelivery;
    }

    public void setIsSameDayDelivery(Boolean isSameDayDelivery) {
        this.isSameDayDelivery = isSameDayDelivery;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public List<GalleryProductImage> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryProductImage> gallery) {
        this.gallery = gallery;
    }

    public Long getTimeOfProductAdditionInMilliSecond() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = getCreatedAt();
        Date date = sdf.parse(dateInString);
        return date.getTime();
    }

}
