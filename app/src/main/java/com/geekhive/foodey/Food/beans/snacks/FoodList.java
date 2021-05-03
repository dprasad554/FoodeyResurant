
package com.geekhive.foodey.Food.beans.snacks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodList {

    @SerializedName("Food")
    @Expose
    private Food food;
    @SerializedName("Resturant")
    @Expose
    private Resturant resturant;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

}
