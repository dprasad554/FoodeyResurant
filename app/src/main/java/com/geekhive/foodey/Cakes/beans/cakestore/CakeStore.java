
package com.geekhive.foodey.Cakes.beans.cakestore;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeStore {

    @SerializedName("CakeStore")
    @Expose
    private CakeStore_ cakeStore;
    @SerializedName("Cake")
    @Expose
    private List<Cake> cake = null;
    @SerializedName("CakeOffer")
    @Expose
    private List<CakeOffer> cakeOffer = null;

    public CakeStore_ getCakeStore() {
        return cakeStore;
    }

    public void setCakeStore(CakeStore_ cakeStore) {
        this.cakeStore = cakeStore;
    }

    public List<Cake> getCake() {
        return cake;
    }

    public void setCake(List<Cake> cake) {
        this.cake = cake;
    }

    public List<CakeOffer> getCakeOffer() {
        return cakeOffer;
    }

    public void setCakeOffer(List<CakeOffer> cakeOffer) {
        this.cakeOffer = cakeOffer;
    }

}
