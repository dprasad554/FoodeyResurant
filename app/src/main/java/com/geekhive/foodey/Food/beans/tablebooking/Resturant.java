
package com.geekhive.foodey.Food.beans.tablebooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resturant {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("long")
    @Expose
    private Object _long;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("date")
    @Expose
    private Object date;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("open_status")
    @Expose
    private Object openStatus;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("about")
    @Expose
    private Object about;
    @SerializedName("features")
    @Expose
    private Object features;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("timing")
    @Expose
    private Object timing;
    @SerializedName("password")
    @Expose
    private Object password;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLong() {
        return _long;
    }

    public void setLong(Object _long) {
        this._long = _long;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Object openStatus) {
        this.openStatus = openStatus;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getAbout() {
        return about;
    }

    public void setAbout(Object about) {
        this.about = about;
    }

    public Object getFeatures() {
        return features;
    }

    public void setFeatures(Object features) {
        this.features = features;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getTiming() {
        return timing;
    }

    public void setTiming(Object timing) {
        this.timing = timing;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

}
