package com.geekhive.foodey.Food.beans.updatecart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCartItem {
    @SerializedName("message")
    @Expose
    private String message;

    public UpdateCartItem() {
    }

    public UpdateCartItem(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
