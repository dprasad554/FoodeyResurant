
package com.geekhive.foodey.Food.beans.restaurantmenulist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantMenuList {

    @SerializedName("Food")
    @Expose
    private List<Food> food = null;

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

}
