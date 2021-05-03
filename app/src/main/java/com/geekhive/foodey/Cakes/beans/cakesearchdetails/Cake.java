
package com.geekhive.foodey.Cakes.beans.cakesearchdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cake {

    @SerializedName("Cake")
    @Expose
    private Cake_ cake;

    public Cake_ getCake() {
        return cake;
    }

    public void setCake(Cake_ cake) {
        this.cake = cake;
    }

}
