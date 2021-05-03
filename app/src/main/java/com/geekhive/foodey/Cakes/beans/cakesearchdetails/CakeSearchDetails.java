
package com.geekhive.foodey.Cakes.beans.cakesearchdetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeSearchDetails {

    @SerializedName("Cake")
    @Expose
    private List<Cake> cake = null;

    public List<Cake> getCake() {
        return cake;
    }

    public void setCake(List<Cake> cake) {
        this.cake = cake;
    }

}
