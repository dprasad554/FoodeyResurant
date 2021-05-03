
package com.geekhive.foodey.Grocery.beans.groceryproductlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GrocerySubProductList {

    @SerializedName("Grocery")
    @Expose
    private List<Grocery> grocery = null;

    public List<Grocery> getGrocery() {
        return grocery;
    }

    public void setGrocery(List<Grocery> grocery) {
        this.grocery = grocery;
    }

}
