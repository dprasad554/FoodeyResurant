
package com.geekhive.foodey.Food.beans.restaurantdetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResturantDetail {

    @SerializedName("Resturant")
    @Expose
    private List<Resturant> resturant = null;
    @SerializedName("Food")
    @Expose
    private List<Food> food = null;

    public List<Resturant> getResturant() {
        return resturant;
    }

    public void setResturant(List<Resturant> resturant) {
        this.resturant = resturant;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

}
