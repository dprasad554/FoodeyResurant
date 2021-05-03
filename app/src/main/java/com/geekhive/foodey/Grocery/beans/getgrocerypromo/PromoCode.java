
package com.geekhive.foodey.Grocery.beans.getgrocerypromo;

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
