
package com.geekhive.foodey.Food.beans.newslider;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResNewSlider {

    @SerializedName("Slider")
    @Expose
    private List<Slider> slider = null;

    public List<Slider> getSlider() {
        return slider;
    }

    public void setSlider(List<Slider> slider) {
        this.slider = slider;
    }

}
