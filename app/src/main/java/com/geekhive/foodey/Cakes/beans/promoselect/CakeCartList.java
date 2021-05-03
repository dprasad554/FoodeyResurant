
package com.geekhive.foodey.Cakes.beans.promoselect;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeCartList {

    @SerializedName("CakeCart")
    @Expose
    private CakeCart cakeCart;
    @SerializedName("CakeStore")
    @Expose
    private CakeStore cakeStore;
    @SerializedName("User")
    @Expose
    private User user;
    @SerializedName("Address")
    @Expose
    private Address address;
    @SerializedName("DeliveryBoy")
    @Expose
    private DeliveryBoy deliveryBoy;
    @SerializedName("CakeCartDetail")
    @Expose
    private List<CakeCartDetail> cakeCartDetail = null;

    public CakeCart getCakeCart() {
        return cakeCart;
    }

    public void setCakeCart(CakeCart cakeCart) {
        this.cakeCart = cakeCart;
    }

    public CakeStore getCakeStore() {
        return cakeStore;
    }

    public void setCakeStore(CakeStore cakeStore) {
        this.cakeStore = cakeStore;
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

    public List<CakeCartDetail> getCakeCartDetail() {
        return cakeCartDetail;
    }

    public void setCakeCartDetail(List<CakeCartDetail> cakeCartDetail) {
        this.cakeCartDetail = cakeCartDetail;
    }

}
