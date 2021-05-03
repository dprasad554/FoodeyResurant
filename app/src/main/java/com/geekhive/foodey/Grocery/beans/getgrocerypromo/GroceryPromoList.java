
package com.geekhive.foodey.Grocery.beans.getgrocerypromo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryPromoList {

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
