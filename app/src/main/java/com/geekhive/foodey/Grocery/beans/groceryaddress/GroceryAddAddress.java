package com.geekhive.foodey.Grocery.beans.groceryaddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryAddAddress {

    @SerializedName("message")
    @Expose
    private String message;

    public GroceryAddAddress() {
    }

    public GroceryAddAddress(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
