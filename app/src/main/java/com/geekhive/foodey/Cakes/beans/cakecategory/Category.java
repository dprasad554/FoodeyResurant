
package com.geekhive.foodey.Cakes.beans.cakecategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("CakeCategory")
    @Expose
    private List<CakeCategory> cakeCategory = null;

    public List<CakeCategory> getCakeCategory() {
        return cakeCategory;
    }

    public void setCakeCategory(List<CakeCategory> cakeCategory) {
        this.cakeCategory = cakeCategory;
    }

}
