package com.geekhive.foodey.Cakes.beans.cakeupdatecart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeUpdateCartItem {
    @SerializedName("message")
    @Expose
    private String message;

    public CakeUpdateCartItem() {
    }

    public CakeUpdateCartItem(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
