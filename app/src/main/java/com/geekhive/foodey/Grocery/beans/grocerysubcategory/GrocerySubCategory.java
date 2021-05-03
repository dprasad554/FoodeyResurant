
package com.geekhive.foodey.Grocery.beans.grocerysubcategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GrocerySubCategory {

    @SerializedName("ProductSubCategory")
    @Expose
    private List<GroceryProductSubCategory> productSubCategory = null;

    public List<GroceryProductSubCategory> getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(List<GroceryProductSubCategory> productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

}
