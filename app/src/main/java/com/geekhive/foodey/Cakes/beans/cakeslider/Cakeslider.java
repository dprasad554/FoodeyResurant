
package com.geekhive.foodey.Cakes.beans.cakeslider;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cakeslider {

    @SerializedName("CSlider")
    @Expose
    private List<CSlider> cSlider = null;

    public List<CSlider> getCSlider() {
        return cSlider;
    }

    public void setCSlider(List<CSlider> cSlider) {
        this.cSlider = cSlider;
    }

}
