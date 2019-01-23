package com.am.app.bouqeh.data.models;

public class ModelGender {

    private Integer id;
    private String name;

    public ModelGender(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
