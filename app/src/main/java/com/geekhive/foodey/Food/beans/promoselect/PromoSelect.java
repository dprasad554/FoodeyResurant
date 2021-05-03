
package com.geekhive.foodey.Food.beans.promoselect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoSelect {

    @SerializedName("CartList")
    @Expose
    private CartList cartList;

    public CartList getCartList() {
        return cartList;
    }

    public void setCartList(CartList cartList) {
        this.cartList = cartList;
    }

}
