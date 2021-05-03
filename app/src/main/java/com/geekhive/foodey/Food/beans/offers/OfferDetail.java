
package com.geekhive.foodey.Food.beans.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferDetail {

    @SerializedName("Offer")
    @Expose
    private Offer offer;
    @SerializedName("Resturant")
    @Expose
    private Resturant resturant;

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

}
