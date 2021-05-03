
package com.geekhive.foodey.Food.beans.restaurantList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantListOut {

    @SerializedName("Resturant")
    @Expose
    private List<Resturant> resturant = null;

    public List<Resturant> getResturant() {
        return resturant;
    }

    public void setResturant(List<Resturant> resturant) {
        this.resturant = resturant;
    }

}
