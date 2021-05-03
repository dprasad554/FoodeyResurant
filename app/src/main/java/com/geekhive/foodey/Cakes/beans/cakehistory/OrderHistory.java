
package com.geekhive.foodey.Cakes.beans.cakehistory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistory {

    @SerializedName("CakeCart")
    @Expose
    private List<CakeCart> cakeCart = null;
    @SerializedName("CakeCartDetail")
    @Expose
    private List<List<CakeCartDetail>> cakeCartDetail = null;
    @SerializedName("DeliveryBoy")
    @Expose
    private List<DeliveryBoy> deliveryBoy = null;
    @SerializedName("CakeStore")
    @Expose
    private List<CakeStore> cakeStore = null;

    public List<CakeCart> getCakeCart() {
        return cakeCart;
    }

    public void setCakeCart(List<CakeCart> cakeCart) {
        this.cakeCart = cakeCart;
    }

    public List<List<CakeCartDetail>> getCakeCartDetail() {
        return cakeCartDetail;
    }

    public void setCakeCartDetail(List<List<CakeCartDetail>> cakeCartDetail) {
        this.cakeCartDetail = cakeCartDetail;
    }

    public List<DeliveryBoy> getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(List<DeliveryBoy> deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    public List<CakeStore> getCakeStore() {
        return cakeStore;
    }

    public void setCakeStore(List<CakeStore> cakeStore) {
        this.cakeStore = cakeStore;
    }

}
