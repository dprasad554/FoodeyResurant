
package com.geekhive.foodey.Cakes.beans.storerating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Raiting {

    @SerializedName("CRating")
    @Expose
    private CRating cRating;

    public CRating getCRating() {
        return cRating;
    }

    public void setCRating(CRating cRating) {
        this.cRating = cRating;
    }

}
