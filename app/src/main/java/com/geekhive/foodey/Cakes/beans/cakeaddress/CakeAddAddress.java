package com.geekhive.foodey.Cakes.beans.cakeaddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeAddAddress {

    @SerializedName("message")
    @Expose
    private String message;

    public CakeAddAddress() {
    }

    public CakeAddAddress(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
