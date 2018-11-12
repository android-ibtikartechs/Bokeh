package com.ibtikar.app.bokeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelArea {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("Cities")
    @Expose
    private List<ModelCity> cities = null;

    public ModelArea(Integer id, String name, List<ModelCity> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ModelCity> getCities() {
        return cities;
    }

    public void setCities(List<ModelCity> cities) {
        this.cities = cities;
    }
}
