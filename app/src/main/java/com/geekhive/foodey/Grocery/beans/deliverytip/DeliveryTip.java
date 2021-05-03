
package com.geekhive.foodey.Grocery.beans.deliverytip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryTip {

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
