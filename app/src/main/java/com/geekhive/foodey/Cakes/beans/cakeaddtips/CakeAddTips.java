
package com.geekhive.foodey.Cakes.beans.cakeaddtips;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeAddTips {

    @SerializedName("Tip")
    @Expose
    private Tip tip;

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

}
