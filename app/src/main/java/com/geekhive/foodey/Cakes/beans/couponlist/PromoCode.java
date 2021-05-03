
package com.geekhive.foodey.Cakes.beans.couponlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoCode {

    @SerializedName("PromoCode")
    @Expose
    private PromoCode_ promoCode;

    public PromoCode_ getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCode_ promoCode) {
        this.promoCode = promoCode;
    }

}
