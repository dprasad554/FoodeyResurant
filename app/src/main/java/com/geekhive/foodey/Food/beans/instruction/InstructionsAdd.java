
package com.geekhive.foodey.Food.beans.instruction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstructionsAdd {

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
