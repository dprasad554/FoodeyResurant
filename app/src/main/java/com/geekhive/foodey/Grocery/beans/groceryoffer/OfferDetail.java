
package com.geekhive.foodey.Grocery.beans.groceryoffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferDetail {

    @SerializedName("GroceryOffer")
    @Expose
    private GroceryOffer_ groceryOffer;

    public GroceryOffer_ getGroceryOffer() {
        return groceryOffer;
    }

    public void setGroceryOffer(GroceryOffer_ groceryOffer) {
        this.groceryOffer = groceryOffer;
    }

}
