package com.geekhive.foodey.Grocery.beans.groceryremovecart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryRemoveCartItem {
    @SerializedName("message")
    @Expose
    private String message;

    public GroceryRemoveCartItem() {
    }

    public GroceryRemoveCartItem(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
