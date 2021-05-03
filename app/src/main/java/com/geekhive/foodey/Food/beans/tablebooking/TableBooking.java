package com.geekhive.foodey.Food.beans.tablebooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableBooking {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
