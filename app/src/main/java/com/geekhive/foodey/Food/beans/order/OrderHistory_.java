
package com.geekhive.foodey.Food.beans.order;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistory_ {

    @SerializedName("Cart")
    @Expose
    private List<Cart> cart = null;
    @SerializedName("CartDetail")
    @Expose
    private List<List<CartDetail>> cartDetail = null;
    @SerializedName("DeliveryBoy")
    @Expose
    private List<DeliveryBoy> deliveryBoy = null;
    @SerializedName("Resturant")
    @Expose
    private List<Resturant> resturant = null;

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public List<List<CartDetail>> getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(List<List<CartDetail>> cartDetail) {
        this.cartDetail = cartDetail;
    }

    public List<DeliveryBoy> getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(List<DeliveryBoy> deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    public List<Resturant> getResturant() {
        return resturant;
    }

    public void setResturant(List<Resturant> resturant) {
        this.resturant = resturant;
    }

}
