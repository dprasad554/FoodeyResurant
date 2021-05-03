
package com.geekhive.foodey.Cakes.beans.promoselect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakePromoSelect {

    @SerializedName("CakeCartList")
    @Expose
    private CakeCartList cakeCartList;

    public CakeCartList getCakeCartList() {
        return cakeCartList;
    }

    public void setCakeCartList(CakeCartList cakeCartList) {
        this.cakeCartList = cakeCartList;
    }

}
