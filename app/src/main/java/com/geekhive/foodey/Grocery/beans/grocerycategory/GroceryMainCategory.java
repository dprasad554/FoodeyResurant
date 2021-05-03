
package com.geekhive.foodey.Grocery.beans.grocerycategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryMainCategory {

    @SerializedName("GroceryCategory")
    @Expose
    private List<GroceryCategory> groceryCategory = null;

    public List<GroceryCategory> getGroceryCategory() {
        return groceryCategory;
    }

    public void setGroceryCategory(List<GroceryCategory> groceryCategory) {
        this.groceryCategory = groceryCategory;
    }

}
