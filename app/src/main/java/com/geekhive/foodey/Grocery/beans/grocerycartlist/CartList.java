
package com.geekhive.foodey.Grocery.beans.grocerycartlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartList {

    @SerializedName("CartList")
    @Expose
    private CartList_ cartList;

    public CartList_ getCartList() {
        return cartList;
    }

    public void setCartList(CartList_ cartList) {
        this.cartList = cartList;
    }

}
