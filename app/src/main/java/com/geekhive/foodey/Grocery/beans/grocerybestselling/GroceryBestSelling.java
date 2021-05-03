
package com.geekhive.foodey.Grocery.beans.grocerybestselling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryBestSelling {

    @SerializedName("GroceryList")
    @Expose
    private GroceryList groceryList;

    public GroceryList getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(GroceryList groceryList) {
        this.groceryList = groceryList;
    }

}
