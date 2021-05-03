
package com.geekhive.foodey.Cakes.beans.cakecartlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartList_ {

    @SerializedName("CakeCart")
    @Expose
    private CakeCart cakeCart;
    @SerializedName("CakeStore")
    @Expose
    private CakeStore cakeStore;
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

    public List<CakeCartDetail> getCakeCartDetail() {
        return cakeCartDetail;
    }

    public void setCakeCartDetail(List<CakeCartDetail> cakeCartDetail) {
        this.cakeCartDetail = cakeCartDetail;
    }

}
