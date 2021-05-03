
package com.geekhive.foodey.Food.beans.rating;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResturantRating {

    @SerializedName("Raiting")
    @Expose
    private List<Raiting> raiting = null;

    public List<Raiting> getRaiting() {
        return raiting;
    }

    public void setRaiting(List<Raiting> raiting) {
        this.raiting = raiting;
    }

}
