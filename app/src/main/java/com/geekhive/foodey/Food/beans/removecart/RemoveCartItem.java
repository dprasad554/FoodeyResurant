package com.geekhive.foodey.Food.beans.removecart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveCartItem {
    @SerializedName("message")
    @Expose
    private String message;

    public RemoveCartItem() {
    }

    public RemoveCartItem(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
