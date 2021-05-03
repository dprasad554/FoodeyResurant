
package com.geekhive.foodey.Grocery.beans.grocerypromoapply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryPromoApply {

    @SerializedName("GCartList")
    @Expose
    private GCartList gCartList;

    public GCartList getGCartList() {
        return gCartList;
    }

    public void setGCartList(GCartList gCartList) {
        this.gCartList = gCartList;
    }

}
