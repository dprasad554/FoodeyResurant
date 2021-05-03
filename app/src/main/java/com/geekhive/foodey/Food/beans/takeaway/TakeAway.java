
package com.geekhive.foodey.Food.beans.takeaway;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TakeAway {

    @SerializedName("Cart")
    @Expose
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
