
package com.geekhive.foodey.Cakes.beans.cakeoffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferDetail {

    @SerializedName("CakeOffer")
    @Expose
    private CakeOffer cakeOffer;
    @SerializedName("CakeStore")
    @Expose
    private CakeStore cakeStore;

    public CakeOffer getCakeOffer() {
        return cakeOffer;
    }

    public void setCakeOffer(CakeOffer cakeOffer) {
        this.cakeOffer = cakeOffer;
    }

    public CakeStore getCakeStore() {
        return cakeStore;
    }

    public void setCakeStore(CakeStore cakeStore) {
        this.cakeStore = cakeStore;
    }

}
