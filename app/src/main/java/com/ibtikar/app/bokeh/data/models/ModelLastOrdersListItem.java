package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ModelLastOrdersListItem {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("price")
    @Expose
    private Integer price;

    @SerializedName("numOfItems")
    @Expose
    private Integer numOfItems;

    @SerializedName("itemsList")
    @Expose
    private List<ModelCartItem> itemsList = null;


    public ModelLastOrdersListItem() {
    }

    public ModelLastOrdersListItem(Integer id, String date, Integer price, Integer numOfItems, List<ModelCartItem> itemsList) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.numOfItems = numOfItems;
        this.itemsList = itemsList;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(Integer price) {
        this.price = price;

    }

    public void setNumOfItems(Integer numOfItems) {
        this.numOfItems = numOfItems;
    }

    public void setItemsList(List<ModelCartItem> itemsList) {
        this.itemsList = itemsList;
    }


    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getNumOfItems() {
        return numOfItems;
    }

    public List<ModelCartItem> getItemsList() {
        return itemsList;
    }

    public String getDayOfMonth(String date)
    {
        Timestamp timestamp = Timestamp.valueOf(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public String getMonth(String date)
    {
        Timestamp timestamp = Timestamp.valueOf(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        Date dat = calendar.getTime();
        SimpleDateFormat daymonthsimpleDataFormat = new SimpleDateFormat("MMM");

        return daymonthsimpleDataFormat.format(dat);
    }
}
