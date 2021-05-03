
package com.geekhive.foodey.Cakes.beans.cakehistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeHistory {

    @SerializedName("OrderHistory")
    @Expose
    private OrderHistory orderHistory;

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

}
