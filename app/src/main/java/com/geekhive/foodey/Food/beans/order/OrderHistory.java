
package com.geekhive.foodey.Food.beans.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistory {

    @SerializedName("OrderHistory")
    @Expose
    private OrderHistory_ orderHistory;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderHistory_ getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory_ orderHistory) {
        this.orderHistory = orderHistory;
    }

}
