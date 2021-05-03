
package com.geekhive.foodey.Food.beans.restdeltip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestDeliveryTip {

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
