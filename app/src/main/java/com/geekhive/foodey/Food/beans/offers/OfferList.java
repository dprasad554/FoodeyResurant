
package com.geekhive.foodey.Food.beans.offers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferList {

    @SerializedName("OfferDetails")
    @Expose
    private List<OfferDetail> offerDetails = null;

    public List<OfferDetail> getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(List<OfferDetail> offerDetails) {
        this.offerDetails = offerDetails;
    }

}
