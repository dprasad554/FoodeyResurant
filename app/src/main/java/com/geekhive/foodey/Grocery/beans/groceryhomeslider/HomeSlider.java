
package com.geekhive.foodey.Grocery.beans.groceryhomeslider;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeSlider {

    @SerializedName("GSlider")
    @Expose
    private List<GSlider> gSlider = null;

    public List<GSlider> getGSlider() {
        return gSlider;
    }

    public void setGSlider(List<GSlider> gSlider) {
        this.gSlider = gSlider;
    }

}
