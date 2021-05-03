
package com.geekhive.foodey.Grocery.beans.grocerybestselling;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryList {

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
