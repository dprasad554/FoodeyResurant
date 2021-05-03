
package com.geekhive.foodey.Food.beans.rslider;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResSlider {

    @SerializedName("RSlider")
    @Expose
    private List<RSlider> rSlider = null;

    public List<RSlider> getRSlider() {
        return rSlider;
    }

    public void setRSlider(List<RSlider> rSlider) {
        this.rSlider = rSlider;
    }

}
