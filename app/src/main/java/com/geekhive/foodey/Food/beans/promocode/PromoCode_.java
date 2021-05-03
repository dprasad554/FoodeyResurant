
package com.geekhive.foodey.Food.beans.promocode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoCode_ {

    @SerializedName("PromoCode")
    @Expose
    private PromoCode__ promoCode;

    public PromoCode__ getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCode__ promoCode) {
        this.promoCode = promoCode;
    }

}
