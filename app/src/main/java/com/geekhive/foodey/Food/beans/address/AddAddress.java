package com.geekhive.foodey.Food.beans.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAddress {

    @SerializedName("message")
    @Expose
    private String message;

    public AddAddress() {
    }

    public AddAddress(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
