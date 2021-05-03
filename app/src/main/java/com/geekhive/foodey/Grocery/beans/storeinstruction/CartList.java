
package com.geekhive.foodey.Grocery.beans.storeinstruction;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartList {

    @SerializedName("GCart")
    @Expose
    private GCart gCart;
    @SerializedName("Store")
    @Expose
    private Store store;
    @SerializedName("User")
    @Expose
    private User user;
    @SerializedName("Address")
    @Expose
    private Address address;
    @SerializedName("DeliveryBoy")
    @Expose
    private DeliveryBoy deliveryBoy;
    @SerializedName("GCartDetail")
    @Expose
    private List<GCartDetail> gCartDetail = null;

    public GCart getGCart() {
        return gCart;
    }

    public void setGCart(GCart gCart) {
        this.gCart = gCart;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryBoy getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(DeliveryBoy deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    public List<GCartDetail> getGCartDetail() {
        return gCartDetail;
    }

    public void setGCartDetail(List<GCartDetail> gCartDetail) {
        this.gCartDetail = gCartDetail;
    }

}
