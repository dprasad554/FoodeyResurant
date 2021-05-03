
package com.geekhive.foodey.Food.beans.sliderhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderHomeListDown {

    @SerializedName("Slider")
    @Expose
    private List<String> slider = null;

    public List<String> getSlider() {
        return slider;
    }

    public void setSlider(List<String> slider) {
        this.slider = slider;
    }

}
