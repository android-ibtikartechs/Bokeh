package com.am.app.bouqeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelCity {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("AreasList")
    @Expose
    private List<ModelArea> cities = null;

    public ModelCity(Integer id, String name, List<ModelArea> cities) {
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

    public List<ModelArea> getAreas() {
        return cities;
    }

    public void setCities(List<ModelArea> cities) {
        this.cities = cities;
    }
}
