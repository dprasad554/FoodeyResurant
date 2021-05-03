
package com.geekhive.foodey.Grocery.beans.storeinstruction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreInstruction {

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
