
package com.geekhive.foodey.Grocery.beans.searchdetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchDetails {

    @SerializedName("GroceryList")
    @Expose
    private List<GroceryList> groceryList = null;

    public List<GroceryList> getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(List<GroceryList> groceryList) {
        this.groceryList = groceryList;
    }

}
