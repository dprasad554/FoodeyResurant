
package com.geekhive.foodey.Food.beans.promocode;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoCode {

    @SerializedName("PromoCode")
    @Expose
    private List<PromoCode_> promoCode = null;

    public List<PromoCode_> getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(List<PromoCode_> promoCode) {
        this.promoCode = promoCode;
    }

}
