
package com.geekhive.foodey.Grocery.beans.grocerycheckout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryCheckout {

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
