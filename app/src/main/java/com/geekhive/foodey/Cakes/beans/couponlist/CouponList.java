
package com.geekhive.foodey.Cakes.beans.couponlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponList {

    @SerializedName("PromoCode")
    @Expose
    private List<PromoCode> promoCode = null;

    public List<PromoCode> getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(List<PromoCode> promoCode) {
        this.promoCode = promoCode;
    }

}
