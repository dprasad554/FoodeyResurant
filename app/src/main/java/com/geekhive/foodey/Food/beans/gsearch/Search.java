
package com.geekhive.foodey.Food.beans.gsearch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("Food")
    @Expose
    private List<Food> food = null;
    @SerializedName("Resturant")
    @Expose
    private List<Resturant> resturant = null;

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public List<Resturant> getResturant() {
        return resturant;
    }

    public void setResturant(List<Resturant> resturant) {
        this.resturant = resturant;
    }

}
