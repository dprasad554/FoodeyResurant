
package com.geekhive.foodey.Grocery.beans.groceryaddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroceryAddressList {

    @SerializedName("Address")
    @Expose
    private List<GroceryAddress> address = null;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GroceryAddress> getAddress() {
        return address;
    }

    public void setAddress(List<GroceryAddress> address) {
        this.address = address;
    }

}
