
package com.geekhive.foodey.Grocery.beans.recentpurchase;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentPurchase_ {

    @SerializedName("GCart")
    @Expose
    private List<GCart> gCart = null;
    @SerializedName("GCartDetail")
    @Expose
    private List<List<GCartDetail>> gCartDetail = null;
    @SerializedName("Store")
    @Expose
    private List<Store> store = null;

    public List<GCart> getGCart() {
        return gCart;
    }

    public void setGCart(List<GCart> gCart) {
        this.gCart = gCart;
    }

    public List<List<GCartDetail>> getGCartDetail() {
        return gCartDetail;
    }

    public void setGCartDetail(List<List<GCartDetail>> gCartDetail) {
        this.gCartDetail = gCartDetail;
    }

    public List<Store> getStore() {
        return store;
    }

    public void setStore(List<Store> store) {
        this.store = store;
    }

}
