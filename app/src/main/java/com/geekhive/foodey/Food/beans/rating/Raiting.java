
package com.geekhive.foodey.Food.beans.rating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Raiting {

    @SerializedName("Rating")
    @Expose
    private Rating rating;
    @SerializedName("Resturant")
    @Expose
    private Resturant resturant;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

}
