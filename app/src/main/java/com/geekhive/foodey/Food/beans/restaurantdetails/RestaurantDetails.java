
package com.geekhive.foodey.Food.beans.restaurantdetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantDetails {

    @SerializedName("ResturantDetails")
    @Expose
    private List<ResturantDetail> resturantDetails = null;

    public List<ResturantDetail> getResturantDetails() {
        return resturantDetails;
    }

    public void setResturantDetails(List<ResturantDetail> resturantDetails) {
        this.resturantDetails = resturantDetails;
    }

}
