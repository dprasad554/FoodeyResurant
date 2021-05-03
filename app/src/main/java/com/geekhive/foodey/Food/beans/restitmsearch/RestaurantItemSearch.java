
package com.geekhive.foodey.Food.beans.restitmsearch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantItemSearch {

    @SerializedName("FoodList")
    @Expose
    private List<FoodList> foodList = null;

    public List<FoodList> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodList> foodList) {
        this.foodList = foodList;
    }

}
