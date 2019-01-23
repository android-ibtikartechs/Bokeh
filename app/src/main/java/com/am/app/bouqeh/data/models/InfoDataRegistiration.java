package com.am.app.bouqeh.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoDataRegistiration {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;

    /**
     * No args constructor for use in serialization
     *
     */
    public InfoDataRegistiration() {
    }

    /**
     *
     * @param id
     * @param phone
     * @param last
     * @param email
     * @param birthdate
     * @param gender
     * @param first
     */
    public InfoDataRegistiration(Integer id, String first, String last, String email, String phone, Integer gender, String birthdate) {
        super();
        this.id = id;
        this.first = first;
        this.last = last;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
