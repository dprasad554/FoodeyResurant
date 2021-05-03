package com.geekhive.foodey.Cakes.beans.cakeremovecart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeRemoveCartItem {
    @SerializedName("message")
    @Expose
    private String message;

    public CakeRemoveCartItem() {
    }

    public CakeRemoveCartItem(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
