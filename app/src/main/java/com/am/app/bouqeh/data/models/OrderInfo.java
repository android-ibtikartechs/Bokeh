package com.am.app.bouqeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderInfo {
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Time")
    @Expose
    private Integer time;
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("City")
    @Expose
    private Integer city;
    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("Area")
    @Expose
    private Integer area;
    @SerializedName("AreaName")
    @Expose
    private String areaName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("SubTotal")
    @Expose
    private Integer subTotal;
    @SerializedName("DelivaryFees")
    @Expose
    private Integer delivaryFees;
    @SerializedName("Total")
    @Expose
    private Integer total;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderInfo() {
    }

    /**
     *
     * @param total
     * @param time
     * @param area
     * @param delivaryFees
     * @param areaName
     * @param subTotal
     * @param address
     * @param cityName
     * @param type
     * @param date
     * @param city
     */
    public OrderInfo(String date, Integer time, Integer type, Integer city, String cityName, Integer area, String areaName, String address, Integer subTotal, Integer delivaryFees, Integer total) {
        super();
        this.date = date;
        this.time = time;
        this.type = type;
        this.city = city;
        this.cityName = cityName;
        this.area = area;
        this.areaName = areaName;
        this.address = address;
        this.subTotal = subTotal;
        this.delivaryFees = delivaryFees;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getDelivaryFees() {
        return delivaryFees;
    }

    public void setDelivaryFees(Integer delivaryFees) {
        this.delivaryFees = delivaryFees;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
