package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSearchResultItem {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;

    /**
     * No args constructor for use in serialization
     *
     */
    public ModelSearchResultItem() {
    }

    /**
     *
     * @param id
     * @param category
     * @param name
     */
    public ModelSearchResultItem(Integer id, String name, String category) {
        super();
        this.id = id;
        this.name = name;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
