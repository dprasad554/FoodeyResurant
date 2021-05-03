
package com.geekhive.foodey.Cakes.beans.bestselling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestSellingProduct {

    @SerializedName("CakeList")
    @Expose
    private CakeList cakeList;

    public CakeList getCakeList() {
        return cakeList;
    }

    public void setCakeList(CakeList cakeList) {
        this.cakeList = cakeList;
    }

}
