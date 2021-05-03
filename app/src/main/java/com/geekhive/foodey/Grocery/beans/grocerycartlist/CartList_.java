
package com.geekhive.foodey.Grocery.beans.grocerycartlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartList_ {

    @SerializedName("GCart")
    @Expose
    private GCart gCart;
    @SerializedName("Store")
    @Expose
    private Store store;
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

    public List<GCartDetail> getGCartDetail() {
        return gCartDetail;
    }

    public void setGCartDetail(List<GCartDetail> gCartDetail) {
        this.gCartDetail = gCartDetail;
    }

}
