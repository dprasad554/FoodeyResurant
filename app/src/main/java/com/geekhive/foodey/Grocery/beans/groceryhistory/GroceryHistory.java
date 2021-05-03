
package com.geekhive.foodey.Grocery.beans.groceryhistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryHistory {

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
