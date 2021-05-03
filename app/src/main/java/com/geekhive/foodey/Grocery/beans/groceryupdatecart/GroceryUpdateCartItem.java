package com.geekhive.foodey.Grocery.beans.groceryupdatecart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryUpdateCartItem {
    @SerializedName("message")
    @Expose
    private String message;

    public GroceryUpdateCartItem() {
    }

    public GroceryUpdateCartItem(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
