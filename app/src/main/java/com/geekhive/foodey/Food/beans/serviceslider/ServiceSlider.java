
package com.geekhive.foodey.Food.beans.serviceslider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceSlider {

    @SerializedName("SSlider")
    @Expose
    private List<SSlider> sSlider = null;

    public List<SSlider> getSSlider() {
        return sSlider;
    }

    public void setSSlider(List<SSlider> sSlider) {
        this.sSlider = sSlider;
    }

}
