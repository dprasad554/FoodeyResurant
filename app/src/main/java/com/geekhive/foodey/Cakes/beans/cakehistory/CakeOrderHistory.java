
package com.geekhive.foodey.Cakes.beans.cakehistory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeOrderHistory {

    @SerializedName("CakeCart")
    @Expose
    private List<CakeCart> cakeCart = null;
    @SerializedName("CakeCartDetail")
    @Expose
    private List<List<CakeCartDetail>> cakeCartDetail = null;
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

    public List<CakeStore> getCakeStore() {
        return cakeStore;
    }

    public void setCakeStore(List<CakeStore> cakeStore) {
        this.cakeStore = cakeStore;
    }

}
